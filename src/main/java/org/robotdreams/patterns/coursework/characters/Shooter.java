package org.robotdreams.patterns.coursework.characters;

import org.robotdreams.patterns.coursework.exceptions.GameException;

/**
 * Стрелок
 */
public interface Shooter {
    void shoot(Character character) throws GameException;
    void attack(Character character) throws GameException;
}
