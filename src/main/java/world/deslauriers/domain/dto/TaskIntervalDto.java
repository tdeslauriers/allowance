package world.deslauriers.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskIntervalDto implements Serializable {

	private static final long serialVersionUID = -4898241516452116705L;
	
	private Long taskId;
	private LocalDate date;
	private String taskTypeName;
	private Boolean isComplete;
	private Boolean isQuality;
	private Long tasktypeId;
	private Long allowanceId;
	
}
