package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import world.deslauriers.domain.TaskType;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;
import world.deslauriers.repository.TaskTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Singleton
public class TaskTypeServiceImpl implements TaskTypeService {
	
	@Inject
	private TaskTypeRepository taskTypeDao;
	
	@Override
	public List<TaskType> findAll(){
		
		return new ArrayList<>(taskTypeDao.findAll());
	}
	
	@Override
	public void save(TaskType taskType) {
		
		taskTypeDao.save(taskType);
	}
	
	@Override
	public List<TaskTypeCadenceDto> findByCadence(String cadence){
		
		return taskTypeDao.findTasktypesByCadence(cadence);
	}
	
	@Override
	public Optional<TaskType> findById(Long id){
		
		return taskTypeDao.findById(id);
	}
}
