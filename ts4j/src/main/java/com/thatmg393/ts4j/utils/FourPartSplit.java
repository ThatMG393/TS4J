package com.thatmg393.ts4j.utils;

import lombok.ToString;

import org.apache.commons.lang3.tuple.Triple;

// https://github.com/Steffo99/lihzahrd/tree/master/lihzahrd/header/fourpartsplit.py#L4

@ToString
public class FourPartSplit {
	private final Triple<Integer, Integer, Integer> separators;
	private final Quadruple<Integer, Integer, Integer, Integer> properties;

	public FourPartSplit(
		Triple<Integer, Integer, Integer> separators,
		Quadruple<Integer, Integer, Integer, Integer> properties
	) {
		this.separators = separators;
		this.properties = properties;
	}

	public int getFarLeft() {
		return properties.getLeft();
	}

	public int getFarRight() {
		return properties.getBottom();
	}

	public int getNearbyLeft() {
		return properties.getRight();
	}

	public int getNearbyRight() {
		return properties.getTop();
	}

	public int getPropertyAt(int x) {
		if (x < separators.getLeft()) return getFarLeft();
		else if (x < separators.getMiddle()) return getNearbyLeft();
		else if (x < separators.getRight()) return getNearbyRight();
		else return getFarRight();
	}
}
