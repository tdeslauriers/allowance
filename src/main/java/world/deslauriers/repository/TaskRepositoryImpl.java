package world.deslauriers.repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import io.micronaut.transaction.annotation.ReadOnly;
import world.deslauriers.domain.Task;


@Singleton
public class TaskRepositoryImpl implements TaskRepository {
	
	private final EntityManager em;

	public TaskRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	@ReadOnly
	public List<Task> findAll(){
		
		String hql = "SELECT t FROM Task t";
		List<Task> tasks = new ArrayList<>(
				em.createQuery(hql, Task.class).getResultList());
		
		return tasks;
	}
	
	@Override
	@ReadOnly
	public Optional<Task> findById(Long id){
		
		String hql = "SELECT t FROM Task t WHERE t.id = :id";
		Task task = em
				.createQuery(hql, Task.class)
				.setParameter("id", id)
				.getSingleResult();
		
		return Optional.ofNullable(task);
	}
	
	@Override
	@ReadOnly
	public Optional<Task> findByName(String name){
		
		String hql = "SELECT t FROM Task t WHERE t.name = :name";
		Task task = em
				.createQuery(hql, Task.class)
				.setParameter("name", name)
				.getSingleResult();
		
		return Optional.ofNullable(task);
	}
	
	@Override
	@ReadOnly
	public List<Task> findByInterval(LocalDate start, LocalDate end){
		
		String hql = "SELECT t FROM Task t WHERE t.date BETWEEN :start AND :end ORDER BY t.date";
		List<Task> tasks = new ArrayList<>(
				em.createQuery(hql, Task.class)
				.setParameter("start", localDateToTimeStamp(start))
				.setParameter("end", localDateToTimeStamp(end)) 
				.getResultList());
				
		return tasks;
	}
	
	private Timestamp localDateToTimeStamp(LocalDate date) {
		
		return Timestamp.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
	}
	
	@Override
	@Transactional
	public int updateIsCompleteById(@NotNull Boolean isComplete, @NotNull Long id) {
		
		String hql = "UPDATE Task t SET t.isComplete = :isComplete WHERE t.id = :id";
		
		return em
				.createQuery(hql)
				.setParameter("isComplete", isComplete)
				.setParameter("id", id)
				.executeUpdate();
	}
	
	@Override
	@Transactional
	public int updateIsQualityById(@NotNull Boolean isQuality, @NotNull Long id) {
		
		String hql = "UPDATE Task t SET t.isQuality = :isQuality WHERE t.id =:id";
		
		return em
				.createQuery(hql)
				.setParameter("isQuality", isQuality)
				.setParameter("id", id)
				.executeUpdate();
	}
	
	@Override
	@Transactional
	public Optional<Task> save(Task task) {

		em.persist(task);
		return Optional.of(task);
	}
}
