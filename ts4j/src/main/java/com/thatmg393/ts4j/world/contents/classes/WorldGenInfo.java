package com.thatmg393.ts4j.world.contents.classes;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WorldGenInfo {
    private final String seed;
    private final long generatorVersion;

	public WorldGenInfo(String seed, long generatorVersion) {
	    this.seed = seed;
	    this.generatorVersion = generatorVersion;
	}
}
