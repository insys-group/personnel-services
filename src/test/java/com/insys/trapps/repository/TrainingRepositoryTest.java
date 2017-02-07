package com.insys.trapps.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.insys.trapps.TrappsApiApplication;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.model.ProgressType;
import com.insys.trapps.model.Training;
import com.insys.trapps.model.TrainingTask;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.TrainingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
@Transactional
public class TrainingRepositoryTest {

	@Autowired
	private TrainingRepository repository;

	@Autowired
	private BusinessRepository businessRepository;

	private Person person;

	private Business business;

	@Before
	public void init() {
		business = initBusiness();
		person = initPerson();
		businessRepository.saveAndFlush(business);
	}

	@Test
	public void testCreateTraining() {
		Training training = initTraining();

		Long id = repository.saveAndFlush(training).getId();
		Training savedTraining = repository.findOne(id);

		assertThat(savedTraining, equalTo(training));
		checkFieldsNotIncludedInEquals(training, savedTraining);
	}

	@Test
	public void testDeleteTraining() {
		Training training = initTraining();

		Long id = repository.saveAndFlush(training).getId();
		repository.delete(training);

		assertThat(repository.findOne(id), nullValue());
	}

	@Test
	public void testUpdateTraining() {
		Training training = initTraining();
		Long id = repository.saveAndFlush(training).getId();

		Training updatedTraining = initTraining();
		updatedTraining.setId(id);
		String differentName = "Different Name";
		updatedTraining.setName(differentName);
		repository.saveAndFlush(updatedTraining);

		assertThat(repository.findOne(id).getName(), equalTo(differentName));
	}

	private void checkFieldsNotIncludedInEquals(Training training, Training savedTraining) {
		assertThat(savedTraining.getTrainees(), hasSize(1));
		assertThat(savedTraining.getTrainees(), hasItems(person));
		assertThat(savedTraining.getProgress(), equalTo(training.getProgress()));
		assertThat(savedTraining.getEndDate(), equalTo(training.getEndDate()));
		assertThat(savedTraining.getTasks(), hasSize(2));
	}

	private Training initTraining() {
		return Training.builder()
				.name("Test Training")
				.trainees(initTrainees())
				.progress(ProgressType.NOT_STARTED)
				.location(initAddress())
				.online(true)
				.startDate(new Date(LocalDate.of(2017, Month.JANUARY, 25).toEpochDay()))
				.endDate(new Date(LocalDate.of(2017, Month.JANUARY, 31).toEpochDay()))
				.tasks(initTasks())
				.build();
	}

	private Set<TrainingTask> initTasks() {
		return new HashSet<>(Arrays.asList(
				TrainingTask.builder().name("Test Task 1").weblink("http://training.org/1").build(),
				TrainingTask.builder().name("Test Task 2").weblink("http://training.org/2").build()
				));
	}

	private Address initAddress() {
		return Address.builder()
				.address1("395 W Passaic St")
				.city("Rochelle Park")
				.zipCode("07662")
				.state("NJ")
				.country("USA")
				.build();
	}

	private Person initPerson() {
		return Person.builder()
				.firstName("Mike")
				.lastName("Tian")
				.personType(PersonType.Candidate)
				.business(business)
				.email("mtian@insys.com")
				.build();
	}

	private Business initBusiness() {
		return Business.builder()
				.name("Test Business")
				.businessType(BusinessType.INSYS)
				.description("Test Business")
				.build();
	}

	private Set<Person> initTrainees() {
		Set<Person> trainees = new HashSet<>();
		trainees.add(person);
		return trainees;
	}

}
