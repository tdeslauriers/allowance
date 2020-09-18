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
import world.deslauriers.domain.dto.DailyTasksDto;

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
		
		String hql = "SELECT a FROM Allowance a";
		List<Allowance> allowances = new ArrayList<Allowance>(
				em.createQuery(hql, Allowance.class).getResultList());
		
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
