package world.deslauriers.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;
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
		List<Task> tasks = 
				em.createQuery(hql, Task.class).getResultList();
		
		return tasks;
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
