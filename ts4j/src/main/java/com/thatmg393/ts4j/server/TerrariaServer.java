package com.thatmg393.ts4j.server;

import com.thatmg393.ts4j.server.client.TServerClient;
import com.thatmg393.ts4j.server.networking.TPacketParser;
import com.thatmg393.ts4j.socket.MultiClientSocketServer;
import com.thatmg393.ts4j.world.TerrariaWorld;

import com.thatmg393.ts4j.world.TerrariaWorldFactory;
import java.nio.file.Paths;
import lombok.Getter;

import java.io.IOException;
import java.util.logging.Logger;

public class TerrariaServer {
	private static final Logger LOGGER = Logger.getLogger("TerrariaServer");
	
	public final String serverIP;
	public final int serverPort;
	
	private MultiClientSocketServer<TServerClient, TPacketParser> server;
	private String worldPath;
	
	@Getter
	private TerrariaWorld world;
	
	@Getter
	private boolean isRunning = false;
	
	public TerrariaServer(String worldPath) {
		this("0.0.0.0", 7777, 10, worldPath);
	}

	public TerrariaServer(int maxPlayers, String worldPath) {
		this("0.0.0.0", 7777, maxPlayers, worldPath);
	}
	
	public TerrariaServer(int port, int maxPlayers, String worldPath) {
		this("0.0.0.0", port, maxPlayers, worldPath);
	}
	
	public TerrariaServer(String ip, int port, int maxPlayers, String worldPath) {
		try {
			this.server = new MultiClientSocketServer<>(ip, port, maxPlayers) { };
		} catch (Exception e) {
			LOGGER.severe("Error creating socket:");
			LOGGER.severe(e.getMessage());
		}
		
		this.serverIP = ip;
		this.serverPort = port;
		this.worldPath = worldPath;
	}
	
	public void start() {
		try {
			if (worldPath == null || !Paths.get(worldPath).toFile().exists()) {
				LOGGER.info("World path is not a valid path!");
				return;
			}
			
			LOGGER.info("Loading world...");
			world = TerrariaWorldFactory.load(Paths.get(worldPath));
			LOGGER.info("World has been loaded.");
			
			server.startServing();
			LOGGER.info("Server started on " + serverIP + ":" + serverPort);
			
			isRunning = true;
		} catch (IOException | AssertionError e) {
			LOGGER.severe("Failed to load world!");
		}
	}
	
	public void stop() {
		try {
			LOGGER.info("Stopping server...");
			server.stopServer();
			isRunning = false;
		} catch (IOException e) {
			LOGGER.info("Failed to stop server, force closing JVM >:)");
			e.printStackTrace(System.err);
			
			Runtime.getRuntime().exit(1);
		}
	}
}
