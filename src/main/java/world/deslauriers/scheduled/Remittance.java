package world.deslauriers.scheduled;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import world.deslauriers.service.AllowanceService;
import world.deslauriers.service.TaskService;

@Singleton
public class Remittance {

	@Inject
	private final AllowanceService allowanceService;
	
	@Inject
	private final TaskService taskService;

	public Remittance(AllowanceService allowanceService, TaskService taskService) {
		this.allowanceService = allowanceService;
		this.taskService = taskService;
	}

	@Scheduled(cron = "0 0 6 ? * 1")
	void updateAllowance() {
		
		allowanceService.weeklyRemittance();
	}
	
	@Scheduled(cron = "0 0 5 * * *")
	void dailyTasks() {
		
		taskService.createDailyTasks();
	}
	
	
}
