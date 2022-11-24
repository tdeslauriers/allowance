package world.deslauriers.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import world.deslauriers.domain.Allowance;
import world.deslauriers.service.AllowanceService;

import java.net.URI;
import java.util.List;


@ExecuteOn(TaskExecutors.IO)
@Controller("/allowance")
public class AllowanceController {
	
	protected final AllowanceService allowanceSerivice;

	public AllowanceController(AllowanceService allowanceSerivice) {
		this.allowanceSerivice = allowanceSerivice;
	}

	@Get("/all")
	public List<Allowance> showAllAllowances(){
		
		return allowanceSerivice.findAll();
	}
	
	protected URI location(String firstname) {
		
		return URI.create("/allowance/" + firstname);
	}
}
