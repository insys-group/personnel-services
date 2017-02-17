/**
 * 
 */
package com.insys.trapps.repository;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
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
import com.insys.trapps.model.PersonTraining;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.model.ProgressType;
import com.insys.trapps.model.Training;
import com.insys.trapps.model.TrainingTask;
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
			business=Business.builder().name("Test Business").businessType(BusinessType.INSYS).description("Test Business").build();
			business=businessRepository.saveAndFlush(business);
			log.debug("The Id is " + business.getId());
			businessRepository.findAll().stream().forEach(bus -> log.debug("Business created is " + bus.getId()));
		}
	}

	
	@Test
	public void testCreatePerson() {
		Person person=Person.builder().firstName("Omar").lastName("Sabir").personType(PersonType.Candidate).business(business).email("omar@insys.com").build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
	}

	@Test
	public void testCreatePersonWithSkills() {
		Set<PersonSkill> personSkills=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonSkills().forEach(personSkill -> assertNotNull(personSkill.getId()));
	}
	
	@Test
	public void testUpdatePersonWithSkills() {
		Set<PersonSkill> personSkills=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonSkills().forEach(personSkill -> assertNotNull(personSkill.getId()));
		
		PersonSkill skill=PersonSkill.builder().name("AWS").scale(8).build();
		person.getPersonSkills().add(skill);
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		person.getPersonSkills().forEach(personSkill -> {
			assertNotNull(personSkill.getId());
			log.debug("Skill is " + personSkill.toString());
		});
	}
	
	@Test
	public void testUpdatePersonWithTraining() {
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.build();
		PersonTraining personTraining = initPersonTraining(person,initTraining("Test Training"));
		person.setPersonTrainings(singletonList(personTraining));
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		
		PersonTraining anotherPersonTraining = initPersonTraining(person, initTraining("Another Training"));
		person.getPersonTrainings().add(anotherPersonTraining);
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		assertThat(assoicatedTrainingNames(person), hasItems("Test Training", "Another Training"));
	}

	@Test
	public void testDeletePersonSkills() {
		Set<PersonSkill> personSkills=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				
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
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments)
				
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> assertNotNull(personDocument.getId()));
	}

	@Test
	public void testCreatePersonWithSkillsAndDocuments() {
		Set<PersonSkill> personSkills=new HashSet<>();
		Set<PersonDocument> personDocuments=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				.personDocuments(personDocuments)
				
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> assertNotNull(personDocument.getId()));
		person.getPersonSkills().forEach(personSkill -> assertNotNull(personSkill.getId()));
	}
	
	@Test
	public void testDeletePersonWithSkillsAndDocuments() {
		Set<PersonSkill> personSkills=new HashSet<>();
		Set<PersonDocument> personDocuments=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				.personDocuments(personDocuments)
				
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> assertNotNull(personDocument.getId()));
		person.getPersonSkills().forEach(personSkill -> assertNotNull(personSkill.getId()));
		repository.delete(person.getId());
		Person deletedPerson=repository.findOne(person.getId());
		assertNull(deletedPerson);
	}

	@Test
	public void testUpdatePersonWithDocuments() {
		Set<PersonDocument> personDocuments=new HashSet<>();
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments)
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		person=repository.getOne(person.getId());
		person.getPersonDocuments().forEach(personDocument -> assertNotNull(personDocument.getId()));
		
		PersonDocument document=PersonDocument.builder().fileName("skills.doc").document("This is skills document".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build();
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
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("skills.doc").document("This is skills document".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());

		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments)
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
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("skills.doc").document("This is skills document".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());

		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments)
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
		personDocuments.add(PersonDocument.builder().fileName("resume.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("profile.doc").document("This is profile".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		personDocuments.add(PersonDocument.builder().fileName("skills.doc").document("This is skills document".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());

		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personDocuments(personDocuments)
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
		
		person.getPersonDocuments().add(PersonDocument.builder().fileName("resume-update.doc").document("This is resume".getBytes()).uploadTimestamp(new Date()).fileSize(100L).build());
		repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		assertEquals(1, person.getPersonDocuments().size());
	}

	@Test
	public void testCreatePersonWithAddress() {
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.address(Address.builder().address1("3 Gerson Rd").city("Robbinsville").zipCode("08691").state("NJ").country("USA").build())
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		assertNotNull(person.getAddress().getId());
	}
	
	@Test
	public void testUpdatePersonWithAddress() {
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.address(Address.builder().address1("3 Gerson Rd").city("Robbinsville").zipCode("08691").state("NJ").country("USA").build())
				.build();
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
				.address(Address.builder().address1("3 Gerson Rd").city("Robbinsville").zipCode("08691").state("NJ").country("USA").build())
				.build();
		person=repository.saveAndFlush(person);
		assertNotNull(person.getId());
		assertNotNull(person.getAddress().getId());
		
		person=repository.getOne(person.getId());
		person.setAddress(null);
		person=repository.saveAndFlush(person);
		
		person=repository.getOne(person.getId());
		assertNull(person.getAddress());
	}

	private List<String> assoicatedTrainingNames(Person person) {
		return person.getPersonTrainings().stream().map(PersonTraining::getTraining).map(Training::getName).collect(Collectors.toList());
	}

	private PersonTraining initPersonTraining(Person person, Training training) {
		return PersonTraining.builder()
				.progress(ProgressType.IN_PROGRESS)
				.startDate(LocalDate.of(2017, Month.JANUARY, 25).toEpochDay())
				.endDate(LocalDate.of(2017, Month.JANUARY, 30).toEpochDay())
				.training(training).person(person).build();
	}

	private Training initTraining(String name) {
		Training training = Training.builder()
				.name(name)
				.online(true)
				.build();
		training.setTasks(initTasks(training,"Test Task 1", "Test Task 2"));
		return training;
	}

	private Set<TrainingTask> initTasks(Training training, String... taskNames) {
		return Arrays.stream(taskNames)
				.map(name -> TrainingTask.builder().name(name).build())
				.collect(Collectors.toSet());
	}
}



