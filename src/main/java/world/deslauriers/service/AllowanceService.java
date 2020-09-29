package world.deslauriers.service;

import java.util.List;
import java.util.Optional;

import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.dto.DailyTasksDto;

public interface AllowanceService {

	List<Allowance> findAll();
	List<DailyTasksDto> findDaily();
	Optional<Allowance> findById(Long id);
	
	void weeklyRemittance();

	

}
