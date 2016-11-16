package com.insys.trapps.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.BusinessEntityType;
import com.insys.trapps.model.Client;
import com.insys.trapps.model.Location;
import com.insys.trapps.respositories.ClientRepository;
import com.insys.trapps.util.ClientBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientRepositoryTest.class); 
	
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClientRepository clientRepository;
    private Client testClient;
   
    
    /*@Test
    public void testFindByName() {
    	Address address_1 = new Address(Long.valueOf(1), "Insys Street", "Denver", "CO", "80014");
		Location location_1 = new Location(Long.valueOf(1), address_1);
		
		Address address_2 = new Address(Long.valueOf(2), "Luxoft Street", "Seattle", "WA", "70014");
		Location location_2 = new Location(Long.valueOf(2), address_2);
		Collection<Location> locations = Arrays.asList(location_1, location_2);
		
		Client client = new Client(
		           "test", "testing-denver business", 
		          BusinessEntityType.CONSULTING_TYPE,  locations);
		
        entityManager.persist(client);
        Client clientResult = clientRepository.findOne(Long.valueOf(1));
        assertEquals("test", clientResult.getName());
    }*/
    
    
    @Before
    public void beforeEachMethod() {
    	
    	Address address_1 = new Address(Long.valueOf(1), "Insys Street", "Denver", "CO", "80014");
		Location location_1 = new Location(Long.valueOf(1), address_1);
		
		Address address_2 = new Address(Long.valueOf(2), "Luxoft Street", "Seattle", "WA", "70014");
		Location location_2 = new Location(Long.valueOf(2), address_2);
		Collection<Location> locations = Arrays.asList(location_1, location_2);
		
		Client client = new Client(
		           "test", "testing-denver business", 
		          BusinessEntityType.CONSULTING_TYPE,  locations);
		
		testClient=ClientBuilder.buildClient(client).build();
    }
    
    @Test
    public void testSaveClient() throws Exception {
    	logger.debug("Enter: testSaveClient");
        this.clientRepository.save(testClient);
        Iterable<Client> clients = this.clientRepository.findAll();
        clients.forEach(client ->{
        	logger.debug("@@@  Client is " + client.toString());
        	assertTrue(testClient.getName().contains(client.getName()));
        	assertNotNull(client.getDescription());
        });
        
        this.clientRepository.delete(testClient);
    }
    
    @Test
    public void testFindClient() throws Exception {
    	logger.debug("Enter: testSaveClient");
        this.clientRepository.save(testClient);
        Iterable<Client> clients = this.clientRepository.findAll();
        clients.forEach(client ->{
        	logger.debug("@@@  Client is " + client.toString());
        	assertTrue(testClient.getName().contains(client.getName()));
        	assertNotNull(client.getDescription());
        });
        
        this.clientRepository.delete(testClient);
    }
    
}
