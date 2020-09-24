package world.deslauriers.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.Task;
import world.deslauriers.domain.TaskType;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;
import world.deslauriers.repository.TaskRepository;

@AllArgsConstructor
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
	public void updateIsCompleteById(@NotNull Boolean isComplete, @NotNull Long id) {
		
		int taskIsComplete = taskDao.updateIsCompleteById(isComplete, id);
		
		logger.info(taskIsComplete + "task(s) had their isComplete status updated.");
	}
	
	@Override
	public void updateIsQualityById(@NotNull Boolean isQuality, @NotNull Long id) {
		
		int taskIsQuality = taskDao.updateIsQualityById(isQuality, id);
		
		logger.info(taskIsQuality + "task(s) had their isQuality status updated.");
	}
	
	@Override
	public void createDailyTasks() {
		
		// because the lists of allowances and tasktypes are small
		// more efficient to call db for whole lists 
		// vs db record-lookup one at a time in task creation loop
		List<Allowance> allowances = allowanceService.findAll();
		List<TaskType> tts = taskTypeService.findAll();
		List<TaskTypeCadenceDto> daily = allowanceService.findDailyTaskTypes();

		for(TaskTypeCadenceDto t: daily) {
			
			Task task = new Task();
			
			TaskType tt = tts.stream()
					.filter(tasktype -> t.getTaskTypeName().equals(tasktype.getName()))
					.findAny().orElse(null);
			
			Allowance a = allowances.stream()
					.filter(allowance -> t.getFirstname().equals(allowance.getFirstname()))
					.findAny().orElse(null);
			
			task.setIsComplete(false);
			task.setIsQuality(true);
			task.setDate(LocalDate.now());
			task.setAllowance(a);
			task.setTasktype(tt);
			
			taskDao.save(task);
		}
		
	}
	
	@Override
	public List<Task> findWeeklyTasks(LocalDate start, LocalDate end){
		
		return new ArrayList<Task>(taskDao.findByInterval(start, end));
	}

}
