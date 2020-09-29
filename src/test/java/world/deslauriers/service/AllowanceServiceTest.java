package world.deslauriers.service;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class AllowanceServiceTest {
	
	@Inject
	private AllowanceService aService;
	
	@Test
	public void testWeeklyRemittance() {
		
		aService.weeklyRemittance();
	}
}
