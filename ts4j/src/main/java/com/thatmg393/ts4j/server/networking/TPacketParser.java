package com.thatmg393.ts4j.server.networking;

import com.thatmg393.ts4j.socket.packet.parser.base.BasePacketParser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class TPacketParser implements BasePacketParser<TPacket> {
	private static final Logger LOGGER = Logger.getLogger("TPacketParser");
	public static final int BUFFER_SIZE = 1024;
	
	@Override
	public TPacket parse(InputStream dataStream) throws IOException {
		int length = ensureNotEOS(dataStream.read());
		int type = ensureNotEOS(dataStream.read()) & 0xFF;
		byte[] payload = dataStream.readNBytes(length);
		
		LOGGER.info("L: " + length + ", T: " + type + ", D: " + new String(payload, Charset.forName("UTF-8")));
		
		return null;
	}
	
	private int ensureNotEOS(int result) {
		return (result == -1) ? result : result;
	}
}
