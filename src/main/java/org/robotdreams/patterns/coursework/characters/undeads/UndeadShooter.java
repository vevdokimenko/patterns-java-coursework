package org.robotdreams.patterns.coursework.characters.undeads;

import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.Shooter;
import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.constants.Names;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UndeadShooter extends Character implements Shooter {
    public UndeadShooter() {
        name = Names.UNDEAD_SHOOTER_NAME;
        shootCaption = Names.UNDEAD_SHOOTER_SHOOT;
        attackCaption = Names.UNDEAD_SHOOTER_ATTACK;
        shootDamage = Constants.UNDEAD_SHOOTER_SHOOT; // стрелять из лука (нанесение урона 4 HP)
        attackDamage = Constants.UNDEAD_SHOOTER_DAMAGE; // атаковать (нанесение урона 2 HP)
    }

    @Override
    public void shoot(Character character) throws GameException {
        validateCharacterIsEnemy(character);
        setActionType(ActionType.SHOOT);
        character.setLives(character.getLives() - getShootDamage());
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
            shoot(character);
        }
    }
}
