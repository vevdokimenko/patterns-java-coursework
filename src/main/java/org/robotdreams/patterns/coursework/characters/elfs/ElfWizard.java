package org.robotdreams.patterns.coursework.characters.elfs;

import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.Wizard;
import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.constants.Names;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.springframework.stereotype.Component;

@Component
public class ElfWizard extends Character implements Wizard {
    public ElfWizard() {
        name = Names.ELF_WIZARD_NAME;
        shootCaption = Names.GOOD_WIZARD_SHOOT;
        attackCaption = Names.GOOD_WIZARD_ATTACK;
        attackDamage = Constants.ELF_WIZARD_DAMAGE;
    }

    @Override
    public void magic(Character character) throws GameException {
        validateCharacterIsMy(character);
        if (!character.isPrivilege()) {
            character.setPrivilege(true);
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
