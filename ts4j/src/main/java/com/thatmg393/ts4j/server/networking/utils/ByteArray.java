package com.thatmg393.ts4j.server.networking.utils;

import java.util.List;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class ByteArray {
	private final List<Byte> data;
	private int offset = 0;
	
	public ByteArray(Byte[] data) {
		this.data = Arrays.asList(data);
	}
	
	public void moveOffset(int newOffset) {
		this.offset = newOffset;
	}
	
	public byte[] getNBytes(int n) {
		byte[] b = new byte[n];
		
		for (int i = 0; i < n; i++) {
			b[i] = data.get(offset + i);
		}
		
		offset += n;
		return b;
	}
}
