package world.deslauriers.domain.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;
@Serdeable
public record TaskIntervalDto (
	
	Long taskId,
	LocalDate date,
	String taskTypeName,
	Boolean isComplete,
	Boolean isQuality,
	Long tasktypeId,
	Long allowanceId

){}
