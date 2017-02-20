package com.insys.trapps.controllers.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.AuthToken;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.security.User;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.service.PersonService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author msabir
 *
 */
@RestController
public class LoginController {
    @Value("${trapps.client-id}")
    private String clientId;

	@Value("${trapps.client-secret}")
	private String clientSecret;
	
	@Value("${trapps.access-token-url}")
	private String accessTokenUrl;

	private Header[] headers;

	private PersonService personService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(PersonService personService) {
        this.personService=personService;
    }

    @PostMapping("/restlogin")
	public ResponseEntity<AuthToken> executeRestLogin2(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
	    logger.debug("Enter: LoginController()" + request.getRequestURI() + " " + request.getRequestURL() + " Username " + username + " Password " + password);
	    String requestURL=request.getRequestURL().toString();
	    String requestURI=request.getRequestURI();
	    String authTokenUrl=requestURL.substring(0, requestURL.indexOf(requestURI)) + accessTokenUrl;

        HttpClient httpclient = HttpClientBuilder.create().build();
        
		HttpPost httppost = new HttpPost(authTokenUrl);
		httppost.setEntity(createForm(username, password));
		httppost.setHeaders(headers);

        ObjectMapper mapper = new ObjectMapper();
		AuthToken authToken=new AuthToken();
		ResponseEntity<AuthToken> responseEntity=null;
		try {
			HttpResponse response=httpclient.execute(httppost);
			int status = response.getStatusLine().getStatusCode();
			logger.debug("Response from " + status + " " + authTokenUrl + "\n" + response.toString() + " Length " + response.getEntity().getContentLength() + " Type " + response.getEntity().getContentType());
            String tokenBody=convertToTokenBody(response.getEntity().getContent());
            logger.debug("Response body " + tokenBody);
			Map<String, String> authTokenMap = mapper.readValue(tokenBody, new TypeReference<Map<String, String>>(){});
			if(status==HttpStatus.OK.value()) {
				authToken.setAccessToken(authTokenMap.get("access_token"));
				authToken.setRefreshToken(authTokenMap.get("refresh_token"));
				authToken.setTokenType(authTokenMap.get("token_type"));
                authToken.setExpiresIn(Long.parseLong(authTokenMap.get("expires_in")));
                authToken.setScope(authTokenMap.get("scope"));
                loadPersonDetails(authToken, username);
            } else {
				authToken.setError(authTokenMap.get("error"));
				authToken.setErrorDescription(authTokenMap.get("error_description"));
            }
			logger.debug("Token is " + mapper.writeValueAsString(authToken));
			responseEntity=ResponseEntity
					.status(status)
					.body(authToken);
		} catch(IOException e) {
			logger.error("Error while calling /oauth/authToken", e);
			responseEntity=ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
		return responseEntity;
	}

	private String convertToTokenBody(InputStream stream) {
        return new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining(" "));
    }

	private void loadPersonDetails(AuthToken authToken, String username) {
        logger.debug("Enter: LoginController.loadPersonDetails" + username);
        authToken.setUsername(username);
        User user=personService.findUser(username);
        authToken.setAuthorities(user.getAuthorities().stream().map(authority->authority.getAuthority()).collect(Collectors.joining(",")));
        Person person=personService.findPerson(user.getPersonId());
        authToken.setId(person.getId());
        authToken.setFirstName(person.getFirstName());
        authToken.setLastName(person.getLastName());
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
		AuthToken authToken=null;
		try {
			template=new OAuth2RestTemplate(resourceDetails);
			accessToken=template.getAccessToken();
			authToken=extractToken(accessToken);
			logger.debug("**** Rest Token from servlet is " + authToken.toString());
			resp.getOutputStream().write(authToken.toString().getBytes());
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

		logger.debug("Getting ready to send to oauth/authToken");

		return resourceDetails;
	}

	private AuthToken extractToken(OAuth2AccessToken accessToken) {
		AuthToken authToken=new AuthToken();
		authToken.setAccessToken(accessToken.getValue());
		authToken.setTokenType(accessToken.getTokenType());
		authToken.setExpiresIn((long)accessToken.getExpiresIn());
		authToken.setExpiration(accessToken.getExpiration());
		//authToken.setScopes(accessToken.getScope());
		authToken.setRefreshToken(accessToken.getRefreshToken().getValue());
		return authToken;
	}


	//@PostMapping("/restlogin")
    public ResponseEntity<AuthToken> login(
    		HttpServletRequest request,
    		@RequestBody PasswordCredentials credentials) {
		ResourceOwnerPasswordResourceDetails resourceDetails=createResourceDetails(credentials.getUsername(), credentials.getPassword());

		OAuth2RestTemplate template=null;
		OAuth2AccessToken accessToken=null;
		AuthToken authToken=null;
		try {
			template=new OAuth2RestTemplate(resourceDetails);
			accessToken=template.getAccessToken();
			authToken=extractToken(accessToken);
			logger.debug("**** Rest Token is " + authToken.toString());
		}catch(Exception e) {
			logger.error("Error while Authenticating ", e);
		}
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(authToken);
	}

 */