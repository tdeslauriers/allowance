package world.deslauriers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.AllArgsConstructor;
import world.deslauriers.domain.TaskType;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;
import world.deslauriers.repository.TaskTypeRepository;

@AllArgsConstructor
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
