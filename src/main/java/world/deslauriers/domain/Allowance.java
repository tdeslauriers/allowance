package world.deslauriers.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "allowance")
public class Allowance implements Serializable {

	private static final long serialVersionUID = 377230080671867060L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "allowance_amount")
	private Double amount;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "age")
	private Integer age;
	
	@ManyToMany
	@JoinTable(name = "allowance_tasktype", 
	joinColumns = @JoinColumn(name = "allowance_id"),
	inverseJoinColumns = @JoinColumn(name = "task_type_id"))
	private Set<TaskType> tasktype;
	
	@OneToMany
	private Set<Task> task = new HashSet<>();

	public Allowance() {
	}

	public Allowance(Long id, Double amount, String firstname, String lastname, Integer age, Set<TaskType> tasktype,
			Set<Task> task) {
		this.id = id;
		this.amount = amount;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.tasktype = tasktype;
		this.task = task;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<TaskType> getTasktype() {
		return tasktype;
	}

	public void setTasktype(Set<TaskType> tasktype) {
		this.tasktype = tasktype;
	}

	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
