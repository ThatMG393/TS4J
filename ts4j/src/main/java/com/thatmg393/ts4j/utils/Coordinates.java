package com.thatmg393.ts4j.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Coordinates {
	public static Coordinates fromReversed(int y, int x) {
		return new Coordinates(x, y);
	}
	
	private final int x;
	private final int y;

	public Coordinates(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

}