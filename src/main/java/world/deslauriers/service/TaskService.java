package world.deslauriers.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import world.deslauriers.domain.Task;

public interface TaskService {

	void updateIsCompleteById(@NotNull Boolean isComplete, @NotNull Long id);
	void updateIsQualityById(@NotNull Boolean isQuality, @NotNull Long id);
	void createDailyTasks();
	List<Task> findWeeklyTasks(LocalDate start, LocalDate end);
}
