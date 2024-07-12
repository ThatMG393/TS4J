package com.thatmg393.ts4j.world.events;

import com.thatmg393.ts4j.world.events.enums.InvasionTypes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Invasion {
    private final int delay;
    private final int size;
    private final InvasionTypes invationType;
    private final double position;
	
	@Setter private int sizeStart;
 
    public Invasion(int delay, int size, InvasionTypes invationType, double position) {
        this.delay = delay;
        this.size = size;
        this.invationType = invationType;
        this.position = position;
    }
}
