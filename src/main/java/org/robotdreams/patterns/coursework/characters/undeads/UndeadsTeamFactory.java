package org.robotdreams.patterns.coursework.characters.undeads;

import org.robotdreams.patterns.coursework.characters.BaseTeamFactory;
import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.TeamFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UndeadsTeamFactory extends BaseTeamFactory implements TeamFactory {
    public UndeadsTeamFactory(UndeadWizard wizard,
                              UndeadShooter shooter1, UndeadShooter shooter2, UndeadShooter shooter3,
                              UndeadWarrior warrior1, UndeadWarrior warrior2, UndeadWarrior warrior3, UndeadWarrior warrior4) {
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
