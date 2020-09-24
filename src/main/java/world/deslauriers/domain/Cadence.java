package world.deslauriers.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Cadence {

	DAILY("daily"),
	WEEKLY("weekly");

	private final String cadence;
}
