package com.thatmg393.ts4j.world.events;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Party {
	private final boolean partyDoomed;
	private final int cooldown;
	private final int[] partyingNPCs;

	private final boolean thrownByPartyCenter;
	private final boolean thrownByNaturalEvent;

	public Party(
			boolean partyDoomed,
			int cooldown,
			int[] partyingNPCs,
			boolean thrownByPartyCenter,
			boolean thrownByNaturalEvent) {
		this.partyDoomed = partyDoomed;
		this.cooldown = cooldown;
		this.partyingNPCs = partyingNPCs;
		this.thrownByPartyCenter = thrownByPartyCenter;
		this.thrownByNaturalEvent = thrownByNaturalEvent;
	}
}
