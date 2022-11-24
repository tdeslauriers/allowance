package world.deslauriers.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Serdeable
@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "is_complete")
	private Boolean isComplete;
	
	@Column(name = "is_quality")
	private Boolean isQuality;
	
	@ManyToOne(targetEntity = TaskType.class, fetch = FetchType.LAZY)
	private TaskType tasktype;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "allowance_task",
			joinColumns = {@JoinColumn(name = "task_id")},
			inverseJoinColumns = {@JoinColumn(name = "allowance_id")}
	)
	Set<Allowance> allowances = new HashSet<>();

	public Task() {
	}

	public Task(Long id, LocalDate date, Boolean isComplete, Boolean isQuality) {
		this.id = id;
		this.date = date;
		this.isComplete = isComplete;
		this.isQuality = isQuality;
	}

	public Task(Long id, LocalDate date, Boolean isComplete, Boolean isQuality, TaskType tasktype, Set<Allowance> allowances) {
		this.id = id;
		this.date = date;
		this.isComplete = isComplete;
		this.isQuality = isQuality;
		this.tasktype = tasktype;
		this.allowances = allowances;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean complete) {
		isComplete = complete;
	}

	public Boolean getQuality() {
		return isQuality;
	}

	public void setIsQuality(Boolean quality) {
		isQuality = quality;
	}

	public TaskType getTasktype() {
		return tasktype;
	}

	public void setTasktype(TaskType tasktype) {
		this.tasktype = tasktype;
	}

	public Set<Allowance> getAllowances() {
		return allowances;
	}

	public void setAllowances(Set<Allowance> allowances) {
		this.allowances = allowances;
	}

	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", date=" + date +
				", isComplete=" + isComplete +
				", isQuality=" + isQuality +
				", tasktype=" + tasktype +
				", allowances=" + allowances +
				'}';
	}
}