package com.insys.trapps.controllers.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.AuthToken;
import com.insys.trapps.model.PasswordCredentials;

/**
 * @author msabir
 *
 */
@RestController
public class LoginController {
    @Value("${security.oauth2.client.client-id}")
    private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Value("${trapps.access-token.url}")
	private String accessTokenUrl;
	
	//private Header[] headers;
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	//@CrossOrigin
	@PostMapping("/restlogin")
    public ResponseEntity<AuthToken> login(
    		HttpServletRequest request,
    		@RequestBody PasswordCredentials credentials) {
		logger.debug("Enter: LoginController.login()" + credentials.getUsername() + ", " + credentials.getPassword());
		
		ResourceOwnerPasswordResourceDetails resourceDetails=new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		resourceDetails.setAccessTokenUri(accessTokenUrl);
		resourceDetails.setScope(Arrays.asList("read", "write"));
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(clientSecret);
		resourceDetails.setUsername(credentials.getUsername());
		resourceDetails.setPassword(credentials.getPassword());

		logger.debug("Getting ready to send to oauth/token");

		OAuth2RestTemplate template=null;
		OAuth2AccessToken accessToken=null;

		try {
			template=new OAuth2RestTemplate(resourceDetails);
			accessToken=template.getAccessToken();
		}catch(Exception e) {
			logger.error("Error while Authenticating ", e);
		}
		AuthToken token=new AuthToken();
		token.setAccessToken(accessToken.getValue());
		token.setTokenType(accessToken.getTokenType());
		token.setExpiresIn((long)accessToken.getExpiresIn());
		token.setExpiration(accessToken.getExpiration());
		token.setScopes(accessToken.getScope());
		token.setRefreshToken(accessToken.getRefreshToken().getValue());
		
		logger.debug("**** Rest Token is " + token.toString());

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(token);
	}
	
	@RequestMapping(path="/restlogin", method=RequestMethod.OPTIONS)
    public ResponseEntity<?> loginOptions(
    		HttpServletRequest request,
    		HttpServletResponse response) {
		logger.debug("Enter: LoginController.loginOptions()");
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(null);
	}
	/*
	private ResponseEntity<AuthToken> executeRestLogin(HttpServletRequest request, String username, String password) {
	    logger.debug("Request URL is " + request.getRequestURI() + " " + request.getRequestURL());
	    String requestURL=request.getRequestURL().toString();
	    String requestURI=request.getRequestURI();
	    String tokenURL=requestURL.substring(0, requestURL.indexOf(requestURI)) + "/oauth/token";
	    
        HttpClient httpclient = HttpClientBuilder.create().build();
        
		HttpPost httppost = new HttpPost(tokenURL);
		httppost.setEntity(createForm(username, password));
		httppost.setHeaders(headers);
		
		AuthToken token=null;
		ResponseEntity<AuthToken> responseEntity=null;
		try {
			HttpResponse response=httpclient.execute(httppost);
			logger.debug("Response from " + tokenURL + "\n" + response.toString());
			if(response.getStatusLine().getStatusCode()==HttpStatus.OK.value()) {
				ObjectMapper mapper = new ObjectMapper();
				token = mapper.readValue(response.getEntity().getContent(), AuthToken.class);
			}
			logger.debug("Token is " + token);
			responseEntity=ResponseEntity
					.status(response.getStatusLine().getStatusCode())
					.body(token);
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
	*/
}