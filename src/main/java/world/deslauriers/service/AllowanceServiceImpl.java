package world.deslauriers.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.TaskIntervalDto;
import world.deslauriers.repository.AllowanceRepository;

@Singleton
public class AllowanceServiceImpl implements AllowanceService {
	
	private static final Logger logger = LoggerFactory.getLogger(AllowanceServiceImpl.class);
	
	@Inject
	private AllowanceRepository allowanceDao;

	@Override
	public void weeklyRemittance() {
		
		List<Allowance> allowances = new ArrayList<>(
				allowanceDao.findAll());
		
		LocalDate end = LocalDate.now().minusDays(1);
		LocalDate start = end.minusDays(8);
		List<TaskIntervalDto> lastWeekTasks = new ArrayList<>(
				allowanceDao.findTasksByInterval(start, end));

		for(Allowance a: allowances) {

			Double age = 11d; // CALL USER SERVICE
			Double totalPossible = Double.valueOf(age);
			Double totalPerTask = Double.valueOf(age)/lastWeekTasks.size();
			
			for(TaskIntervalDto task: lastWeekTasks) {
				
				if (task.allowanceId() == a.getId()) {
					
					if(!task.isComplete()) {
						
						totalPossible -= totalPerTask;
						
						logger.info(task.date() + ": "
								+ task.taskTypeName() + " was not completed - "
								+ "subtracting " + totalPerTask + " from total possible.");
					}
					
					if(task.isComplete() && !task.isQuality()) {
						
						totalPossible -= (totalPerTask/2);
						logger.info(task.date() + ": "
								+ task.taskTypeName() + " was not completed to expectation - "
								+ "subtracting " + totalPerTask/2 + " from total possible.");
					}
				}
			}
			
			totalPossible = round(totalPossible, 2);
			int newBal = allowanceDao.updateBalance(a.getBalance() + totalPossible, a.getId());

		}
	}
	
	@Override
	public List<Allowance> findAll(){
		
		//Once auth built, add check to validate requestor authorized		
		return new ArrayList<Allowance>(allowanceDao.findAll());
	}
	
	@Override
	public List<DailyTasksDto> findDaily(){
		
		//Once auth built, add check to validate requestor authorized		
		return new ArrayList<DailyTasksDto>(allowanceDao.findDailyTasks());
	}
	
	@Override
	public Optional<Allowance> findById(Long id){
		
		return allowanceDao.findById(id);
	}

	private static double round(double value, int precision) {
		
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}
}
