package world.deslauriers.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTasksDto implements Serializable {
	
	private static final long serialVersionUID = 6668115305861543065L;

	private Long taskId;
	private String firstname;
	private String lastname;
	private String taskTypeName;
	private LocalDate date;
	private Boolean isComplete;
	private Boolean isQuality;

}
