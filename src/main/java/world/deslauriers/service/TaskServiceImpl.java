package world.deslauriers.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import javax.validation.constraints.NotNull;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.Cadence;
import world.deslauriers.domain.Task;
import world.deslauriers.domain.TaskType;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;
import world.deslauriers.repository.TaskRepository;

@Singleton
public class TaskServiceImpl implements TaskService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Inject
	private TaskRepository taskDao;
	
	@Inject 
	private AllowanceService allowanceService;
	
	@Inject 
	private TaskTypeService taskTypeService;
	
	@Override
	public List<Task> findAll(){
		
		return taskDao.findAll();
	}

	@Override
	public void updateIsCompleteById(@NotNull Boolean isComplete, @NotNull Long id) {
		
		int taskIsComplete = taskDao.updateIsCompleteById(isComplete, id);
		
		logger.info(taskIsComplete + " task(s) had their isComplete status updated.");
	}
	
	@Override
	public void updateIsQualityById(@NotNull Boolean isQuality, @NotNull Long id) {
		
		int taskIsQuality = taskDao.updateIsQualityById(isQuality, id);
		
		logger.info(taskIsQuality + " task(s) had their isQuality status updated.");
	}
	
	@Override
	public void createDailyTasks() {
		
		var daily=
				taskTypeService.findByCadence(Cadence.DAILY.getCadence());
		
		for(var ttc: daily) {
			
			var t = new Task();
			t.setDate(LocalDate.now());
			t.setIsComplete(false);
			t.setIsQuality(true);
			
			var tt = taskTypeService.findById(ttc.tasktypeId());
			
			var a = allowanceService.findById(ttc.allowanceId());

			
			taskDao.save(t);
		}
	}

}
