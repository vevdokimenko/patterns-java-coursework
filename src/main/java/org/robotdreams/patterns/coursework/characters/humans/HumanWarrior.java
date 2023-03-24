package org.robotdreams.patterns.coursework.characters.humans;

import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.constants.Names;
import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.Warrior;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class HumanWarrior extends Character implements Warrior {
    public HumanWarrior() {
        name = Names.HUMAN_WARRIOR_NAME;
        attackCaption = Names.GOOD_WARRIOR_ATTACK;
        attackDamage = Constants.HUMAN_WARRIOR_DAMAGE;
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
