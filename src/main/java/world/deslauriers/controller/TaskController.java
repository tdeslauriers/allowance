package world.deslauriers.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.AllArgsConstructor;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.QualityCompleteUpdateCommand;
import world.deslauriers.service.AllowanceService;
import world.deslauriers.service.TaskService;

@AllArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller("/api/v1/task")
public class TaskController {
	
	protected final TaskService taskService;
	
	protected final AllowanceService allowanceSerivce;
	
	@Get("/daily")
	public List<DailyTasksDto> showDailyTasks(){
		
		return allowanceSerivce.findDaily();
	}
	
	@Put("/quality")
	public HttpResponse<?> updateIsQuality(@Body @Valid QualityCompleteUpdateCommand cmd){
		
		taskService.updateIsQualityById(cmd.getUpdateStatus(), cmd.getTaskId());
		
		return HttpResponse
				.noContent()
				.header(HttpHeaders.LOCATION, location(cmd.getTaskId()).getPath());
	}
	
	@Put("/complete")
	public HttpResponse<?> updateIsComplete(@Body @Valid QualityCompleteUpdateCommand cmd){
		
		taskService.updateIsCompleteById(cmd.getUpdateStatus(), cmd.getTaskId());
		
		return HttpResponse
				.noContent()
				.header(HttpHeaders.LOCATION, location(cmd.getTaskId()).getPath());
	}
	
	protected URI location(Long taskId) {
		
		return URI.create("/api/v1/task/" + taskId);
	}
}
