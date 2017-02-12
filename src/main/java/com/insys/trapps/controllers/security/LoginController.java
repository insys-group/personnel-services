package com.insys.trapps.controllers.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.AuthToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author msabir
 *
 */
@RestController
//@Component
public class LoginController { //extends HttpServlet {
    @Value("${security.oauth2.client.client-id}")
    private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Value("${trapps.access-token.url}")
	private String accessTokenUrl;
	
	private Header[] headers;
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/restlogin")
	public ResponseEntity<AuthToken> executeRestLogin(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
	    logger.debug("Request URL is " + request.getRequestURI() + " " + request.getRequestURL());
	    String requestURL=request.getRequestURL().toString();
	    String requestURI=request.getRequestURI();
	    String tokenUrl=requestURL.substring(0, requestURL.indexOf(requestURI)) + accessTokenUrl;

        HttpClient httpclient = HttpClientBuilder.create().build();
        
		HttpPost httppost = new HttpPost(tokenUrl);
		httppost.setEntity(createForm(username, password));
		httppost.setHeaders(headers);
		
		AuthToken token=new AuthToken();
		ResponseEntity<AuthToken> responseEntity=null;
		try {
            ObjectMapper mapper = new ObjectMapper();
			HttpResponse response=httpclient.execute(httppost);
			logger.debug("Response from " + tokenUrl + "\n" + response.toString() + " " + response.getEntity().getContentLength() + response.getEntity().getContentType());
			int status = response.getStatusLine().getStatusCode();
			if(status==HttpStatus.OK.value()) {
				Map<String, String> tokenMap = mapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, String>>(){});
				token.setAccessToken(tokenMap.get("access_token"));
				token.setRefreshToken(tokenMap.get("refresh_token"));
				token.setTokenType(tokenMap.get("token_type"));
                token.setExpiresIn(Long.parseLong(tokenMap.get("expires_in")));
                token.setScope(tokenMap.get("scope"));
                logger.debug("Token is " + mapper.writeValueAsString(token));
                responseEntity=ResponseEntity
                        .status(response.getStatusLine().getStatusCode())
                        .body(token);
            } else {
                responseEntity=ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(null);
            }
		} catch(IOException e) {
			logger.error("Error while calling /oauth/token", e);
			responseEntity=ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
		return responseEntity;
	}
	
	private UrlEncodedFormEntity createForm(String username, String password) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("grant_type", "password"));
		formParams.add(new BasicNameValuePair("client_id", clientId));
		formParams.add(new BasicNameValuePair("client_secret", clientSecret));
		formParams.add(new BasicNameValuePair("username", username));
		formParams.add(new BasicNameValuePair("password", password));
		return new UrlEncodedFormEntity(formParams, Consts.UTF_8);
	}
	
	@PostConstruct
	private void createHeaders() {
		List<Header> headers=new ArrayList<>();
		headers.add(new BasicHeader("Authorization", "Basic " +
				new String(Base64.encodeBase64((clientId+":"+clientSecret).getBytes(Consts.UTF_8)), Consts.UTF_8)));
		this.headers=headers.toArray(new Header[0]);
	}

}


/*

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		logger.debug("Enter: LoginController.doPost()");

		ResourceOwnerPasswordResourceDetails resourceDetails=createResourceDetails(username, password);
		OAuth2RestTemplate template=null;
		OAuth2AccessToken accessToken=null;
		AuthToken token=null;
		try {
			template=new OAuth2RestTemplate(resourceDetails);
			accessToken=template.getAccessToken();
			token=extractToken(accessToken);
			logger.debug("**** Rest Token from servlet is " + token.toString());
			resp.getOutputStream().write(token.toString().getBytes());
		}catch(Exception e) {
			logger.error("Error while Authenticating ", e);
		}
	}

	private ResourceOwnerPasswordResourceDetails createResourceDetails(String username, String password) {
		logger.debug("Enter: LoginController.login()" + username + ", " + password + ", " + clientId + ", " + clientSecret + ", " + accessTokenUrl );

		ResourceOwnerPasswordResourceDetails resourceDetails=new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		resourceDetails.setAccessTokenUri(accessTokenUrl);
		resourceDetails.setScope(Arrays.asList("read", "write"));
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(clientSecret);
		resourceDetails.setUsername(username);
		resourceDetails.setPassword(password);
		resourceDetails.setGrantType("password");

		logger.debug("Getting ready to send to oauth/token");

		return resourceDetails;
	}

	private AuthToken extractToken(OAuth2AccessToken accessToken) {
		AuthToken token=new AuthToken();
		token.setAccessToken(accessToken.getValue());
		token.setTokenType(accessToken.getTokenType());
		token.setExpiresIn((long)accessToken.getExpiresIn());
		token.setExpiration(accessToken.getExpiration());
		//token.setScopes(accessToken.getScope());
		token.setRefreshToken(accessToken.getRefreshToken().getValue());
		return token;
	}


	//@PostMapping("/restlogin")
    public ResponseEntity<AuthToken> login(
    		HttpServletRequest request,
    		@RequestBody PasswordCredentials credentials) {
		ResourceOwnerPasswordResourceDetails resourceDetails=createResourceDetails(credentials.getUsername(), credentials.getPassword());

		OAuth2RestTemplate template=null;
		OAuth2AccessToken accessToken=null;
		AuthToken token=null;
		try {
			template=new OAuth2RestTemplate(resourceDetails);
			accessToken=template.getAccessToken();
			token=extractToken(accessToken);
			logger.debug("**** Rest Token is " + token.toString());
		}catch(Exception e) {
			logger.error("Error while Authenticating ", e);
		}
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(token);
	}

 */