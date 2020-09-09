package world.deslauriers.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import io.micronaut.transaction.annotation.ReadOnly;
import world.deslauriers.domain.TaskType;

@Singleton
public class TaskTypeRepositoryImpl implements TaskTypeRepository {

	private final EntityManager em;

	public TaskTypeRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
 	@ReadOnly
	public List<TaskType> findAll(){
		
		String hql = "SELECT tt FROM TaskType tt";
		List<TaskType> tt = new ArrayList<>(
				em.createQuery(hql, TaskType.class).getResultList());
		
		return tt;
	}
	
	@Override
	@Transactional
	public Optional<TaskType> save(TaskType taskType){
		
		em.persist(taskType);
		return Optional.of(taskType);
	}
} 
