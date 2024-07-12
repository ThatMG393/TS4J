package com.thatmg393.ts4j.socket.packet.parser.base;

import com.thatmg393.ts4j.socket.packet.base.BasePacket;

import java.io.IOException;
import java.io.InputStream;

public interface BasePacketParser<P extends BasePacket> {
	public P parse(InputStream data) throws IOException;
}
