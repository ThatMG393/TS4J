package com.thatmg393.ts4j.world.contents;

import com.thatmg393.ts4j.utils.Coordinates;
import com.thatmg393.ts4j.utils.Rect;
import com.thatmg393.ts4j.world.contents.classes.BiomeBackgrounds;
import com.thatmg393.ts4j.world.contents.classes.DefeatedBosses;
import com.thatmg393.ts4j.world.contents.classes.DefeatedInvasions;
import com.thatmg393.ts4j.world.contents.classes.DefeatedMiniBosses;
import com.thatmg393.ts4j.world.contents.classes.SavedNPCs;
import com.thatmg393.ts4j.world.contents.classes.WorldGenInfo;
import com.thatmg393.ts4j.world.contents.classes.WorldTime;
import com.thatmg393.ts4j.world.contents.enums.WorldDifficulty;
import com.thatmg393.ts4j.world.contents.enums.WorldEvilType;
import com.thatmg393.ts4j.world.contents.enums.WorldSeedType;
import com.thatmg393.ts4j.world.events.Invasion;
import com.thatmg393.ts4j.world.events.LunarEvent;
import com.thatmg393.ts4j.world.events.Party;
import com.thatmg393.ts4j.world.events.weather.Clouds;
import com.thatmg393.ts4j.world.events.weather.Rain;
import com.thatmg393.ts4j.world.events.weather.Sandstorm;
import com.thatmg393.ts4j.world.npc.angler.AnglerQuest;
import com.thatmg393.ts4j.world.tile.EvilOrbs;
import com.thatmg393.ts4j.world.tile.Tile;

import lombok.Getter;
import lombok.ToString;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Getter
@ToString
public class WorldProperties {
    private final String name;
    private final WorldGenInfo genInfo;
    private final Pair<UUID, Integer> identifiers;
    private final Rect bounds;
    private final Coordinates worldSize;
    private final WorldDifficulty difficulty;
    private final WorldSeedType seedType;
    private final Date creationTime;
    private final WorldTileStyles tileStyles;
    private final BiomeBackgrounds biomeBackgrounds;
    private final Coordinates spawnCoordinates;
    private final Pair<Double, Double> worldLevels;
    private final WorldTime worldTime;
    private final Coordinates dungeonCoordinates;
    private final WorldEvilType evilType;
    private final DefeatedBosses defeatedBosses;
    private final SavedNPCs savedNPCs;
    private final DefeatedInvasions defeatedInvasions;
    private final EvilOrbs evilOrbs;
    private final boolean isHardmode;
    private final int altarSmashedCount;
    private final Invasion currentInvasion;
    private final double slimeRainDuration;
    private final short sundialCooldown;
    private final Rain rain;
    private final Triple<Tile, Tile, Tile> hardmodeOres;
    private final Clouds clouds;
    private final AnglerQuest currentAnglerQuest;
    private final HashMap<Integer, Integer> killedMobs;
    private final boolean isSundialRunning;
    private final DefeatedMiniBosses defeatedMiniBosses;
    private final LunarEvent currentLunarEvent;
    private final Party party;
    private final Sandstorm sandstorm;

    public WorldProperties(
            String name,
            WorldGenInfo genInfo,
            Pair<UUID, Integer> identifiers,
            Rect bounds,
            Coordinates worldSize,
            WorldDifficulty difficulty,
            WorldSeedType seedType,
            Date creationTime,
            WorldTileStyles tileStyles,
            BiomeBackgrounds biomeBackgrounds,
            Coordinates spawnCoordinates,
            Pair<Double, Double> worldLevels,
            WorldTime worldTime,
            Coordinates dungeonCoordinates,
            WorldEvilType evilType,
			DefeatedBosses defeatedBosses,
            SavedNPCs savedNPCs,
            DefeatedInvasions defeatedInvasions,
            EvilOrbs evilOrbs,
            boolean isHardmode,
            int altarSmashedCount,
            Invasion currentInvasion,
            double slimeRainDuration,
            short sundialCooldown,
            Rain rain,
            Triple<Tile, Tile, Tile> hardmodeOres,
            Clouds clouds,
            AnglerQuest currentAnglerQuest,
            HashMap<Integer, Integer> killedMobs,
            boolean isSundialRunning,
            DefeatedMiniBosses defeatedMiniBosses,
            LunarEvent currentLunarEvent,
            Party party,
            Sandstorm sandstorm) {
        this.name = name;
        this.genInfo = genInfo;
        this.identifiers = identifiers;
        this.bounds = bounds;
        this.worldSize = worldSize;
        this.difficulty = difficulty;
        this.seedType = seedType;
        this.creationTime = creationTime;
        this.tileStyles = tileStyles;
        this.biomeBackgrounds = biomeBackgrounds;
        this.spawnCoordinates = spawnCoordinates;
        this.worldLevels = worldLevels;
        this.worldTime = worldTime;
        this.dungeonCoordinates = dungeonCoordinates;
        this.evilType = evilType;
		this.defeatedBosses = defeatedBosses;
        this.savedNPCs = savedNPCs;
        this.defeatedInvasions = defeatedInvasions;
        this.evilOrbs = evilOrbs;
        this.isHardmode = isHardmode;
        this.altarSmashedCount = altarSmashedCount;
        this.currentInvasion = currentInvasion;
        this.slimeRainDuration = slimeRainDuration;
        this.sundialCooldown = sundialCooldown;
        this.rain = rain;
        this.hardmodeOres = hardmodeOres;
        this.clouds = clouds;
        this.currentAnglerQuest = currentAnglerQuest;
        this.killedMobs = killedMobs;
        this.isSundialRunning = isSundialRunning;
        this.defeatedMiniBosses = defeatedMiniBosses;
        this.currentLunarEvent = currentLunarEvent;
        this.party = party;
        this.sandstorm = sandstorm;
    }
}
