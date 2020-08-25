package world.deslauriers.service;

import java.util.Optional;

import world.deslauriers.domain.Allowance;

public interface AllowanceService {

	void weeklyRemittance(String firstname);
	void decrementBy2(String firstname);
	Optional<Allowance> findByFirstname(String firstname);

}
