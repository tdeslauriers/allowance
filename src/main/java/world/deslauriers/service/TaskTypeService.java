package world.deslauriers.service;

import java.util.List;
import java.util.Optional;

import world.deslauriers.domain.TaskType;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;

public interface TaskTypeService {

	List<TaskType> findAll();
	void save(TaskType taskType);
	List<TaskTypeCadenceDto> findByCadence(String cadence);
	Optional<TaskType> findById(Long id);
}
