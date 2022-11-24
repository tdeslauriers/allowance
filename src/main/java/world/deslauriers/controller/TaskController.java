package world.deslauriers.controller;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.QualityCompleteUpdateCommand;
import world.deslauriers.service.AllowanceService;
import world.deslauriers.service.TaskService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/tasks")
public class TaskController {
	
	protected final TaskService taskService;
	
	protected final AllowanceService allowanceSerivce;

	public TaskController(TaskService taskService, AllowanceService allowanceSerivce) {
		this.taskService = taskService;
		this.allowanceSerivce = allowanceSerivce;
	}

	@Get("/daily")
	public List<DailyTasksDto> showDailyTasks(){
		
		return allowanceSerivce.findDaily();
	}
	
	@Put("/quality")
	public HttpResponse<?> updateIsQuality(@Body @Valid QualityCompleteUpdateCommand cmd){
		
		taskService.updateIsQualityById(cmd.updateStatus(), cmd.taskId());
		
		return HttpResponse
				.noContent()
				.header(HttpHeaders.LOCATION, location(cmd.taskId()).getPath());
	}
	
	@Put("/complete")
	public HttpResponse<?> updateIsComplete(@Body @Valid QualityCompleteUpdateCommand cmd){
		
		taskService.updateIsCompleteById(cmd.updateStatus(), cmd.taskId());
		
		return HttpResponse
				.noContent()
				.header(HttpHeaders.LOCATION, location(cmd.taskId()).getPath());
	}
	
	protected URI location(Long taskId) {
		
		return URI.create("/tasks/" + taskId);
	}
}
