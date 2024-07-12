package com.thatmg393.ts4j.world.events.weather;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Sandstorm {
	private final boolean isActive;
	private final int timeLeft;
	private final float severity;
	private final float intendedSeverity;

	public Sandstorm(boolean isActive, int timeLeft, float severity, float intendedSeverity) {
		this.isActive = isActive;
		this.timeLeft = timeLeft;
		this.severity = severity;
		this.intendedSeverity = intendedSeverity;
	}
}
