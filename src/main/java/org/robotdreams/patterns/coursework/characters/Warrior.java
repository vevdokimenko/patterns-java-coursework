package org.robotdreams.patterns.coursework.characters;

import org.robotdreams.patterns.coursework.exceptions.GameException;

/**
 * Воин
 */
public interface Warrior {
    void attack(Character character) throws GameException;
}
