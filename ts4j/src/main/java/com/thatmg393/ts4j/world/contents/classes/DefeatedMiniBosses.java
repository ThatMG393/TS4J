package com.thatmg393.ts4j.world.contents.classes;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DefeatedMiniBosses {
    private final boolean pumpking;
    private final boolean mourningWood;
    private final boolean iceQueen;
    private final boolean santaNK1;
    private final boolean everscream;

    public DefeatedMiniBosses(
            boolean pumpking,
            boolean mourningWood,
            boolean iceQueen,
            boolean santaNK1,
            boolean everscream) {
        this.pumpking = pumpking;
        this.mourningWood = mourningWood;
        this.iceQueen = iceQueen;
        this.santaNK1 = santaNK1;
        this.everscream = everscream;
    }
}
