package world.deslauriers.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import world.deslauriers.domain.Task;

public interface TaskRepository {
	
	List<Task> findAll();
	
	Optional<Task> findById(Long id);

	Optional<Task> findByName(String name);

	List<Task> findByInterval(LocalDate start, LocalDate end);

	int updateCompleteById(@NotNull Boolean completeness, @NotNull Long id);

	int updateQualityById(@NotNull Boolean quality, @NotNull Long id);

	void save(Task task);

}
