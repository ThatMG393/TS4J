package com.thatmg393.ts4j.world;

import com.thatmg393.ts4j.world.contents.WorldMetadata;
import com.thatmg393.ts4j.world.contents.WorldProperties;
import com.thatmg393.ts4j.world.contents.WorldTiles;

import lombok.Getter;

public class TerrariaWorld {
	@Getter
	private final WorldMetadata metadata;
	
	@Getter
	private final WorldProperties properties;
	
	@Getter
	private final WorldTiles tiles;
	
	public TerrariaWorld(
		WorldMetadata md,
		WorldProperties prop,
		WorldTiles tiles
	) {
		this.metadata = md;
		this.properties = prop;
		this.tiles = tiles;
	}
}
