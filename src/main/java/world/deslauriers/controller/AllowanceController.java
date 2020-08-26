package world.deslauriers.controller;

import java.net.URI;

import javax.validation.Valid;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import world.deslauriers.domain.Allowance;
import world.deslauriers.service.AllowanceService;
import world.deslauriers.validation.AllowanceUpdateCommand;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/v1/allowance")
public class AllowanceController {
	
	protected final AllowanceService allowanceSerivice;

	public AllowanceController(AllowanceService allowanceSerivice) {
		this.allowanceSerivice = allowanceSerivice;
	}
	
	@Get("/{firstname}")
	public Allowance showAllowance(String firstname) {
		
		return allowanceSerivice
				.findByFirstname(firstname)
				.orElse(null);
	}
	
	// remove once front end created
	// replace with encapsulated @Body @Valid AllowanceUpdateCommand request
	@Put("/{firstname}")
	public HttpResponse decrementBy2(@Body @Valid AllowanceUpdateCommand command) {
		
		allowanceSerivice.decrementBy2(command.getFirstname());
		
		return HttpResponse
				.noContent()
				.header(HttpHeaders.LOCATION, location(command.getFirstname()).getPath());
	}
	
	protected URI location(String firstname) {
		
		return URI.create("/api/v1/allowance/" + firstname);
	}
}
