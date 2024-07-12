package com.thatmg393.ts4j.world.contents.classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class BiomeBackgrounds {
    private final int undergroundSnow;
    private final int undergroundJungle;
    private final int underworld;
	
	@Setter
	private short forest;
	
	@Setter
	private short corruption;
	
	@Setter
	private short jungle;
	
	@Setter
	private short snow;
	
	@Setter
	private short hallow;
	
	@Setter
	private short crimson;

	@Setter
	private short desert;
	
	@Setter
	private short ocean;

    public BiomeBackgrounds(int undergroundSnow, int undergroundJungle, int underworld) {
        this.undergroundSnow = undergroundSnow;
        this.undergroundJungle = undergroundJungle;
        this.underworld = underworld;
    }
}
