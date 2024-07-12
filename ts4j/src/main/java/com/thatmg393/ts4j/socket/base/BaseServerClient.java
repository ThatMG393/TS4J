package com.thatmg393.ts4j.socket.base;

import com.thatmg393.ts4j.socket.packet.base.BasePacket;
import com.thatmg393.ts4j.socket.packet.parser.base.BasePacketParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class BaseServerClient<P extends BasePacket> implements Runnable {
	public Socket client;
	
	private BasePacketParser<P> packetParser;
	private InputStream out;
	private OutputStream in;

	public BaseServerClient(Socket client, BasePacketParser<P> packetParser) {
		this.client = client;
		this.packetParser = packetParser;
		
		try {
			this.out = client.getInputStream();
			this.in = client.getOutputStream();
		} catch (IOException e) { }
	}
	
	public abstract void onRecievePacket(P packet);
	
	@Override
	public final void run() {
		while (!Thread.interrupted()) {
			try {
				onRecievePacket(packetParser.parse(out));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
