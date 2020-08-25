package world.deslauriers.repository;

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

@Singleton
public class AllowanceRepositoryImpl implements AllowanceRepository {
	
	private final EntityManager em;

	public AllowanceRepositoryImpl(EntityManager em) {
		super();
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
	public void save(Allowance allowance) {
		
		em.persist(allowance);
	}
}
