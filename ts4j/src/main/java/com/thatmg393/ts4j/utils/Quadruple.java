package com.thatmg393.ts4j.utils;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Quadruple<L, R, T, B> implements Serializable, Comparable<Quadruple<L, R, T, B>> {
    private final L left;
    private final R right;
    private final T top;
    private final B bottom;

    public Quadruple(L left, R right, T top, B bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public int compareTo(Quadruple<L, R, T, B> o) {
        return this.equals(o) ? 1 : 0;
    }
}
