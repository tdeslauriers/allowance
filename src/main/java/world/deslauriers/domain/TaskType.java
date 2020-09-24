package world.deslauriers.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasktype")
public class TaskType implements Serializable {

	private static final long serialVersionUID = 8655835783845056181L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "cadence")
	private String cadence;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "allowance_tasktype",
			joinColumns = {@JoinColumn(name = "task_type_id")},
			inverseJoinColumns = {@JoinColumn(name = "allowance_id")}
			)
	@JsonBackReference(value = "allowance-tasktype")
	Set<Allowance> allowances = new HashSet<>();
	
	@OneToMany(mappedBy = "tasktype", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "tasktype-task")
	@JsonIgnore
	private Set<Task> task = new HashSet<>();

	
}
