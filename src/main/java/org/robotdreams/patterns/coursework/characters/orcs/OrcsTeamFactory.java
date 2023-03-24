package org.robotdreams.patterns.coursework.characters.orcs;

import org.robotdreams.patterns.coursework.characters.BaseTeamFactory;
import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.TeamFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrcsTeamFactory extends BaseTeamFactory implements TeamFactory {
    public OrcsTeamFactory(OrcWizard wizard,
                           OrcShooter shooter1, OrcShooter shooter2, OrcShooter shooter3,
                           OrcWarrior warrior1, OrcWarrior warrior2, OrcWarrior warrior3, OrcWarrior warrior4) {
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
