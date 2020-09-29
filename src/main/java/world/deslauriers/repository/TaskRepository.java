package world.deslauriers.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import world.deslauriers.domain.Task;

public interface TaskRepository {
	
	List<Task> findAll();
	
	Optional<Task> save(Task task);
	int updateIsCompleteById(@NotNull Boolean isComplete, @NotNull Long id);
	int updateIsQualityById(@NotNull Boolean isQuality, @NotNull Long id);
}
