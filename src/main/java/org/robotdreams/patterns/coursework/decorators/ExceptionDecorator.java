package org.robotdreams.patterns.coursework.decorators;

import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;

import java.util.List;
import java.util.Random;

public class ExceptionDecorator extends Character {
    private final Character decorated;
    private List<Character> victims;

    public ExceptionDecorator(Character decorated) {
        this.decorated = decorated;
    }

    public void setVictims(List<Character> victims) {
        this.victims = victims;
    }

    @Override
    public void action(Character character, ActionType type) {
        boolean hasError;
        do {
            try {
                this.decorated.action(character, type);
                hasError = false;
            } catch (GameException e) {
                character = victims.get(new Random().nextInt(victims.size()));
                hasError = true;
                if (new Random().nextBoolean()) {
                    type = ActionType.SHOOT;
                } else
                    type = ActionType.ATTACK;
            }
        }
        while (hasError);
    }
}
