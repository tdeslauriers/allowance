package world.deslauriers.controller;

import java.net.URI;
import java.util.List;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.AllArgsConstructor;
import world.deslauriers.domain.Allowance;
import world.deslauriers.service.AllowanceService;

@AllArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller("/api/v1/allowance")
public class AllowanceController {
	
	protected final AllowanceService allowanceSerivice;
	
	@Get("/all")
	public List<Allowance> showAllAllowances(){
		
		return allowanceSerivice.findAll();
	}
	
	protected URI location(String firstname) {
		
		return URI.create("/api/v1/allowance/" + firstname);
	}
}
