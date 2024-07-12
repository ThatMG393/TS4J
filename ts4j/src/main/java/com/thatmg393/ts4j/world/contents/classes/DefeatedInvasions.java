package com.thatmg393.ts4j.world.contents.classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class DefeatedInvasions {
    private final boolean goblinArmy;
    private final boolean frostMoon;
    private final boolean pirates;
	
	@Setter
	private boolean martianMadness;

    public DefeatedInvasions(boolean goblinArmy, boolean frostMoon, boolean pirates) {
        this.goblinArmy = goblinArmy;
        this.frostMoon = frostMoon;
        this.pirates = pirates;
    }
}
