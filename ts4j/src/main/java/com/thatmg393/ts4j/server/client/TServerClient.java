package com.thatmg393.ts4j.server.client;

import com.thatmg393.ts4j.server.networking.TPacket;
import com.thatmg393.ts4j.server.networking.TPacketParser;
import com.thatmg393.ts4j.socket.base.BaseServerClient;
import com.thatmg393.ts4j.socket.packet.parser.base.BasePacketParser;

import java.net.Socket;

public class TServerClient extends BaseServerClient<TPacket> {
	public TServerClient(Socket s, BasePacketParser<TPacket> pp) {
		super(s, pp);
	}
	
    @Override
    public void onRecievePacket(TPacket packet) {
        if (packet != null) {
			System.out.println(packet);
		}
    }
}
