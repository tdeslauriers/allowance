package world.deslauriers.repository;

import java.util.List;
import java.util.Optional;

import world.deslauriers.domain.TaskType;

public interface TaskTypeRepository {

	List<TaskType> findAll();
	Optional<TaskType> save(TaskType taskType);
}
