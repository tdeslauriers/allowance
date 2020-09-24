package world.deslauriers.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.Task;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;
import world.deslauriers.repository.AllowanceRepository;

@AllArgsConstructor
@Singleton
public class AllowanceServiceImpl implements AllowanceService {
	
	private static final Logger logger = LoggerFactory.getLogger(AllowanceServiceImpl.class);
	
	@Inject
	private AllowanceRepository allowanceDao;

	@Override
	public void weeklyRemittance() {
//		
//		List<Allowance> allowances = new ArrayList<>(allowanceDao.findAll());
//		
//		LocalDate start = LocalDate.now();
//		LocalDate end = start.minusDays(7);
//		
//
//		List<Task> weeklyTasks = new ArrayList<>(taskService.findWeeklyTasks(start, end));
//		
//		for (Allowance a : allowances) {
//			
//			List<Task> aTasks = new ArrayList<>();
//			for(Task t: weeklyTasks) {
//				
//				if(t.getAllowance().getFirstname().equals(a.getFirstname())) {
//					
//					aTasks.add(t);
//				}
//			}
//			
//			Double totalPossible = Double.valueOf(a.getAge());
//			Double possiblePerTask = totalPossible/aTasks.size();
//			for (Task t: aTasks) {
//				
//				if(t.getIsComplete() && !t.getIsQuality()) {
//					
//					totalPossible -= (possiblePerTask/2);
//					logger.info(t.getDate() + ": " + t.getTasktype().getName()
//							+ " was not completed up to par - Subtracting " 
//							+ possiblePerTask/2 + " from possible " + totalPossible);
//				}
//				
//				if(!t.getIsComplete()) {
//					
//					totalPossible -= possiblePerTask;
//					logger.info(t.getDate() + ": " + t.getTasktype().getName()
//							+ " was not completed - Subtracting " 
//							+ possiblePerTask + " from possible " + totalPossible);
//				}
//			}
//			
//			Double currentBalance = a.getAmount() + totalPossible;
//			
//			int recordsUpdated = allowanceDao.update(currentBalance, a.getFirstname());
//			
//			logger.info("Allowance table: " + recordsUpdated + " record(s) updated.  " + 
//			a.getFirstname() + "'s balance incremented by " + totalPossible + " dollars.");
//		}
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
	
	@Override
	public List<DailyTasksDto> findDaily(){
		
		//Once auth built, add check to validate requestor authorized		
		return new ArrayList<DailyTasksDto>(allowanceDao.findDaily());
	}
	
	@Override
	public List<TaskTypeCadenceDto> findDailyTaskTypes(){
		
		return new ArrayList<>(allowanceDao.findAllowanceDailyTaskTypes());
	}
}
