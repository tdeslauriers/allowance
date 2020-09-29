package world.deslauriers.service;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class TaskServiceTest {
	
	@Inject
	private TaskService tserv;
	
	@Test
	public void testCreateTaskService() {
		
		tserv.createDailyTasks();
	}
}
