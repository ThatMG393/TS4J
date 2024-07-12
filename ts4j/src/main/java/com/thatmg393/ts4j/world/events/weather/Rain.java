package com.thatmg393.ts4j.world.events.weather;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Rain {
	private final boolean isRaining;
	private final int duration;
	private final float maxRain; // maxDroplets ??

	public Rain(boolean isRaining, int duration, float maxRain) {
		this.isRaining = isRaining;
		this.duration = duration;
		this.maxRain = maxRain;
	}
}
