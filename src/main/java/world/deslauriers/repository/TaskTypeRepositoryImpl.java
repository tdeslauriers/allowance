package world.deslauriers.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import io.micronaut.transaction.annotation.ReadOnly;
import world.deslauriers.domain.TaskType;
import world.deslauriers.domain.dto.TaskTypeCadenceDto;

@Singleton
public class TaskTypeRepositoryImpl implements TaskTypeRepository {

	private final EntityManager em;

	public TaskTypeRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
 	@ReadOnly
	public List<TaskType> findAll(){
		
		String hql = "SELECT new world.deslauriers.domain.TaskType("
				+ "tt.id, tt.name, tt.cadence) FROM TaskType tt";
		List<TaskType> tt = new ArrayList<>(
				em.createQuery(hql, TaskType.class).getResultList());
		
		return tt;
	}
	
	@Override
	@ReadOnly
	public List<TaskTypeCadenceDto> findTasktypesByCadence(String cadence){
		
		String hql = "SELECT new world.deslauriers.domain.dto.TaskTypeCadenceDto("
				+ "tt.id, "
				+ "a.id)"
				+ " FROM TaskType tt"
				+ "	LEFT JOIN tt.allowances a"
				+ "	WHERE tt.cadence = :cadence";
		List<TaskTypeCadenceDto> tasktypes = new ArrayList<>(
				em.createQuery(hql, TaskTypeCadenceDto.class)
				.setParameter("cadence", cadence)
				.getResultList());
		
		return tasktypes;
	}
	
	@Override
	@Transactional
	public Optional<TaskType> save(TaskType taskType){
		
		em.persist(taskType);
		return Optional.of(taskType);
	}

	@Override
	@ReadOnly
	public Optional<TaskType> findById(Long id) {
		
		String hql = "SELECT tt"
				+ " FROM TaskType tt"
				+ " WHERE tt.id = :id";
		TaskType tts = 
				em.createQuery(hql, TaskType.class)
				.setParameter("id", id)
				.getSingleResult();
		
		return Optional.ofNullable(tts);
	}
} 
