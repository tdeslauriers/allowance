package world.deslauriers.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.micronaut.transaction.annotation.ReadOnly;
import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.Cadence;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;

@Singleton
public class AllowanceRepositoryImpl implements AllowanceRepository {
	
	private final EntityManager em;

	public AllowanceRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	@ReadOnly
	public Optional<Allowance> findByFirstName(String firstname){
		
		String hql = "SELECT a FROM Allowance a WHERE a.firstname = :firstname";
		Allowance allowance = em
				.createQuery(hql, Allowance.class)
				.setParameter("firstname", firstname)
				.getSingleResult();
		
		return Optional.ofNullable(allowance);
	}
	
	@Override
	@ReadOnly
	public List<Allowance> findAll(){
		
		String hql = "SELECT a.id, a.amount, a.firstname, a.lastname, a.age FROM Allowance a";
		List<Object[]> lookup = new ArrayList<>(
				em.createQuery(hql, Object[].class).getResultList());
		
		List<Allowance> allowances = new ArrayList<>(lookup.size());
		
		for (Object[] record: lookup) {
			
			Allowance a = new Allowance();
			a.setId((Long) record[0]);
			a.setAmount((Double) record[1]);
			a.setFirstname((String) record[2]);
			a.setLastname((String) record[3]);
			a.setAge((Integer) record[4]);
			
			allowances.add(a);
			
		}
		
		return allowances;
	}
	
	@Override
	@ReadOnly
	public List<DailyTasksDto> findDaily(){
		
		String hql = "SELECT "
				+ "t.id, " 
				+ "a.firstname, "
				+ "a.lastname, "
				+ "tt.name, "
				+ "t.date, "
				+ "t.isComplete, "
				+ "t.isQuality "
				+ "FROM Allowance a "
				+ "LEFT JOIN a.tasktype tt "
				+ "LEFT JOIN  tt.task t "
				+ "WHERE t.date = :today";
		
		List<Object[]> lookup = 
				new ArrayList<>(em
					.createQuery(hql, Object[].class)
					.setParameter("today", LocalDate.now())
					.getResultList());
		
		List<DailyTasksDto> dailyTasks = new ArrayList<>(lookup.size());
		
		for (Object[] record : lookup) {
			
			DailyTasksDto task = new DailyTasksDto();
			task.setTaskId((Long) record[0]);
			task.setFirstname((String) record[1]);
			task.setLastname((String) record[2]);
			task.setTaskTypeName((String) record[3]);
			task.setDate((LocalDate) record[4]);
			task.setIsComplete((Boolean) record[5]);
			task.setIsQuality((Boolean) record[6]);
			
			dailyTasks.add(task);
		}
		
		return dailyTasks;
	}
	
	@Override
	@ReadOnly
	public List<TaskTypeCadenceDto> findAllowanceDailyTaskTypes(){
		
		String hql = "SELECT"
				+ " a.id,"
				+ " a.firstname,"
				+ " tt.name "
				+ "FROM Allowance a LEFT JOIN a.tasktype tt "
				+ "WHERE tt.cadence = :cadence";
		List<Object[]> lookup = new ArrayList<>(
				em.createQuery(hql, Object[].class)
				.setParameter("cadence", Cadence.DAILY.getCadence())
				.getResultList());
		
		List<TaskTypeCadenceDto> daily = new ArrayList<>(lookup.size());
		
		for (Object[] record: lookup) {
			
			TaskTypeCadenceDto t = new TaskTypeCadenceDto();
			t.setId((Long) record[0]);
			t.setFirstname((String) record[1]);
			t.setTaskTypeName((String) record[2]);
			
			daily.add(t);
		}
		
		return daily;
	}
	
	@Override
	@Transactional
	public int update(
			@NotNull Double amount, @NotBlank @Size(max = 10, min = 2)String firstname) {
		
		String hql = "UPDATE Allowance a SET a.amount = :amount WHERE a.firstname = :firstname";
		int update = em
				.createQuery(hql)
				.setParameter("amount", amount)
				.setParameter("firstname", firstname)
				.executeUpdate();
	
		return update;
	}
	
	@Override
	@Transactional
	public Optional<Allowance> save(Allowance allowance) {
		
		em.persist(allowance);
		return Optional.ofNullable(allowance);
	}
	
}
