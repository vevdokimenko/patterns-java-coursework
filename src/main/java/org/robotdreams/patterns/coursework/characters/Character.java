package org.robotdreams.patterns.coursework.characters;

import lombok.Getter;
import lombok.Setter;
import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class Character implements Cloneable {
    private int id;
    protected String name;
    protected String shootCaption;
    protected String attackCaption;
    protected List<Character> enemyTeam;
    protected List<Character> myTeam;
    private String caption;
    private int lives;
    protected int shootDamage;
    protected int attackDamage;
    private boolean isPrivilege;
    private boolean isDamned;
    private ActionType actionType;

    public Character() {
        lives = Constants.LIVES;
    }

    public abstract void action(Character character, ActionType type) throws GameException;

    public int getAttackDamage() {
        int damage = attackDamage;
        if (isPrivilege) damage = (int) (damage * 1.5);
        if (isDamned) damage = damage / 2;
        return damage;
    }

    public int getShootDamage() {
        int damage = shootDamage;
        if (isPrivilege) damage = (int) (damage * 1.5);
        if (isDamned) damage = damage / 2;
        return damage;
    }

    public void validateCharacterIsEnemy(Character character) throws GameException {
        if (getEnemyTeam().contains(character)) return;
        throw new GameException(Constants.Error.CHARACTER_NOT_IN_ENEMY_TEAM);
    }

    public void validateCharacterIsMy(Character character) throws GameException {
        if (getMyTeam().contains(character)) return;
        throw new GameException(Constants.Error.CHARACTER_NOT_IN_YOUR_TEAM);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(name);
        sb.append("#").append(id);
        if (isPrivilege) {
            sb.append(Constants.UNDERSCORE).append(Constants.PRIVILEGE).append(Constants.UNDERSCORE);
        }
        if (isDamned) {
            sb.append(Constants.UNDERSCORE).append(Constants.DAMN).append(Constants.UNDERSCORE);
        }
        sb.append("[").append(lives).append("HP]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id && name.equals(character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    protected Character clone() {
        Character cloned;
        try {
            cloned = (Character) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage());
        }
        return cloned;
    }
}
