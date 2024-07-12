package com.thatmg393.ts4j.world.contents;

import com.thatmg393.ts4j.world.tile.Tile;

import lombok.Getter;

import java.util.ArrayList;

public class WorldTiles {
    @Getter
	private final ArrayList<Tile> tiles;
			
    public WorldTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

    public Tile getTileAt(int index) {
		return tiles.get(index);
	}
}
