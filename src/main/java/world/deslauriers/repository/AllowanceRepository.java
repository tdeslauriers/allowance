package world.deslauriers.repository;

import java.util.List;
import java.util.Optional;

import world.deslauriers.domain.Allowance;

public interface AllowanceRepository {

	Optional<Allowance> findByFirstName(String firstname);
	List<Allowance> findAll();
	int update(Double amount, String firstname);
	Optional<Allowance> save(Allowance allowance);
}
