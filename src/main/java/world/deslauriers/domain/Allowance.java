package world.deslauriers.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "allowance")
public class Allowance implements Serializable {

	private static final long serialVersionUID = 377230080671867060L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "balance")
	private Double balance;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "age")
	private Integer age;
	
	@ManyToMany(mappedBy = "allowances", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonManagedReference(value = "allowance-tasktype")
	private Set<TaskType> tasktype;
	
	@OneToMany(mappedBy = "allowance", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonManagedReference(value = "allowance-task")
	@JsonIgnore
	private Set<Task> task;

	public Allowance(Long id, Double balance, String firstname, String lastname, Integer age) {
		this.id = id;
		this.balance = balance;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}
}