package world.deslauriers.service;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import world.deslauriers.domain.Allowance;
import world.deslauriers.repository.AllowanceRepository;

@Singleton
public class AllowanceServiceImpl implements AllowanceService {
	
	private static final Logger logger = LoggerFactory.getLogger(AllowanceServiceImpl.class);
	
	@Inject
	private AllowanceRepository allowanceDao;
	
	public AllowanceServiceImpl(AllowanceRepository allowanceDao) {
		this.allowanceDao = allowanceDao;
	}

	@Override
	public void weeklyRemittance(String firstname) {
		
		Optional<Allowance> allowance = allowanceDao.findByFirstName(firstname);
		Double currentBalance = allowance.get().getAmount();
		currentBalance += allowance.get().getAge();
		
		int recordsUpdated = allowanceDao.update(currentBalance, firstname);
		
		logger.info(recordsUpdated + " record(s) had weekly remittance applied in Allowance table.");
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
}
