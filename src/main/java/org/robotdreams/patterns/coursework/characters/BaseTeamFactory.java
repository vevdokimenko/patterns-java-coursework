package org.robotdreams.patterns.coursework.characters;

import java.util.ArrayList;
import java.util.List;

public class BaseTeamFactory {
    protected final Character wizard;
    protected final List<Character> shooters;
    protected final List<Character> warriors;
    public BaseTeamFactory(Character wizard,
                           Character shooter1, Character shooter2, Character shooter3,
                           Character warrior1, Character warrior2, Character warrior3, Character warrior4) {
        this.wizard = wizard;

        shooters = new ArrayList<>(3);
        shooters.add(shooter1);
        shooters.add(shooter2);
        shooters.add(shooter3);

        warriors = new ArrayList<>(4);
        warriors.add(warrior1);
        warriors.add(warrior2);
        warriors.add(warrior3);
        warriors.add(warrior4);
    }
}
