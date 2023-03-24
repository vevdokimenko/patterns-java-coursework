package org.robotdreams.patterns.coursework.characters.orcs;

import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.Wizard;
import org.robotdreams.patterns.coursework.constants.Names;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.springframework.stereotype.Component;

@Component
public class OrcWizard extends Character implements Wizard {
    public OrcWizard() {
        name = Names.ORC_WIZARD_NAME;
        shootCaption = Names.ORC_WIZARD_SHOOT;
        attackCaption = Names.ORC_WIZARD_ATTACK;
    }

    @Override
    public void magic(Character character) throws GameException {
        validateCharacterIsMy(character);
        if (!character.isPrivilege()) {
            character.setPrivilege(true); // наложение улучшения на персонажа своего отряда.
        }
        setCaption(shootCaption);
    }

    @Override
    public void attack(Character character) throws GameException {
        validateCharacterIsEnemy(character);
        if (character.isPrivilege()) {
            character.setPrivilege(false); // наложение проклятия (снятие улучшения с персонажа противника для следующего хода)
        }
        setCaption(attackCaption);
    }

    @Override
    public void action(Character character, ActionType type) throws GameException {
        if (type == ActionType.ATTACK) {
            attack(character);
        } else {
            magic(character);
        }
    }
}
