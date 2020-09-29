package world.deslauriers.repository;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;
import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.TaskIntervalDto;

@MicronautTest
public class AllowanceRepositoryTest {
	
	@Inject
	private AllowanceRepository adao;
	
	@Test
	public void testAllowanceCRUD() {
		
		List<Allowance> all = adao.findAll();
		System.out.println(all.toString());
		for (Allowance a: all) {
			System.out.println(a.toString());
		}
		
		List<Allowance> all0 = adao.findAllTableData();
		System.out.println(all0.toString());
		for (Allowance a: all0) {
			System.out.println(a.toString());
		}
		
		List<DailyTasksDto> daily = adao.findDailyTasks();
		System.out.println(daily.toString());
		for (DailyTasksDto a: daily) {
			System.out.println(a.toString());
		}
		
		
		LocalDate end = LocalDate.now();
		LocalDate start = end.minusDays(7);
		List<TaskIntervalDto> lastweek = 
				adao.findTasksByInterval(start, end);
		System.out.println(lastweek);
	}
}
