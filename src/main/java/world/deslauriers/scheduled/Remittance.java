package world.deslauriers.scheduled;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.micronaut.context.env.Environment;
import io.micronaut.scheduling.annotation.Scheduled;
import world.deslauriers.service.AllowanceService;
import world.deslauriers.service.TaskService;

@Singleton
public class Remittance {

	@Inject
	private final AllowanceService allowanceService;
	
	@Inject
	private final TaskService taskService;
	
	@Inject
	private Environment environment;

	public Remittance(AllowanceService allowanceService, TaskService taskService, Environment environment) {
		this.allowanceService = allowanceService;
		this.taskService = taskService;
		this.environment = environment;
	}

	@Scheduled(cron = "0 0 5 ? * 1")
	void updateAllowance() {
		
		String name = environment
				.getProperty("allowance.indexZero", String.class)
				.get();
		
		allowanceService.weeklyRemittance(name);
	}
	
	@Scheduled(cron = "0 0 6 ? * *")
	void dailyTasks() {
		
		taskService.createDailyTasks();
	}
}
