package org.robotdreams.patterns.coursework.characters.elfs;

import org.robotdreams.patterns.coursework.characters.BaseTeamFactory;
import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.TeamFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElfsTeamFactory extends BaseTeamFactory implements TeamFactory {

    public ElfsTeamFactory(ElfWizard wizard,
                           ElfShooter shooter1, ElfShooter shooter2, ElfShooter shooter3,
                           ElfWarrior warrior1, ElfWarrior warrior2, ElfWarrior warrior3, ElfWarrior warrior4) {
        super(wizard, shooter1, shooter2, shooter3, warrior1, warrior2, warrior3, warrior4);
    }

    @Override
    public Character getWizard() {
        return wizard;
    }

    @Override
    public List<? extends Character> getShooters() {
        return shooters;
    }

    @Override
    public List<? extends Character> getWarriors() {
        return warriors;
    }
}
