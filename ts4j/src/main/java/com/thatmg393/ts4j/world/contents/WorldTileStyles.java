package com.thatmg393.ts4j.world.contents;

import com.thatmg393.ts4j.utils.FourPartSplit;
import com.thatmg393.ts4j.world.moon.MoonStyles;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WorldTileStyles {
    private final MoonStyles moonStyle;
    private final FourPartSplit treeStyles;
    private final FourPartSplit mossStyles;

	public WorldTileStyles(MoonStyles moonStyle, FourPartSplit treeStyles, FourPartSplit mossStyles) {
	    this.moonStyle = moonStyle;
	    this.treeStyles = treeStyles;
	    this.mossStyles = mossStyles;
	}
}
