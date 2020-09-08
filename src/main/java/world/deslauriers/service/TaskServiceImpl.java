package world.deslauriers.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.Task;
import world.deslauriers.domain.TaskType;
import world.deslauriers.repository.TaskRepository;

@Singleton
public class TaskServiceImpl implements TaskService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Inject
	private TaskRepository taskDao;
	
	@Inject 
	private AllowanceService allowanceService;
	
	public TaskServiceImpl(TaskRepository taskDao, AllowanceService allowanceService) {
		this.taskDao = taskDao;
		this.allowanceService = allowanceService;
	}

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
		
		List<Allowance> allowances = allowanceService.findAll();
		for(Allowance a : allowances) {
			
			Set<TaskType> tasktypes = a.getTasktype();
			for(TaskType t: tasktypes) {
				
				Task task = new Task();
				task.setIsComplete(false);
				task.setIsQuality(true);
				task.setDate(LocalDate.now());
				task.setAllowance(a);
				task.setTasktype(t);
				
				taskDao.save(task);
			}
		}
	}
}
