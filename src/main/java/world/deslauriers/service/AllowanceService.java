package world.deslauriers.service;

import java.util.List;
import java.util.Optional;

import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.dto.DailyTasksDto;

public interface AllowanceService {

	void decrementBy2(String firstname);
	Optional<Allowance> findByFirstname(String firstname);
	List<Allowance> findAll();
	void weeklyRemittance();
	List<DailyTasksDto> findDaily();

}
