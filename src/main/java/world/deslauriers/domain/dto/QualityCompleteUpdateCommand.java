package world.deslauriers.domain.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record QualityCompleteUpdateCommand (
	
	@NonNull
	Long taskId,
	
	@NonNull
	Boolean updateStatus
){}
