package world.deslauriers.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Serdeable
@Entity
@Table(name = "tasktype")
public class TaskType  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "cadence")
	private String cadence;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "allowance_tasktype",
			joinColumns = {@JoinColumn(name = "tasktype_id")},
			inverseJoinColumns = {@JoinColumn(name = "allowance_id")}
			)
	Set<Allowance> allowances = new HashSet<>();
	
	@OneToMany(mappedBy = "tasktype", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<Task> task = new HashSet<>();

	public TaskType() {
	}

	public TaskType(Long id, String name, String cadence) {
		this.id = id;
		this.name = name;
		this.cadence = cadence;
	}

	public TaskType(Long id, String name, String cadence, Set<Allowance> allowances, Set<Task> task) {
		this.id = id;
		this.name = name;
		this.cadence = cadence;
		this.allowances = allowances;
		this.task = task;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCadence() {
		return cadence;
	}

	public void setCadence(String cadence) {
		this.cadence = cadence;
	}

	public Set<Allowance> getAllowances() {
		return allowances;
	}

	public void setAllowances(Set<Allowance> allowances) {
		this.allowances = allowances;
	}

	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "TaskType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", cadence='" + cadence + '\'' +
				", allowances=" + allowances +
				", task=" + task +
				'}';
	}
}

