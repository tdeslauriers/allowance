package world.deslauriers.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.Cadence;
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
		
		List<TaskTypeCadenceDto> daily= 
				taskTypeService.findByCadence(Cadence.DAILY.getCadence());
		
		for(TaskTypeCadenceDto ttc: daily) {
			
			Task t = new Task();
			t.setDate(LocalDate.now());
			t.setIsComplete(false);
			t.setIsQuality(true);
			
			Optional<TaskType> tt = taskTypeService.findById(ttc.getTasktypeId());
			t.setTasktype(tt.get());
			
			Optional<Allowance> a = allowanceService.findById(ttc.getAllowanceId());
			t.setAllowance(a.get());
			
			taskDao.save(t);
		}
	}

}
