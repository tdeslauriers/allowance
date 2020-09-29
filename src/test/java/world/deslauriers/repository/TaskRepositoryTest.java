package world.deslauriers.repository;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;
import world.deslauriers.domain.Task;

@MicronautTest
public class TaskRepositoryTest {

	@Inject
	private TaskRepository tdao;
	
	@Test
	public void testTaskCRUD() {
	
		List<Task> all = tdao.findAll();
		for (Task t: all) {
			System.out.println(t.toString());
		}
	}
}
