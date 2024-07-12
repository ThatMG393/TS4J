package com.thatmg393.ts4j.utils;
import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDUtil {
    public static UUID fromBytes(byte[] uuidBytes) {
		ByteBuffer b = ByteBuffer.wrap(uuidBytes);
		return new UUID(b.getLong(), b.getLong());
	}
}
