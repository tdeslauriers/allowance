package world.deslauriers.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import world.deslauriers.domain.Task;

public interface TaskService {

	List<Task> findAll();
	void updateIsCompleteById(@NotNull Boolean isComplete, @NotNull Long id);
	void updateIsQualityById(@NotNull Boolean isQuality, @NotNull Long id);
	void createDailyTasks();
}
