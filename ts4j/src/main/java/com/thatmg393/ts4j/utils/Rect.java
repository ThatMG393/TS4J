package com.thatmg393.ts4j.utils;

import lombok.ToString;

@ToString
public class Rect {
    public final int left;
    public final int right;
    public final int top;
    public final int bottom;

    public Rect(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
}
