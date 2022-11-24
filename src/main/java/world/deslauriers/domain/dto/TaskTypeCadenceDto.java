package world.deslauriers.domain.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record TaskTypeCadenceDto (
	
	@NonNull Long tasktypeId,
	@NonNull Long allowanceId
){}
