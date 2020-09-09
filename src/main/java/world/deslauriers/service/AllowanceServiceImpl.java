package world.deslauriers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import world.deslauriers.domain.Allowance;
import world.deslauriers.repository.AllowanceRepository;

@AllArgsConstructor
@Singleton
public class AllowanceServiceImpl implements AllowanceService {
	
	private static final Logger logger = LoggerFactory.getLogger(AllowanceServiceImpl.class);
	
	@Inject
	private AllowanceRepository allowanceDao;

	@Override
	public void weeklyRemittance() {
		
		List<Allowance> allowances = new ArrayList<>(allowanceDao.findAll());
		for (Allowance a : allowances) {
			
			Double currentBalance = a.getAmount();
			currentBalance += a.getAge();
			
			int recordsUpdated = allowanceDao.update(currentBalance, a.getFirstname());
			
			logger.info("Allowance table: " + recordsUpdated + " record(s) updated.  " + 
			a.getFirstname() + "'s balance incremented by " + a.getAge() + " dollars.");
		}
	}
	
	@Override
	public void decrementBy2(String firstname) {
		
		Optional<Allowance> allowance = allowanceDao.findByFirstName(firstname);
		Double currentBalance = allowance.get().getAmount();
		currentBalance -= 2;
		
		int recordsUpdated = allowanceDao.update(currentBalance, firstname);
		
		logger.info(recordsUpdated + " record(s) sussessfully decremented by 2 in Allowance table.");
	}
	
	@Override
	public Optional<Allowance> findByFirstname(String firstname){
		
		//Once auth built, add check to validate requestor authorized
		Optional<Allowance> allowance = allowanceDao.findByFirstName(firstname);
		
		return allowance;
	}
	
	@Override
	public List<Allowance> findAll(){
		
		//Once auth built, add check to validate requestor authorized		
		return new ArrayList<Allowance>(allowanceDao.findAll());
	}
}
