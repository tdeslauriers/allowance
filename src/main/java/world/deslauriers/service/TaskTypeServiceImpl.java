package world.deslauriers.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import world.deslauriers.domain.TaskType;
import world.deslauriers.repository.TaskTypeRepository;

@Singleton
public class TaskTypeServiceImpl implements TaskTypeService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskTypeServiceImpl.class);
	
	@Inject
	private TaskTypeRepository taskTypeDao;

	public TaskTypeServiceImpl(TaskTypeRepository taskTypeDao) {
		this.taskTypeDao = taskTypeDao;
	}
	
	@Override
	public List<TaskType> findAll(){
		
		return new ArrayList<TaskType>(taskTypeDao.findAll());
	}
}
