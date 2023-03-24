package org.robotdreams.patterns.coursework.characters.humans;

import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.constants.Names;
import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.Wizard;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.springframework.stereotype.Component;

@Component
public class HumanWizard extends Character implements Wizard {
    public HumanWizard() {
        name = Names.HUMAN_WIZARD_NAME;
        shootCaption = Names.GOOD_WIZARD_SHOOT;
        attackCaption = Names.GOOD_WIZARD_ATTACK;
        attackDamage = Constants.HUMAN_WIZARD_DAMAGE; // атаковать магией (нанесение урона 4 HP)
    }

    @Override
    public void magic(Character character) throws GameException {
        validateCharacterIsMy(character);
        if (!character.isPrivilege()) {
            character.setPrivilege(true); // наложение улучшения на персонажа своего отряда
        }
        setCaption(shootCaption);
    }

    @Override
    public void attack(Character character) throws GameException {
        validateCharacterIsEnemy(character);
        setActionType(ActionType.ATTACK);
        character.setLives(character.getLives() - getAttackDamage());
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
