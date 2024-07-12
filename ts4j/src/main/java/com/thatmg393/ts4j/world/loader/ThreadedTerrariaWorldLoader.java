package com.thatmg393.ts4j.world.loader;

import com.thatmg393.ts4j.utils.BinaryReader;
import com.thatmg393.ts4j.utils.Coordinates;
import com.thatmg393.ts4j.utils.FourPartSplit;
import com.thatmg393.ts4j.utils.Quadruple;
import com.thatmg393.ts4j.utils.Rect;
import com.thatmg393.ts4j.utils.UUIDUtil;
import com.thatmg393.ts4j.world.TerrariaWorld;
import com.thatmg393.ts4j.world.contents.WorldFileOffsets;
import com.thatmg393.ts4j.world.contents.WorldMetadata;
import com.thatmg393.ts4j.world.contents.WorldProperties;
import com.thatmg393.ts4j.world.contents.WorldTileStyles;
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
import com.thatmg393.ts4j.world.events.enums.InvasionTypes;
import com.thatmg393.ts4j.world.events.weather.Clouds;
import com.thatmg393.ts4j.world.events.weather.Rain;
import com.thatmg393.ts4j.world.events.weather.Sandstorm;
import com.thatmg393.ts4j.world.loader.base.BaseWorldLoader;
import com.thatmg393.ts4j.world.moon.MoonPhases;
import com.thatmg393.ts4j.world.moon.MoonStyles;
import com.thatmg393.ts4j.world.npc.angler.AnglerQuest;
import com.thatmg393.ts4j.world.npc.angler.AnglerQuestFish;
import com.thatmg393.ts4j.world.tile.EvilOrbs;
import com.thatmg393.ts4j.world.tile.Tile;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public class ThreadedTerrariaWorldLoader implements BaseWorldLoader {
	private static final Logger LOGGER = Logger.getLogger("ThreadedTerrariaWorldLoader");
		
	private BinaryReader reader;

	public ThreadedTerrariaWorldLoader(Path file) throws IOException {
		this.reader = new BinaryReader(Files.newInputStream(file), "ISO-8859-1");
	}
	
	private WorldMetadata constructMetadata() {
		int version = reader.readInt32();
		assertOrLogNThrow(version, 279, "versions do not match");
		
		String magic = new String(reader.readBytes(7), StandardCharsets.UTF_8).trim();
		assertOrLogNThrow(magic, "relogic", "magic key from world didnt match the correct key, are you sure its not corrupted?");
		
		int fileType = reader.readByte();
		assertOrLogNThrow(fileType, 2, "file type is not 2, are you sure you passed the correct world?");
		
		int revision = reader.readInt32();
		boolean favorite = reader.readUInt64().compareTo(BigInteger.ZERO) != 0;
		
		int[] offsets = new int[reader.readInt16()];
		for (int i = 0; i < offsets.length; ++i) {
			offsets[i] = reader.readInt32();
		}
		
		// TFImportances (idk what they are)
		// See: https://github.com/TerraMap/terramap.github.io/blob/21cb1b502cb487a91f97579a1ff03cbe3ce42c54/resources/js/WorldLoader.js#L81
		boolean[] importances = new boolean[(int) Math.ceil(reader.readInt16() / 8f)];
		short byteFlag = 0;
		int bitMask = 128;
		
		for (int i = 0; i < importances.length; ++i) {
			if (bitMask == 128) {
				byteFlag = reader.readUInt8();
				bitMask = 1;
			} else bitMask <<= i;
			
			importances[i] = (byteFlag & bitMask) == bitMask;
		}
		
		reader.skipTo(offsets[0]); // Skip for properties
		
		return new WorldMetadata(
			version, revision, favorite, new WorldFileOffsets(offsets), importances
		);
	}
	
	private WorldProperties constructProperties() {
		String name = reader.readString();
		WorldGenInfo genVer = new WorldGenInfo(reader.readString(), reader.readInt64());
		ImmutablePair<UUID, Integer> wIden = ImmutablePair.of(UUIDUtil.fromBytes(reader.readBytes(16)), reader.readInt32());
		Rect wBounds = new Rect(reader.readInt32(), reader.readInt32(), reader.readInt32(), reader.readInt32());
		Coordinates wSize = Coordinates.fromReversed(reader.readInt32(), reader.readInt32());
		WorldDifficulty wDiff = WorldDifficulty.values()[reader.readInt32()];
		WorldSeedType wSType = WorldSeedType.NORMAL;
		byte[] b = reader.readBytes(8);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] != 0) wSType = WorldSeedType.values()[i];
		}
		Date cOn = new Date((reader.readInt64() - 621355968000000000L) / 10000);
		WorldTileStyles wts = new WorldTileStyles(
			MoonStyles.values()[reader.readByte()],
			new FourPartSplit(
				ImmutableTriple.of(reader.readInt32(), reader.readInt32(), reader.readInt32()),
				new Quadruple<>(reader.readInt32(), reader.readInt32(), reader.readInt32(), reader.readInt32())
			),
			new FourPartSplit(
				ImmutableTriple.of(reader.readInt32(), reader.readInt32(), reader.readInt32()),
				new Quadruple<>(reader.readInt32(), reader.readInt32(), reader.readInt32(), reader.readInt32())
			)
		);
		BiomeBackgrounds bbg = new BiomeBackgrounds(reader.readInt32(), reader.readInt32(), reader.readInt32());
		Coordinates sCords = new Coordinates(reader.readInt32(), reader.readInt32());
		ImmutablePair<Double, Double> wl = ImmutablePair.of(reader.readDouble(), reader.readDouble());
		WorldTime wt = new WorldTime(
			reader.readDouble(), reader.readBoolean(),
			MoonPhases.values()[reader.readInt32()],
			reader.readBoolean(), reader.readBoolean()
		);
		Coordinates dunCord = new Coordinates(reader.readInt32(), reader.readInt32());
		WorldEvilType evTypShi = WorldEvilType.values()[(int)reader.readByte()];
		DefeatedBosses db = new DefeatedBosses(
			reader.readBoolean(), reader.readBoolean(), reader.readBoolean(), reader.readBoolean(),
			reader.readBoolean(), reader.readBoolean(), reader.readBoolean(), reader.readBoolean(),
			reader.readBoolean(), reader.readBoolean(), reader.readBoolean()
		);
		SavedNPCs sn = new SavedNPCs(reader.readBoolean(), reader.readBoolean(), reader.readBoolean());
		boolean gbarm = reader.readBoolean(), clown = reader.readBoolean(), fmoon = reader.readBoolean(), piratas = reader.readBoolean();
		db.setClown(clown); // Stupid random aah things between data
		DefeatedInvasions de = new DefeatedInvasions(
			gbarm, fmoon, piratas
		);
		EvilOrbs eo /* executive optical reference */ = new EvilOrbs(reader.readBoolean(), reader.readBoolean(), reader.readByte());
		int aSmashh = reader.readInt32();
		boolean hard = reader.readBoolean();
		boolean partiDomed = !reader.readBoolean(); // PARTY IS DOOMED ?!?!?!?!??!
		Invasion inv = new Invasion(reader.readInt32(), reader.readInt32(), InvasionTypes.values()[reader.readInt32()], reader.readDouble());
		double srd = reader.readDouble();
		short scd = reader.readUInt8();
		Rain r = new Rain(reader.readBoolean(), reader.readInt32(), reader.readSingle());
		ImmutableTriple<Tile, Tile, Tile> hmorez = ImmutableTriple.of(
			runAndForgetException(() -> Tile.values()[reader.readInt32()]),
			runAndForgetException(() -> Tile.values()[reader.readInt32()]),
			runAndForgetException(() -> Tile.values()[reader.readInt32()])
		);
		
		bbg.setForest(reader.readUInt8());
		bbg.setCorruption(reader.readUInt8());
		bbg.setJungle(reader.readUInt8());
		bbg.setSnow(reader.readUInt8());
		bbg.setHallow(reader.readUInt8());
		bbg.setCrimson(reader.readUInt8());
		bbg.setDesert(reader.readUInt8());
		bbg.setOcean(reader.readUInt8());
		
		Clouds cld = new Clouds(reader.readInt32(), reader.readInt16(), reader.readSingle());
		int anglerTaskCompletersNamesCount = reader.readInt32();
		ArrayList<String> anglerTaskCompletersNames = new ArrayList<>();
		for (int i = 0; i < anglerTaskCompletersNamesCount; ++i) {
			anglerTaskCompletersNames.add(reader.readString());
		}
		
		sn.setAnglerSaved(reader.readBoolean());
		AnglerQuest aq = new AnglerQuest(AnglerQuestFish.values()[reader.readInt32()], anglerTaskCompletersNames);
		
		sn.setStylistSaved(reader.readBoolean());
		sn.setTaxCollectorSaved(reader.readBoolean());
		sn.setGolferSaved(reader.readBoolean());
		inv.setSizeStart(reader.readInt32());
		
		reader.skip(4); // cultistDelay (what the hell is this)
		
		short mobTypesCount = reader.readInt16();
		HashMap<Integer, Integer> mobKills = new HashMap<>();
		for (int i = 0; i < mobTypesCount; ++i) {
			mobKills.put(i, reader.readInt32());
		}
		
		boolean sdialRun = reader.readBoolean();
		
		db.setDukeFishron(reader.readBoolean());
		de.setMartianMadness(reader.readBoolean());
		db.setLunaticCultist(reader.readBoolean());
		db.setMoonLord(reader.readBoolean());
		
		DefeatedMiniBosses dmb = new DefeatedMiniBosses(
			reader.readBoolean(), reader.readBoolean(), reader.readBoolean(),
			reader.readBoolean(), reader.readBoolean()
		);
		
		reader.skip(4); // duplicate of ActivePillar
		
		LunarEvent le = new LunarEvent(
			new LunarEvent.ActivePillars(reader.readBoolean(), reader.readBoolean(), reader.readBoolean(), reader.readBoolean()),
			reader.readBoolean()
		);
		
		boolean pCentActib = reader.readBoolean();
		boolean pNatuActib = reader.readBoolean();
		int pCd = reader.readInt32();
		
		int[] partyingNPCs = new int[reader.readInt32()];
		for (int i = 0; i < partyingNPCs.length; i++) partyingNPCs[i] = reader.readInt32();
		
		Party pty = new Party(
			partiDomed, pCd, partyingNPCs,
			pCentActib, pNatuActib
		);
		
		Sandstorm sst = new Sandstorm(
			reader.readBoolean(), reader.readInt32(),
			reader.readSingle(), reader.readSingle()
		);
		
		sn.setBartenderSaved(reader.readBoolean());
		
		reader.skip(3);
		
		return new WorldProperties(
			name, genVer, wIden, wBounds, wSize, wDiff,
			wSType, cOn, wts, bbg, sCords, wl, wt, dunCord,
			evTypShi, db, sn, de, eo, hard, aSmashh,
			inv, srd, scd, r, hmorez, cld, aq, mobKills,
			sdialRun, dmb, le, pty, sst
		);
	}

	@Override
	public TerrariaWorld get() {
		return new TerrariaWorld(
			constructMetadata(), constructProperties(), null
		);
	}
}
