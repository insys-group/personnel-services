package com.insys.trapps.repository;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.insys.trapps.TrappsApiApplication;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Training;
import com.insys.trapps.model.TrainingTask;
import com.insys.trapps.respositories.TrainingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
@Transactional
public class TrainingRepositoryTest {

	@Autowired
	private TrainingRepository repository;

	@Test
	public void TrainingRepositoryTesttestCreateTraining() {
		Training training = initTraining();

		Long id = repository.saveAndFlush(training).getId();
		Training savedTraining = repository.findOne(id);

		assertThat(savedTraining, equalTo(training));
		assertThat(savedTraining.getLocation(), equalTo(training.getLocation()));
		assertThat(savedTraining.isOnline(), equalTo(training.isOnline()));
		assertThat(savedTraining.getTasks(), hasSize(2));
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
		List<TrainingTask> tasks = initTasks(1,2);
		Training training = initTraining(tasks);
		Long id = repository.saveAndFlush(training).getId();
		
		Training newTraining = initTraining(tasks);
		newTraining.setId(id);
		String differentName = "Different Name";
		newTraining.setName(differentName);
		newTraining.getTasks().remove(tasks.get(0));
		newTraining.getTasks().add(createTask(3));
		repository.saveAndFlush(newTraining);
		
		Training updatedTraining = repository.findOne(id);
		assertThat(updatedTraining.getName(), equalTo(differentName));
		assertThat(updatedTraining.getTasks(), hasSize(2));
		assertThat(getTaskNames(updatedTraining), hasItems("Test Task 2", "Test Task 3"));
	}

	private List<String> getTaskNames(Training updatedTraining) {
		return updatedTraining.getTasks().stream().map(TrainingTask::getName).collect(Collectors.toList());
	}
	
	private Training initTraining(){
		return initTraining(initTasks(1,2));
	}

	private Training initTraining(List<TrainingTask> tasks) {
		return Training.builder()
				.name("Test Training")
				.location(initAddress())
				.online(true)
				.tasks(new HashSet<>(tasks))
				.build();
	}

	private List<TrainingTask> initTasks(int... numbers) {
		return Arrays.stream(numbers).mapToObj(this::createTask).collect(toList());
	}
	
	private TrainingTask createTask(int number){
		return TrainingTask.builder()
				.name("Test Task " + number)
				.weblink("http://training.org/" + number)
				.description("Description " + number)
				.build();
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
}
