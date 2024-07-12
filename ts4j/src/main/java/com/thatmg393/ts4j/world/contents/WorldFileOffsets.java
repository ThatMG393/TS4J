package com.thatmg393.ts4j.world.contents;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WorldFileOffsets {
	private final int fileFormatOffset = 0;
	private final int worldHeaderOffset;
	private final int worldTilesOffset;
	
	public WorldFileOffsets(int... offsets) {
		this.worldHeaderOffset = offsets[0];
		this.worldTilesOffset = offsets[1];
	}
}
