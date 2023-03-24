package org.robotdreams.patterns.coursework.characters;

import org.robotdreams.patterns.coursework.characters.elfs.ElfsTeamFactory;
import org.robotdreams.patterns.coursework.characters.humans.HumansTeamFactory;
import org.robotdreams.patterns.coursework.characters.orcs.OrcsTeamFactory;
import org.robotdreams.patterns.coursework.characters.undeads.UndeadsTeamFactory;
import org.robotdreams.patterns.coursework.types.Race;
import org.robotdreams.patterns.coursework.types.TeamType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TeamManager {
    private final UndeadsTeamFactory undeadsTeamFactory;
    private final ElfsTeamFactory elfsTeamFactory;
    private final HumansTeamFactory humansTeamFactory;
    private final OrcsTeamFactory orcsTeamFactory;
    private List<Character> myTeam;
    private List<Character> enemyTeam;
    private TeamType myTeamType;
    private Race myTeamRace;
    private Race enemyTeamRace;
    private int cursor;

    public TeamManager(UndeadsTeamFactory undeadsTeamFactory, ElfsTeamFactory elfsTeamFactory, HumansTeamFactory humansTeamFactory, OrcsTeamFactory orcsTeamFactory) {
        this.undeadsTeamFactory = undeadsTeamFactory;
        this.elfsTeamFactory = elfsTeamFactory;
        this.humansTeamFactory = humansTeamFactory;
        this.orcsTeamFactory = orcsTeamFactory;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public int getCursor() {
        return cursor;
    }

    public List<Character> getMyTeam() {
        return myTeam;
    }

    public List<Character> getEnemyTeam() {
        return enemyTeam;
    }

    public List<Character> createMyTeam(Race race) {
        List<Character> myTeamProto;
        List<Character> enemyTeamProto;
        switch (race) {
            case HUMAN -> {
                myTeamProto = humansTeamFactory.getTeam();
                myTeamType = TeamType.GOOD;
                myTeamRace = race;
            }
            case ELF -> {
                myTeamProto = elfsTeamFactory.getTeam();
                myTeamType = TeamType.GOOD;
                myTeamRace = race;
            }
            case ORC -> {
                myTeamProto = orcsTeamFactory.getTeam();
                myTeamType = TeamType.EVIL;
                myTeamRace = race;
            }
            case UNDEAD -> {
                myTeamProto = undeadsTeamFactory.getTeam();
                myTeamType = TeamType.EVIL;
                myTeamRace = race;
            }
            default -> {
                return null;
            }
        }

        enemyTeamProto = createEnemyTeam();

        myTeam = new ArrayList<>(myTeamProto.size());
        enemyTeam = new ArrayList<>(enemyTeamProto.size());

        myTeamProto.forEach(character -> myTeam.add(character.clone()));
        enemyTeamProto.forEach(character -> enemyTeam.add(character.clone()));

        myTeam.forEach(character -> {
            character.setMyTeam(myTeam);
            character.setEnemyTeam(enemyTeam);
        });

        enemyTeam.forEach(character -> {
            character.setMyTeam(enemyTeam);
            character.setEnemyTeam(myTeam);
        });

        return myTeam;
    }

    private List<Character> createEnemyTeam() {
        if (myTeamType == TeamType.EVIL) {
            if (getRandomBoolean()) {
                enemyTeamRace = Race.HUMAN;
                return humansTeamFactory.getTeam();
            } else {
                enemyTeamRace = Race.ELF;
                return elfsTeamFactory.getTeam();
            }
        } else {
            if (getRandomBoolean()) {
                enemyTeamRace = Race.ORC;
                return orcsTeamFactory.getTeam();
            } else {
                enemyTeamRace = Race.UNDEAD;
                return undeadsTeamFactory.getTeam();
            }
        }
    }

    private static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public Race getMyTeamRace() {
        return myTeamRace;
    }

    public Race getEnemyTeamRace() {
        return enemyTeamRace;
    }

    public Character getById(List<Character> team, int id) {
        for (Character character : team) {
            if (character.getId() == id) return character;
        }
        return null;
    }
}
