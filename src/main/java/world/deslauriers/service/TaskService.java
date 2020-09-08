package world.deslauriers.service;

import javax.validation.constraints.NotNull;

public interface TaskService {

	void updateIsCompleteById(@NotNull Boolean isComplete, @NotNull Long id);
	void updateIsQualityById(@NotNull Boolean isQuality, @NotNull Long id);
	void createDailyTasks();
}
