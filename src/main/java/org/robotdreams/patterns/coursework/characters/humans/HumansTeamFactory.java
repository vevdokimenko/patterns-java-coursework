package org.robotdreams.patterns.coursework.characters.humans;

import org.robotdreams.patterns.coursework.characters.BaseTeamFactory;
import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.TeamFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HumansTeamFactory extends BaseTeamFactory implements TeamFactory {
    public HumansTeamFactory(HumanWizard wizard,
                             HumanShooter shooter1, HumanShooter shooter2, HumanShooter shooter3,
                             HumanWarrior warrior1, HumanWarrior warrior2, HumanWarrior warrior3, HumanWarrior warrior4) {
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
