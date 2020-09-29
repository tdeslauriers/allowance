package world.deslauriers.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import io.micronaut.transaction.annotation.ReadOnly;
import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.dto.DailyTasksDto;
import world.deslauriers.domain.dto.TaskIntervalDto;

@Singleton
public class AllowanceRepositoryImpl implements AllowanceRepository {
	
	private final EntityManager em;

	public AllowanceRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	@ReadOnly
	public Optional<Allowance> findById(Long id){
		
		String hql = "SELECT a FROM Allowance a WHERE a.id = :id";
		Allowance allowance = 
				em
				.createQuery(hql, Allowance.class)
				.setParameter("id", id)
				.getSingleResult();
		
		return Optional.ofNullable(allowance);
	}
	
	@Override
	@ReadOnly
	public List<Allowance> findAll(){
		
		String hql = "SELECT new world.deslauriers.domain.Allowance("
				+ "a.id, a.balance, a.firstname, a.lastname, a.age)"
				+ " FROM Allowance a";
		List<Allowance> allowances = new ArrayList<>(
				em.createQuery(hql, Allowance.class)
				.getResultList());

		return allowances;
	}
	
	@Override
	@ReadOnly
	public List<Allowance> findAllTableData(){
		
		String hql = "FROM Allowance";
		List<Allowance> allowances = 
				em.createQuery(hql, Allowance.class)
				.getResultList();
		
		return allowances;
	}
	
	@Override
	@Transactional
	public int updateBalance(
			@NotNull Double balance, @NotNull Long id) {
		
		String hql = "UPDATE Allowance a SET a.balance = :balance WHERE a.id = :id";
		int update = em
				.createQuery(hql)
				.setParameter("balance", balance)
				.setParameter("id", id)
				.executeUpdate();
	
		return update;
	}
	
	@Override
	@Transactional
	public void save(Allowance allowance) {
		
		em.persist(allowance);
	}
	
	@Override
	@ReadOnly
	public List<DailyTasksDto> findDailyTasks(){
		
		String hql = "SELECT new world.deslauriers.domain.dto.DailyTasksDto("
		+ "t.id, " 
		+ "a.firstname, "
		+ "a.lastname, "
		+ "tt.name, "
		+ "t.date, "
		+ "t.isComplete, "
		+ "t.isQuality) "
		+ "FROM Allowance a "
		+ "LEFT JOIN a.tasktype tt "
		+ "LEFT JOIN  tt.task t "
		+ "WHERE t.date = :today";
		List<DailyTasksDto> daily = 
				em.createQuery(hql, DailyTasksDto.class)
				.setParameter("today", LocalDate.now())
				.getResultList();
				
		return daily;
	}
	
	@Override
	@ReadOnly
	public List<TaskIntervalDto> findTasksByInterval(LocalDate start, LocalDate end){
		
		String hql = "SELECT new world.deslauriers.domain.dto.TaskIntervalDto("
		+ "t.id, " 
		+ "t.date, "
		+ "tt.name, "
		+ "t.isComplete, "
		+ "t.isQuality, "
		+ "tt.id, "
		+ "a.id) "
		+ "FROM Allowance a "
		+ "LEFT JOIN a.tasktype tt "
		+ "LEFT JOIN tt.task t "
		+ "WHERE t.date BETWEEN :start AND :end "
		+ "ORDER BY t.date ASC";
		List<TaskIntervalDto> tasks = 
				em.createQuery(hql, TaskIntervalDto.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
				
		return tasks;
	}
}

