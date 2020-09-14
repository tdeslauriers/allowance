package world.deslauriers.scheduled;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.micronaut.scheduling.annotation.Scheduled;
import lombok.AllArgsConstructor;
import world.deslauriers.service.AllowanceService;
import world.deslauriers.service.TaskService;

@AllArgsConstructor
@Singleton
public class Remittance {

	@Inject
	private final AllowanceService allowanceService;
	
	@Inject
	private final TaskService taskService;

	@Scheduled(cron = "0 0 6 ? * 1")
	void updateAllowance() {
		
		allowanceService.weeklyRemittance();
	}
	
	@Scheduled(cron = "0 0 5 ? * *")
	void dailyTasks() {
		
		taskService.createDailyTasks();
	}
}
