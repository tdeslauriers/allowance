package world.deslauriers.repository;

import java.util.List;

import world.deslauriers.domain.TaskType;

public interface TaskTypeRepository {

	List<TaskType> findAll();

}
