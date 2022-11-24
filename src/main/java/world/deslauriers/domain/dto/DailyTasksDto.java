package world.deslauriers.domain.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Serdeable
public record DailyTasksDto(

	Long taskId,
	Long useId,
	String taskTypeName,
	LocalDate date,
	Boolean isComplete,
	Boolean isQuality

){}
