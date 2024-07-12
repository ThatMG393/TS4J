package com.thatmg393.ts4j.world;

import com.thatmg393.ts4j.world.loader.ThreadedTerrariaWorldLoader;

import java.io.IOException;
import java.nio.file.Path;

public class TerrariaWorldFactory {
	public static TerrariaWorld load(Path file) throws IOException {
		return new ThreadedTerrariaWorldLoader(file).get();
	}
}
