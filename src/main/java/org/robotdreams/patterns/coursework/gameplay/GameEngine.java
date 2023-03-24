package org.robotdreams.patterns.coursework.gameplay;

import org.apache.commons.lang3.StringUtils;
import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.decorators.ExceptionDecorator;
import org.robotdreams.patterns.coursework.exceptions.ExecResult;
import org.robotdreams.patterns.coursework.exceptions.GameException;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameEngine {
    private static final Logger logger = LoggerFactory.getLogger(GameEngine.class);

    public List<String> turnNextAuto(List<Character> attackers, List<Character> victims) {
        List<String> result = new ArrayList<>();
        for (Character attacker : attackers) {
            ExceptionDecorator exceptionDecorator = new ExceptionDecorator(attacker);
            exceptionDecorator.setVictims(victims);
            Character victim = getRandomVictim(victims);
            ActionType actionType = getRandomActionType();
            if (victim != null) {
                try {
                    attacker.action(victim, actionType);
                } catch (GameException e) {
                    exceptionDecorator.action(victim, actionType);
                }
                result.add(getActionDescription(attacker, victim, actionType));
                logger.info(getActionDescription(attacker, victim, actionType));
                if (victim.getLives() <= 0) victims.remove(victim);
            } else {
                logger.warn("!!! Winners: {} !!!", stringify(attackers));
                break;
            }
        }
        return result;
    }

    public ExecResult turnNextManual(Character attacker, Character victim, ActionType type) {
        if (victim != null) {
            try {
                attacker.action(victim, type);
            } catch (GameException e) {
                logger.error(e.getMessage());
                return ExecResult.error(e.getMessage());
            }
            logger.info(getActionDescription(attacker, victim, type));
        } else {
            return ExecResult.error(Constants.Error.VICTIM_IS_NULL);
        }

        return ExecResult.success();
    }

    private String getActionDescription(Character attacker, Character victim, ActionType type) {
        int damage;
        if (type == ActionType.ATTACK) {
            damage = attacker.getAttackDamage();
        } else {
            damage = attacker.getShootDamage();
        }

        StringBuilder message = new StringBuilder();
        message.append(StringUtils.rightPad(attacker.toString(), 35));
        message.append(StringUtils.rightPad(attacker.getCaption(), 20));
        if (damage != 0) {
            message.append(StringUtils.rightPad("(" + damage + ")", 5));
        } else {
            message.append(StringUtils.rightPad(" ", 5, " "));
        }
        message.append("\t--->\t");
        message.append(StringUtils.rightPad(victim.toString(), 30));
        if (victim.getLives() <= 0) message.append("\t -X DEAD X-");

        return message.toString();
    }

    private Character getRandomVictim(List<Character> team) {
        if (team.size() > 0) {
            Character result = team.get(new Random().nextInt(team.size()));
            return (result.getLives() > 0 ? result : null);
        } else {
            return null;
        }
    }

    private ActionType getRandomActionType() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return ActionType.SHOOT;
        } else
            return ActionType.ATTACK;
    }

    private String stringify(List<Character> team) {
        StringJoiner sj = new StringJoiner(" | ");
        team.forEach(item -> sj.add(item.toString()));
        return Constants.NEWLINE + sj;
    }
}
