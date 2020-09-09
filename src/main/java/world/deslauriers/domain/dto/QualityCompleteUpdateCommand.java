package world.deslauriers.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class QualityCompleteUpdateCommand implements Serializable {

	private static final long serialVersionUID = 860325473627728900L;
	
	@NotNull
	private Long taskId;
	
	@NotNull
	private Boolean updateStatus;
}
