package com.thatmg393.ts4j.world.contents.classes;

import com.thatmg393.ts4j.world.moon.MoonPhases;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WorldTime {
    private final double currentTime;
    private final boolean daytime;
    private final MoonPhases currentMoonPhase;
    private final boolean bloodMoon;
    private final boolean eclipse;

    public WorldTime(
        double currentTime,
        boolean daytime,
        MoonPhases currentMoonPhase,
        boolean bloodMoon,
        boolean eclipse
	) {
        this.currentTime = currentTime;
        this.daytime = daytime;
        this.currentMoonPhase = currentMoonPhase;
        this.bloodMoon = bloodMoon;
        this.eclipse = eclipse;
    }
}
