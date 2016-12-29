/**
 * 
 */
package com.insys.trapps.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.TrappsApiApplication;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonDocument;
import com.insys.trapps.model.PersonSkill;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author msabir
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
@Slf4j
@Transactional
public class PersonRepositoryTests {
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private BusinessRepository businessRepository;
	
	private Business business;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		log.debug("Enter: setUp()**********");
		if(business==null) {
			business=Business.builder().name("Test Business").businessType(BusinessType.INSYS).description("Test Business").version(1L).build();
			business=businessRepository.saveAndFlush(business);
			log.debug("The Id is " + business.getId());
			businessRepository.findAll().stream().forEach(bus -> log.debug("Business created is " + bus.getId()));
		}
	}

	
	@Test
	public void testCreatePerson() {
		Person person=Person.builder().firstName("Omar").lastName("Sabir").personType(PersonType.Candidate).business(business).email("omar@insys.com").version(1L).build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
	}

	@Test
	public void testCreatePersonWithSkills() {
		Set<PersonSkill> personSkills=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).version(1L).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).version(1L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				.version(1L)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonSkills().forEach(personSkill -> assertNotNull(personSkill.getId()));
	}
	
	@Test
	public void testUpdatePersonWithSkills() {
		Set<PersonSkill> personSkills=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).version(1L).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).version(1L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills).version(1L)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonSkills().forEach(personSkill -> assertNotNull(personSkill.getId()));
		
		PersonSkill skill=PersonSkill.builder().name("AWS").scale(8).version(1L).build();
		person.getPersonSkills().add(skill);
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		person.getPersonSkills().forEach(personSkill -> {
			assertNotNull(personSkill.getId());
			log.debug("Skill is " + personSkill.toString());
		});
	}

	@Test
	public void testDeletePersonSkills() {
		Set<PersonSkill> personSkills=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).version(1L).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).version(1L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				.version(1L)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonSkills().forEach(personSkill -> {assertNotNull(personSkill.getId());log.debug("Before Skill Delete " + personSkill.toString());});

		PersonSkill skillToDelete=person.getPersonSkills().stream().findFirst().get();
		log.debug("Deleting Skill = " + skillToDelete.toString());
		person.getPersonSkills().remove(skillToDelete);
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		personSkills.forEach(personSkill -> log.debug("After Skill Delete " + personSkill.toString()));

		assertEquals(0, 
				person.getPersonSkills().stream()
					.filter(skill -> skill.getId().equals(skillToDelete.getId()))
					.collect(Collectors.toList()).size()
			);
	}

	@Test
	public void testCreatePersonWithDocuments() {
		Set<PersonDocument> personDocuments=new HashSet<>();
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments)
				.version(1L)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> assertNotNull(personDocument.getId()));
	}

	@Test
	public void testUpdatePersonWithDocuments() {
		Set<PersonDocument> personDocuments=new HashSet<>();
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments).version(1L)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> assertNotNull(personDocument.getId()));
		
		PersonDocument document=PersonDocument.builder().fileName("skills.doc").document("This is skills document".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build();
		person.getPersonDocuments().add(document);
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> {
			assertNotNull(personDocument.getId());
			log.debug("Document is " + personDocument.toString());
		});
	}
	
	@Test
	public void testDeletePersonDocuments() {
		Set<PersonDocument> personDocuments=new HashSet<>();
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		personDocuments.add(PersonDocument.builder().fileName("skills.doc").document("This is skills document".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());

		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments).version(1L)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> {assertNotNull(personDocument.getId());log.debug("Before Document Delete " + personDocument.toString());});
		
		PersonDocument documentToDelete=person.getPersonDocuments().stream().findFirst().get();
		log.debug("Deleting document = " + documentToDelete.toString());
		person.getPersonDocuments().remove(documentToDelete);
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		personDocuments.forEach(personDocument -> log.debug("After Document Delete " + personDocument.toString()));
		
		assertEquals(0, 
			person.getPersonDocuments().stream()
				.filter(document -> document.getId().equals(documentToDelete.getId()))
				.collect(Collectors.toList()).size()
		);
	}
	
	@Test
	public void testDeleteAllPersonDocuments() {
		Set<PersonDocument> personDocuments=new HashSet<>();
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		personDocuments.add(PersonDocument.builder().fileName("skills.doc").document("This is skills document".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());

		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments).version(1L)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> {assertNotNull(personDocument.getId());log.debug("Before Document Delete " + personDocument.toString());});
		
		log.debug("Deleting all documents = ");
		person.getPersonDocuments().clear();
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		assertEquals(0, person.getPersonDocuments().size());
	}
	
	@Test
	public void testUpdatePersonDocuments() {
		Set<PersonDocument> personDocuments=new HashSet<>();
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).version(1L).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("skills.doc").document("This is skills document".getBytes()).uploadTimestamp(new Date()).version(1L).fileSize(100L).build());

		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments).version(1L)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> {assertNotNull(personDocument.getId());log.debug("Before Document Update " + personDocument.toString());});
		
		log.debug("Deleting all documents = ");
		person.getPersonDocuments().clear();
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		assertEquals(0, person.getPersonDocuments().size());
		
		person.getPersonDocuments().add(PersonDocument.builder().fileName("resume-update.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).version(1L).build());
		repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		assertEquals(1, person.getPersonDocuments().size());
	}

	@Test
	public void testCreatePersonWithAddress() {
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.address(Address.builder().address1("3 Gerson Rd").city("Robbinsville").zipCode("08691").state("NJ").country("USA").version(1L).build())
				.version(1L).build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		assertNotNull(person.getAddress().getId());
	}
	
	@Test
	public void testUpdatePersonWithAddress() {
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.address(Address.builder().address1("3 Gerson Rd").city("Robbinsville").zipCode("08691").state("NJ").country("USA").version(1L).build())
				.version(1L).build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		assertNotNull(person.getAddress().getId());
		
		person=repository.getOne(person.getId());
		person.getAddress().setAddress2("#201");
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		assertEquals("#201", person.getAddress().getAddress2());
	}
	
	@Test
	public void testDeletePersonAddress() {
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.address(Address.builder().address1("3 Gerson Rd").city("Robbinsville").zipCode("08691").state("NJ").country("USA").version(1L).build())
				.version(1L).build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		assertNotNull(person.getAddress().getId());
		
		person=repository.getOne(person.getId());
		person.setAddress(null);
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		assertNull(person.getAddress());
	}
}



