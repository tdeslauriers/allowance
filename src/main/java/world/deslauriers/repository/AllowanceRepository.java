package world.deslauriers.repository;

import java.util.List;
import java.util.Optional;

import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;

public interface AllowanceRepository {

	Optional<Allowance> findByFirstName(String firstname);
	List<Allowance> findAll();
	int update(Double amount, String firstname);
	Optional<Allowance> save(Allowance allowance);
	List<DailyTasksDto> findDaily();
	List<TaskTypeCadenceDto> findAllowanceDailyTaskTypes();
}
