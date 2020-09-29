package world.deslauriers.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskTypeCadenceDto implements Serializable {

	private static final long serialVersionUID = -2101510331161224322L;
	
	private Long tasktypeId;
	private Long allowanceId;
}
