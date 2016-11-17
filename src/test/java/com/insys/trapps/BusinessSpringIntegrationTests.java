package com.insys.trapps;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.util.Builder;

/**
 * @author kkrish003c
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(value = "classpath:clients-test-resource.xml")
@TestPropertySource(locations="classpath:test.properties")
public class BusinessSpringIntegrationTests {

    private static final Logger logger = LoggerFactory.getLogger(BusinessSpringIntegrationTests.class);

    protected MockMvc mvc;
    

    @Autowired
    private WebApplicationContext webApplicationContext;
    

    protected ObjectMapper objectMapper;
    

    @BeforeClass
    static public void setup() {
    
   
    }

    @AfterClass
    static public void teardown() {
       
    }


    @Before
    public void setUpMock() throws Exception {
    	MockitoAnnotations.initMocks(this);
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	objectMapper = new ObjectMapper(); 
    }
    
    @Test
    @Ignore
    public void testListBusinesses() throws Exception {
        
        ResultActions resultActions = mvc.perform(get("/business"));
        
        MvcResult result = resultActions.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith("application/json")).andReturn();

        String content = result.getResponse().getContentAsString();
        
        logger.info(content);
       
        assertNotNull(content);

    }
    
    
    @Test
    public void testCreateBusiness() throws Exception {
    	
      	Address address_1 = new Address("Insys Street", "Denver", "CO", "80014");
		Address address_2 = new Address("Luxoft Street", "Seattle", "WA", "70014");

		Business testBuisness = Builder.buildBusiness("test", "testing-denver",  BusinessType.INSYS).addLocation(address_1).addLocation(address_2).build();
        
        ResultActions resultActions = mvc.perform(post("/business").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testBuisness)));
        
        MvcResult result = resultActions.andExpect(status().isCreated()).andExpect(content().contentTypeCompatibleWith("application/json")).andReturn();

        String content = result.getResponse().getContentAsString();
        
        logger.info(content);
       
        assertNotNull(content);

    }
    
    /*@Test
    public void testGetCustomerProductsWithSessionIdInCXP_CacheOnly() throws Exception {
        
        serviceContext.put("sourceServerId", "CXP-TESTING");
        serviceContext.put("sourceSystemId", "CXP-TESTING");
        serviceContext.put("Timestamp", "2015-11-09T21:24:05");
        serviceContext.put("TrackingId", "CXP_rsmith006c_20160520_005");
        serviceContext.put("Authorization", token_type +" "+ accessToken);
       // serviceContext.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJjbGllbnRfaWQiOiJjY19jbGllbnQiLCJleHAiOjE0NzIwODM2MjEsInNjb3BlIjpbImVzcCJdLCJpc3MiOiJQaW5nRmVkZXJhdGUifQ.PL_AJgwseierRMPnXZv_WbxXawl5Agz5--7hsths06K_L6RRUyjKR65-AC1CZwfM2c60gku_PHcDJO4Wkx-cMLhqI7MF6y9WFlCoT01umaWsc_mBcQe7Gdd4D6ayYqFt-E0SpyG55s8J3rRdSTMxJPRWiKIOkHm_kHgTjDHtja0JqHltu-wtztRJsr_vx-pK_TCU1wIhlo5jRfVOJtDWUKjlRuvApzRay3hmEDjaUpDhp8wWetuAhNgQfq95nl2DSzJ91QMS8KQx_mWlPo-qgBiEaVLrzjULZrxwtvj26eQNXemrI9Xn4ncawDyX-23mSAD8CMVUzuI9ShxFOJDAsg");
        
        ResultActions resultActions = mvc.perform(get(Endpoints.CUSTOMER_PRODUCTS).param("sessionId", sessionId).param("cacheRefresh", "CACHE_ONLY").header("sourceServerId", "CXP-TESTING").header("sourceSystemId", "CXP-TESTING").header("Timestamp", "2015-11-09T21:24:05")
        		.header("TrackingId", "CXP_rsmith006c_20160520_005").header("Content-Type", "application/json")
        		.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJjbGllbnRfaWQiOiJjY19jbGllbnQiLCJleHAiOjE0NzIwODM2MjEsInNjb3BlIjpbImVzcCJdLCJpc3MiOiJQaW5nRmVkZXJhdGUifQ.PL_AJgwseierRMPnXZv_WbxXawl5Agz5--7hsths06K_L6RRUyjKR65-AC1CZwfM2c60gku_PHcDJO4Wkx-cMLhqI7MF6y9WFlCoT01umaWsc_mBcQe7Gdd4D6ayYqFt-E0SpyG55s8J3rRdSTMxJPRWiKIOkHm_kHgTjDHtja0JqHltu-wtztRJsr_vx-pK_TCU1wIhlo5jRfVOJtDWUKjlRuvApzRay3hmEDjaUpDhp8wWetuAhNgQfq95nl2DSzJ91QMS8KQx_mWlPo-qgBiEaVLrzjULZrxwtvj26eQNXemrI9Xn4ncawDyX-23mSAD8CMVUzuI9ShxFOJDAsg"
        		));
        
        MvcResult result = resultActions.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith("application/json")).andReturn();

        String content = result.getResponse().getContentAsString();
        
        logger.info(content);
        
        assertNotNull(content);
        

    }

	@Test
    public void testGetCustomerProductsWithSessionIdNotInCXP() throws Exception {
        String sessionId = "12345";
        
        serviceContext.put("sourceServerId", "CXP-TESTING");
        serviceContext.put("sourceSystemId", "CXP-TESTING");
        serviceContext.put("Timestamp", "2015-11-09T21:24:05");
        serviceContext.put("TrackingId", "CXP_rsmith006c_20160520_005");
        serviceContext.put("Authorization", token_type +" "+ accessToken);
        //serviceContext.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJjbGllbnRfaWQiOiJjY19jbGllbnQiLCJleHAiOjE0NzIwODM2MjEsInNjb3BlIjpbImVzcCJdLCJpc3MiOiJQaW5nRmVkZXJhdGUifQ.PL_AJgwseierRMPnXZv_WbxXawl5Agz5--7hsths06K_L6RRUyjKR65-AC1CZwfM2c60gku_PHcDJO4Wkx-cMLhqI7MF6y9WFlCoT01umaWsc_mBcQe7Gdd4D6ayYqFt-E0SpyG55s8J3rRdSTMxJPRWiKIOkHm_kHgTjDHtja0JqHltu-wtztRJsr_vx-pK_TCU1wIhlo5jRfVOJtDWUKjlRuvApzRay3hmEDjaUpDhp8wWetuAhNgQfq95nl2DSzJ91QMS8KQx_mWlPo-qgBiEaVLrzjULZrxwtvj26eQNXemrI9Xn4ncawDyX-23mSAD8CMVUzuI9ShxFOJDAsg");
        
        ResultActions resultActions = mvc.perform(get(Endpoints.CUSTOMER_PRODUCTS).param("sessionId", sessionId).param("cacheRefresh", "FORCE_REFRESH").header("sourceServerId", "CXP-TESTING").header("sourceSystemId", "CXP-TESTING").header("Timestamp", "2015-11-09T21:24:05")
        		.header("TrackingId", "CXP_rsmith006c_20160520_005").header("Content-Type", "application/json")
        		.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJjbGllbnRfaWQiOiJjY19jbGllbnQiLCJleHAiOjE0NzIwODM2MjEsInNjb3BlIjpbImVzcCJdLCJpc3MiOiJQaW5nRmVkZXJhdGUifQ.PL_AJgwseierRMPnXZv_WbxXawl5Agz5--7hsths06K_L6RRUyjKR65-AC1CZwfM2c60gku_PHcDJO4Wkx-cMLhqI7MF6y9WFlCoT01umaWsc_mBcQe7Gdd4D6ayYqFt-E0SpyG55s8J3rRdSTMxJPRWiKIOkHm_kHgTjDHtja0JqHltu-wtztRJsr_vx-pK_TCU1wIhlo5jRfVOJtDWUKjlRuvApzRay3hmEDjaUpDhp8wWetuAhNgQfq95nl2DSzJ91QMS8KQx_mWlPo-qgBiEaVLrzjULZrxwtvj26eQNXemrI9Xn4ncawDyX-23mSAD8CMVUzuI9ShxFOJDAsg"
        		));
        
        MvcResult result = resultActions.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith("application/json")).andReturn();

        String content = result.getResponse().getContentAsString();
        
        logger.info(content);
        
        assertNotNull(content);
        
        //assertEquals( "{\"messages\":[{\"errorType\":\"WARNING\",\"errorCode\":\"CXP-1010\",\"errorMsg\":\"Unable to retrieve account information for given sessionId.\"}],\"sessionId\":null,\"rateCodeInfos\":null,\"productTags\":null}", content);

    }
	
	 @Test
	    public void testGetAsyncCustomerProductsWithSessionIdInCXP_CacheOnly() throws Exception {
	        
	        serviceContext.put("sourceServerId", "CXP-TESTING");
	        serviceContext.put("sourceSystemId", "CXP-TESTING");
	        serviceContext.put("Timestamp", "2015-11-09T21:24:05");
	        serviceContext.put("TrackingId", "CXP_rsmith006c_20160520_005");
	        serviceContext.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJjbGllbnRfaWQiOiJjY19jbGllbnQiLCJleHAiOjE0NzIwODM2MjEsInNjb3BlIjpbImVzcCJdLCJpc3MiOiJQaW5nRmVkZXJhdGUifQ.PL_AJgwseierRMPnXZv_WbxXawl5Agz5--7hsths06K_L6RRUyjKR65-AC1CZwfM2c60gku_PHcDJO4Wkx-cMLhqI7MF6y9WFlCoT01umaWsc_mBcQe7Gdd4D6ayYqFt-E0SpyG55s8J3rRdSTMxJPRWiKIOkHm_kHgTjDHtja0JqHltu-wtztRJsr_vx-pK_TCU1wIhlo5jRfVOJtDWUKjlRuvApzRay3hmEDjaUpDhp8wWetuAhNgQfq95nl2DSzJ91QMS8KQx_mWlPo-qgBiEaVLrzjULZrxwtvj26eQNXemrI9Xn4ncawDyX-23mSAD8CMVUzuI9ShxFOJDAsg");
	        
	        ResultActions resultActions = mvc.perform(get(Endpoints.CUSTOMER_PRODUCTS_ASYNC).param("sessionId", sessionId).param("cacheRefresh", "FORCE_REFRESH").header("sourceServerId", "CXP-TESTING").header("sourceSystemId", "CXP-TESTING").header("Timestamp", "2015-11-09T21:24:05")
	        		.header("TrackingId", "CXP_rsmith006c_20160520_005").header("Content-Type", "application/json")
	        		.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJjbGllbnRfaWQiOiJjY19jbGllbnQiLCJleHAiOjE0NzIwODM2MjEsInNjb3BlIjpbImVzcCJdLCJpc3MiOiJQaW5nRmVkZXJhdGUifQ.PL_AJgwseierRMPnXZv_WbxXawl5Agz5--7hsths06K_L6RRUyjKR65-AC1CZwfM2c60gku_PHcDJO4Wkx-cMLhqI7MF6y9WFlCoT01umaWsc_mBcQe7Gdd4D6ayYqFt-E0SpyG55s8J3rRdSTMxJPRWiKIOkHm_kHgTjDHtja0JqHltu-wtztRJsr_vx-pK_TCU1wIhlo5jRfVOJtDWUKjlRuvApzRay3hmEDjaUpDhp8wWetuAhNgQfq95nl2DSzJ91QMS8KQx_mWlPo-qgBiEaVLrzjULZrxwtvj26eQNXemrI9Xn4ncawDyX-23mSAD8CMVUzuI9ShxFOJDAsg"
	        		));
	        
	        MvcResult result = resultActions.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith("application/json")).andReturn();

	        String content = result.getResponse().getContentAsString();
	        
	        logger.info(content);
	        
	        assertNotNull(content);
	        

	    }

	 @Test
	    public void testGetAsyncCustomerProductsWithSessionIdInCXP_ForceRefresh() throws Exception {
	        
	        serviceContext.put("sourceServerId", "CXP-TESTING");
	        serviceContext.put("sourceSystemId", "CXP-TESTING");
	        serviceContext.put("Timestamp", "2015-11-09T21:24:05");
	        serviceContext.put("TrackingId", "CXP_rsmith006c_20160520_005");
	        serviceContext.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJjbGllbnRfaWQiOiJjY19jbGllbnQiLCJleHAiOjE0NzIwODM2MjEsInNjb3BlIjpbImVzcCJdLCJpc3MiOiJQaW5nRmVkZXJhdGUifQ.PL_AJgwseierRMPnXZv_WbxXawl5Agz5--7hsths06K_L6RRUyjKR65-AC1CZwfM2c60gku_PHcDJO4Wkx-cMLhqI7MF6y9WFlCoT01umaWsc_mBcQe7Gdd4D6ayYqFt-E0SpyG55s8J3rRdSTMxJPRWiKIOkHm_kHgTjDHtja0JqHltu-wtztRJsr_vx-pK_TCU1wIhlo5jRfVOJtDWUKjlRuvApzRay3hmEDjaUpDhp8wWetuAhNgQfq95nl2DSzJ91QMS8KQx_mWlPo-qgBiEaVLrzjULZrxwtvj26eQNXemrI9Xn4ncawDyX-23mSAD8CMVUzuI9ShxFOJDAsg");
	        
	        ResultActions resultActions = mvc.perform(get(Endpoints.CUSTOMER_PRODUCTS_ASYNC).param("sessionId", sessionId).param("cacheRefresh", "FORCE_REFRESH").header("sourceServerId", "CXP-TESTING").header("sourceSystemId", "CXP-TESTING").header("Timestamp", "2015-11-09T21:24:05")
	        		.header("TrackingId", "CXP_rsmith006c_20160520_005").header("Content-Type", "application/json")
	        		.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJjbGllbnRfaWQiOiJjY19jbGllbnQiLCJleHAiOjE0NzIwODM2MjEsInNjb3BlIjpbImVzcCJdLCJpc3MiOiJQaW5nRmVkZXJhdGUifQ.PL_AJgwseierRMPnXZv_WbxXawl5Agz5--7hsths06K_L6RRUyjKR65-AC1CZwfM2c60gku_PHcDJO4Wkx-cMLhqI7MF6y9WFlCoT01umaWsc_mBcQe7Gdd4D6ayYqFt-E0SpyG55s8J3rRdSTMxJPRWiKIOkHm_kHgTjDHtja0JqHltu-wtztRJsr_vx-pK_TCU1wIhlo5jRfVOJtDWUKjlRuvApzRay3hmEDjaUpDhp8wWetuAhNgQfq95nl2DSzJ91QMS8KQx_mWlPo-qgBiEaVLrzjULZrxwtvj26eQNXemrI9Xn4ncawDyX-23mSAD8CMVUzuI9ShxFOJDAsg"
	        		));
	        
	        MvcResult result = resultActions.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith("application/json")).andReturn();

	        String content = result.getResponse().getContentAsString();
	        
	        logger.info(content);
	       
	        assertNotNull(content);

	    }*/

    
}
