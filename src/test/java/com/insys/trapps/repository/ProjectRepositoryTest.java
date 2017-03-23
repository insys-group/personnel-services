package com.insys.trapps.repository;

import com.insys.trapps.TrappsApiApplication;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.Project;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonType;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.OpportunityRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
@Transactional
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;

	@Autowired
	private OpportunityRepository opportunityRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BusinessRepository businessRepository;

	private Business business;

	private Person person;

	private Opportunity opportunity;

	@Before
	public void initOpportunity() {
		business=Business.builder().name("Test Business").businessType(BusinessType.INSYS).description("Test Business").build();
		business=businessRepository.saveAndFlush(business);
		person = Person.builder().firstName("Mike").lastName("Tian").personType(PersonType.Candidate).business(business).email("omar@insys.com").build();
		person = personRepository.saveAndFlush(person);
		opportunity = Opportunity.builder().name("New opportunity").comments("Comments").build();
		opportunity = opportunityRepository.saveAndFlush(opportunity);
	}

	@Test
	public void testCreateProject() {
		Project project = initProject();

		Long id = repository.saveAndFlush(project).getId();
		Project savedProject = repository.findOne(id);

		assertThat(savedProject, equalTo(project));
		assertThat(savedProject.getComments(), equalTo(project.getComments()));
		assertThat(savedProject.getRequirements(), equalTo(project.getRequirements()));
		assertThat(savedProject.getOpportunity(), equalTo(project.getOpportunity()));
		assertThat(savedProject.getPersons(), hasSize(1));
	}

	@Test
	public void testDeleteProject() {
		Project project = initProject();

		Long id = repository.saveAndFlush(project).getId();
		repository.delete(project);

		assertThat(repository.findOne(id), nullValue());
	}

	@Test
	public void testUpdateProject() {
		Project project = initProject();
		Long id = repository.saveAndFlush(project).getId();

		Project newProject = initProject();
		newProject.setId(id);
		String differentName = "Different Name";
		newProject.setName(differentName);
		List<Person> persons = new ArrayList<>(newProject.getPersons());
		Person newPerson = Person.builder().firstName("Another Person").lastName("AnotherLastName").personType(PersonType.Candidate).business(business).email("another@email.com").build();
		newPerson = personRepository.saveAndFlush(newPerson);
		persons.add(newPerson);
		newProject.setPersons(persons);

		repository.saveAndFlush(newProject);

		Project updatedProject = repository.findOne(id);
		assertThat(updatedProject.getName(), equalTo(differentName));
		assertThat(updatedProject.getPersons(), hasSize(2));
	}
	
	private Project initProject() {
		return Project.builder()
				.name("Test Project")
				.comments("Comments")
				.opportunity(opportunity)
				.persons(new ArrayList<>(Collections.singletonList(person)))
				.requirements("Some requirements")
				.build();
	}


}
