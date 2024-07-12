package com.thatmg393.ts4j.world.events;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LunarEvent {
	private final ActivePillars activePillars;
	private final boolean eventActive;

	public LunarEvent(ActivePillars activePillars, boolean eventActive) {
		this.activePillars = activePillars;
		this.eventActive = eventActive;
	}

	public static record ActivePillars(boolean solar, boolean vortex, boolean nebula, boolean stardust) {}
}
