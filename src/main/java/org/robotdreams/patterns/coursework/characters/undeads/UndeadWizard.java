package org.robotdreams.patterns.coursework.characters.undeads;

import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.Wizard;
import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.constants.Names;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.springframework.stereotype.Component;

@Component
public class UndeadWizard extends Character implements Wizard {
    public UndeadWizard() {
        name = Names.UNDEAD_WIZARD_NAME;
        shootCaption = Names.UNDEAD_WIZARD_SHOOT;
        attackCaption = Names.UNDEAD_WIZARD_ATTACK;
        attackDamage = Constants.UNDEAD_WIZARD_DAMAGE; // атака (нанесение урона 5 HP)
    }

    @Override
    public void magic(Character character) throws GameException {
        validateCharacterIsEnemy(character);
        if (!character.isDamned())
            character.setDamned(true); // наслать недуг (уменьшение силы урона персонажа противника на 50% на один ход)
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
