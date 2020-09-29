package world.deslauriers.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.TaskIntervalDto;

public interface AllowanceRepository {


	Optional<Allowance> findById(Long id);
	List<Allowance> findAll();
	List<Allowance> findAllTableData();
	List<DailyTasksDto> findDailyTasks();
	void save(Allowance allowance);
	List<TaskIntervalDto> findTasksByInterval(LocalDate start, LocalDate end);
	int updateBalance(@NotNull Double amount, @NotNull Long id);
}
