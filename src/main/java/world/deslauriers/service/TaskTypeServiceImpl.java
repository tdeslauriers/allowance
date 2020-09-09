package world.deslauriers.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.AllArgsConstructor;
import world.deslauriers.domain.TaskType;
import world.deslauriers.repository.TaskTypeRepository;

@AllArgsConstructor
@Singleton
public class TaskTypeServiceImpl implements TaskTypeService {
	
	@Inject
	private TaskTypeRepository taskTypeDao;
	
	@Override
	public List<TaskType> findAll(){
		
		return new ArrayList<TaskType>(taskTypeDao.findAll());
	}
	
	@Override
	public void save(TaskType taskType) {
		
		taskTypeDao.save(taskType);
	}
}
