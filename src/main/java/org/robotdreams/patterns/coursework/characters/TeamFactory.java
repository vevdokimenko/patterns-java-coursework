package org.robotdreams.patterns.coursework.characters;

import java.util.ArrayList;
import java.util.List;

public interface TeamFactory {
    Character getWizard();
    List<? extends Character> getShooters();
    List<? extends Character> getWarriors();

    default List<Character> getTeam() {
        int counter = 1;
        List<Character> team = new ArrayList<>();

        Character wizard = getWizard();

        wizard.setId(counter);
        team.add(wizard);

        for (Character character : getShooters()) {
            character.setId(++counter);
            team.add(character);
        }

        for (Character character : getWarriors()) {
            character.setId(++counter);
            team.add(character);
        }

        return team;
    }
}
