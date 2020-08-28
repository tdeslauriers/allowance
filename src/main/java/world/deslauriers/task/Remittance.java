package world.deslauriers.task;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.micronaut.context.env.Environment;
import io.micronaut.scheduling.annotation.Scheduled;
import world.deslauriers.service.AllowanceService;

@Singleton
public class Remittance {

	@Inject
	private final AllowanceService allowanceService;
	
	@Inject
	private Environment environment;

	public Remittance(AllowanceService allowanceService, Environment environment) {
		this.allowanceService = allowanceService;
		this.environment = environment;
	}

	@Scheduled(cron = "0 0 6 ? * 1")
	void updateAllowance() {
		
		String name = environment
				.getProperty("allowance.indexZero", String.class)
				.get();
		
		allowanceService.weeklyRemittance(name);
	}
	
	
}
