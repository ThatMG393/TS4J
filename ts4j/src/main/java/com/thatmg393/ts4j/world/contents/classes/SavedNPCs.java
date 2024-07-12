package com.thatmg393.ts4j.world.contents.classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class SavedNPCs {
    private final boolean isGoblinTinkererSaved;
    private final boolean isWizardSaved;
    private final boolean isMechanicSaved;

    @Setter private boolean isAnglerSaved;

    @Setter private boolean isStylistSaved;

    @Setter private boolean isTaxCollectorSaved;
	
	@Setter private boolean isGolferSaved;
	
	@Setter private boolean isBartenderSaved;

    public SavedNPCs(
		boolean isGoblinTinkererSaved,
		boolean isWizardSaved,
		boolean isMechanicSaved
	) {
        this.isGoblinTinkererSaved = isGoblinTinkererSaved;
        this.isWizardSaved = isWizardSaved;
        this.isMechanicSaved = isMechanicSaved;
    }
}
