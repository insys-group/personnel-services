package com.insys.trapps.repository.interview;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.insys.trapps.TrappsApiApplication;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.RoleRepository;
import com.insys.trapps.respositories.interview.InterviewRepository;
import com.insys.trapps.util.RoleBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
@Transactional
public class InterviewRepositoryTests {
	@Autowired
	private InterviewRepository interviewRepo;

	@Autowired
	private BusinessRepository businessRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PersonRepository personRepo;

	private Business mockBusiness;
	private Role mockRole;
	private Person mockCandidate;

	@Before
	public void init() {
		mockBusiness = initBusiness();
		businessRepo.saveAndFlush(mockBusiness);

		mockRole = initRole();
		roleRepo.saveAndFlush(mockRole);

		mockCandidate = initPerson();
		personRepo.saveAndFlush(mockCandidate);
	}

	@Test
	public void testCreateInterview() {
		Interview interview = initInterview();

		Long id = interviewRepo.saveAndFlush(interview).getId();
		Interview retrieved = interviewRepo.findOne(id);

		assertEquals(interview, retrieved);
		assertEquals(interview.getDate(), retrieved.getDate());
		assertEquals(interview.getRole(), retrieved.getRole());
		assertEquals(interview.getCandidate(), retrieved.getCandidate());
	}

	@Test
	public void testUpdateInterview() {
		Interview interview = initInterview();
		Long id = interviewRepo.saveAndFlush(interview).getId();

		Role newRole = RoleBuilder.buildRole("New Role").build();
		roleRepo.saveAndFlush(newRole);

		Interview updateInterview = initInterview();
		updateInterview.setId(id);
		updateInterview.setRole(newRole);

		interviewRepo.saveAndFlush(updateInterview);

		Interview retrieved = interviewRepo.findOne(id);
		assertEquals(retrieved.getRole(), newRole);
	}

	@Test
	public void testDeleteInterview() {
		Interview interview = initInterview();

		Long id = interviewRepo.saveAndFlush(interview).getId();

		interviewRepo.delete(interview);

		assertEquals(interviewRepo.findOne(id), null);
	}

	private Interview initInterview() {
		return Interview.builder().candidate(mockCandidate).date(new Date()).role(mockRole).build();
	}

	private Business initBusiness() {
		return Business.builder().name("Test Business").businessType(BusinessType.Pivotal)
				.description("Test Description").build();
	}

	private Role initRole() {
		return RoleBuilder.buildRole("Swift Developer II").build();
	}

	private Person initPerson() {
		return Person.builder().firstName("Hung").lastName("Do").personType(PersonType.Candidate).business(mockBusiness)
				.email("hdo@insys.com").build();
	}
}
