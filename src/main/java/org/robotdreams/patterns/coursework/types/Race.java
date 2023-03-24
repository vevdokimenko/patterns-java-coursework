package org.robotdreams.patterns.coursework.types;

public enum Race {
    HUMAN(1),
    ELF(2),
    ORC(3),
    UNDEAD(4),
    UNDEFINED;

    private int id;
    Race(){}
    Race(int id) {
        this.id = id;
    }

    public static Race getRace(int id) {
        for (Race race : Race.values()) {
            if (id == race.id) return race;
        }

        return UNDEFINED;
    }
}
