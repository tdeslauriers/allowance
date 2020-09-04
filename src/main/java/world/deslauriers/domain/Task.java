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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task implements Serializable {

	private static final long serialVersionUID = 6721077534804310141L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "complete")
	private Boolean complete;
	
	@Column(name = "quality")
	private Boolean quality;
	
	@ManyToOne(targetEntity = TaskType.class)
	@JsonBackReference
	private TaskType tasktype;
	
	@ManyToOne(targetEntity = Allowance.class)
	@JsonBackReference
	private Allowance allowance;

}
