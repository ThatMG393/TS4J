package com.thatmg393.ts4j.world.npc.angler;

import java.util.ArrayList;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AnglerQuest {
    private final AnglerQuestFish questFish;
    private final ArrayList<String> questCompleters;

    public AnglerQuest(AnglerQuestFish questFish, ArrayList<String> questCompleters) {
        this.questFish = questFish;
        this.questCompleters = questCompleters;
    }
}
