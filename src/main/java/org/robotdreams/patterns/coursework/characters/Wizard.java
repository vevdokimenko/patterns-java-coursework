package org.robotdreams.patterns.coursework.characters;

import org.robotdreams.patterns.coursework.exceptions.GameException;

/**
 * Маг
 */
public interface Wizard {
    void magic(Character character) throws GameException;
    void attack(Character character) throws GameException;
}
