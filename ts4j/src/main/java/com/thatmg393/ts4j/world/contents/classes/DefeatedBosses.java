package com.thatmg393.ts4j.world.contents.classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class DefeatedBosses {
	private final boolean eyeOfCthulhu;
	private final boolean eaterOfWorlds;
	private final boolean skeletron;
	private final boolean queenBee;
	private final boolean theTwins;
	private final boolean theDestroyer;
	private final boolean skeletonPrime;
	private final boolean defeatedAtleastOneMechBoss;
	private final boolean plantera;
	private final boolean golem;
	private final boolean kingSlime;

	@Setter private boolean clown; // jesterpenetrate??

	@Setter private boolean dukeFishron;

	@Setter private boolean lunaticCultist;

	@Setter private boolean moonLord;

	public DefeatedBosses(
			boolean eyeOfCthulhu,
			boolean eaterOfWorlds,
			boolean skeletron,
			boolean queenBee,
			boolean theTwins,
			boolean theDestroyer,
			boolean skeletonPrime,
			boolean defeatedAtleastOneMechBoss,
			boolean plantera,
			boolean golem,
			boolean kingSlime) {
		this.eyeOfCthulhu = eyeOfCthulhu;
		this.eaterOfWorlds = eaterOfWorlds;
		this.skeletron = skeletron;
		this.queenBee = queenBee;
		this.theTwins = theTwins;
		this.theDestroyer = theDestroyer;
		this.skeletonPrime = skeletonPrime;
		this.defeatedAtleastOneMechBoss = defeatedAtleastOneMechBoss;
		this.plantera = plantera;
		this.golem = golem;
		this.kingSlime = kingSlime;
	}
}
