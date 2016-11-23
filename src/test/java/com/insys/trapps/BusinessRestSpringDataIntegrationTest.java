package com.insys.trapps;

import java.net.URI;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * {@link Integration Test using RestTemplate} for PersonnelServices.
 * 
 * @author  Kris Krishna
 * @since 1.0.0
**/


public class BusinessRestSpringDataIntegrationTest {
	
	private final RestTemplate restTemplate = new RestTemplate();

	    private final String businessesUrl = "http://localhost:8080/businesses";

	    private final String addressesUrl = "http://localhost:8080/addresses";

	    @Test
	    public void testCreateBusinessWithLocations() throws Exception {
	        final URI businessesUri = restTemplate.postForLocation(businessesUrl, sampleBusiness()); // create Author
	 
	        /*final URI addressUri = new URI(addressesUrl + "/" + sampleBookIsbn);
	        restTemplate.put(bookUri, sampleBook(authorUri.toString())); // create Book linked to Author
	 
	        Resource<Book> book = getBook(bookUri);
	        assertNotNull(book);
	 
	        final URI authorsOfBookUri = new URI(book.getLink("books.Book.authors").getHref());
	        Resource<List<Resource<Author>>> authors = getAuthors(authorsOfBookUri);
	        assertNotNull(authors.getContent());
	        assertFalse(authors.getContent().isEmpty()); */// check if /books/0132350882/authors contains an author
	    }
	 
	    private String sampleBusiness() {
	        return "{\"businessId\":null,\"name\":\"business-consulting\",\"description\":\"testing-denver\",\"businessType\":\"INSYS\",\"locations\":[{\"locationId\":null,\"address\":{\"addressId\":null,\"street\":\"Luxoft Street\",\"city\":\"Seattle\",\"state\":\"WA\",\"zip\":\"70014\"}},{\"locationId\":null,\"address\":{\"addressId\":null,\"street\":\"Insys Street\",\"city\":\"Denver\",\"state\":\"CO\",\"zip\":\"80014\"}}]}";
	    }
	 
	   /* private final String sampleBookIsbn = "0132350882";
	 
	    private String sampleBook(String authorUrl) {
	        return "{\"title\":\"Clean Code\",\"authors\":[{\"rel\":\"authors\",\"href\":\"" + authorUrl + "\"}]}";
	    }
	 
	    private Resource<Book> getBook(URI uri) {
	        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Book>>() {
	        }).getBody();
	    }
	 
	    private Resource<List<Resource<Author>>> getAuthors(URI uri) {
	        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<List<Resource<Author>>>>() {
	        }).getBody();
	    }
	}*/

}
