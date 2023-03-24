package org.robotdreams.patterns.coursework.characters.undeads;

import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.Warrior;
import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.constants.Names;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UndeadWarrior extends Character implements Warrior {
    public UndeadWarrior() {
        name = Names.UNDEAD_WARRIOR_NAME;
        attackCaption = Names.UNDEAD_WARRIOR_ATTACK;
        attackDamage = Constants.UNDEAD_WARRIOR_DAMAGE; // удар копьем (нанесение урона 18 HP)
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
        attack(character);
    }
}
