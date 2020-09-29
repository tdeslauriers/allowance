package world.deslauriers.repository;

import java.util.List;
import java.util.Optional;

import world.deslauriers.domain.TaskType;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;

public interface TaskTypeRepository {

	List<TaskType> findAll();
	Optional<TaskType> save(TaskType taskType);
	List<TaskTypeCadenceDto> findTasktypesByCadence(String cadence);
	Optional<TaskType> findById(Long id);
}
