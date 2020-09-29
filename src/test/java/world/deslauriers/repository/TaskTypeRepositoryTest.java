package world.deslauriers.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;
import world.deslauriers.domain.Cadence;
import world.deslauriers.domain.TaskType;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;

@MicronautTest
public class TaskTypeRepositoryTest {

	@Inject TaskTypeRepository ttdao;
	
	@Test
	public void testTasktypeRepositoryCRUD() {
		
		List<TaskType> all = ttdao.findAll();
		System.out.println(all.toString());
		
		List<TaskTypeCadenceDto> daily = 
				ttdao.findTasktypesByCadence(Cadence.DAILY.getCadence());
		System.out.println(daily);
		
		Long id = Long.valueOf(1);
		Optional<TaskType> tt = ttdao.findById(Long.valueOf(id));
		assertEquals(id, tt.get().getId());
	}
}
