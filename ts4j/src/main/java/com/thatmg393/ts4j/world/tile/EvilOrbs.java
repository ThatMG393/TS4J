package com.thatmg393.ts4j.world.tile;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EvilOrbs {
    private final boolean smashedAtleastOnce;
    private final boolean willSpawnMeteorite;
    private final byte countUntilBossSpawn;

    public EvilOrbs(
            boolean smashedAtleastOnce, boolean willSpawnMeteorite, byte countUntilBossSpawn) {
        this.smashedAtleastOnce = smashedAtleastOnce;
        this.willSpawnMeteorite = willSpawnMeteorite;
        this.countUntilBossSpawn = countUntilBossSpawn;
    }
}
