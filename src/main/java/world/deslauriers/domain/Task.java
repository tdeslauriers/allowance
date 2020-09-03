package world.deslauriers.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task implements Serializable {

	private static final long serialVersionUID = 6721077534804310141L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "complete")
	private Boolean complete;
	
	@Column(name = "quality")
	private Boolean quality;
	
	@ManyToOne(targetEntity = TaskType.class)
	private TaskType tasktype;
	
	@ManyToOne(targetEntity = Allowance.class)
	private Allowance allowance;

	public Task() {
	}

	public Task(Long id, LocalDate date, Boolean complete, Boolean quality, TaskType tasktype, Allowance allowance) {
		this.id = id;
		this.date = date;
		this.complete = complete;
		this.quality = quality;
		this.tasktype = tasktype;
		this.allowance = allowance;
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
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Boolean getQuality() {
		return quality;
	}

	public void setQuality(Boolean quality) {
		this.quality = quality;
	}

	public TaskType getTasktype() {
		return tasktype;
	}

	public void setTasktype(TaskType tasktype) {
		this.tasktype = tasktype;
	}

	public Allowance getAllowance() {
		return allowance;
	}

	public void setAllowance(Allowance allowance) {
		this.allowance = allowance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
