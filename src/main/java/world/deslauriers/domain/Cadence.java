package world.deslauriers.domain;

public enum Cadence {

	DAILY("daily"),
	WEEKLY("weekly");

	private final String cadence;

	Cadence(String cadence) {
		this.cadence = cadence;
	}

	public String getCadence() {
		return cadence;
	}
}
