package com.thatmg393.ts4j.world.contents;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WorldMetadata {
	private final int version, revision;
	private final boolean favorite;
	private final WorldFileOffsets offsets;
	private final boolean[] importances;

	public WorldMetadata(int version, int revision, boolean favorite, WorldFileOffsets offsets, boolean[] importances) {
		this.version = version;
		this.revision = revision;
		this.favorite = favorite;
		this.offsets = offsets;
		this.importances = importances;
	}
}