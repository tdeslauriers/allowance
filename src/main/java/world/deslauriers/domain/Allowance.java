package world.deslauriers.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import java.util.Set;

@Serdeable
@Entity
@Table(name = "allowance")
public class Allowance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "balance")
	private Double balance;
	
	@Column(name = "user_id")
	private Long userId;
	
	@ManyToMany(mappedBy = "allowances", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<TaskType> tasktype;
	
	@ManyToMany(mappedBy = "allowances", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<Task> tasks;

	public Allowance() {
	}

	public Allowance(Long id, Double balance, Long userId) {
		this.id = id;
		this.balance = balance;
		this.userId = userId;
	}

	public Allowance(Long id, Double balance, Long userId, Set<TaskType> tasktype, Set<Task> tasks) {
		this.id = id;
		this.balance = balance;
		this.userId = userId;
		this.tasktype = tasktype;
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<TaskType> getTasktype() {
		return tasktype;
	}

	public void setTasktype(Set<TaskType> tasktype) {
		this.tasktype = tasktype;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Allowance{" +
				"id=" + id +
				", balance=" + balance +
				", userId=" + userId +
				", tasktype=" + tasktype +
				", tasks=" + tasks +
				'}';
	}
}