package com.thatmg393.ts4j.server.networking;

import com.thatmg393.ts4j.socket.packet.base.BasePacket;
import com.thatmg393.ts4j.utils.BinaryReader;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TPacket extends BasePacket {
	public final char length;
	public final short type;
	public final BinaryReader data;

	public TPacket(char length, short type, BinaryReader data) {
		this.length = length;
		this.type = type;
		this.data = data;
	}
}
