package com.thatmg393.ts4j.world.events.weather;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Clouds {
    private final int backgroundCloud;
    private final short cloudCount;
    private final float windSpeed; // Shouldnt this be in Rain

    public Clouds(int backgroundCloud, short cloudCount, float windSpeed) {
        this.backgroundCloud = backgroundCloud;
        this.cloudCount = cloudCount;
        this.windSpeed = windSpeed;
    }
}
