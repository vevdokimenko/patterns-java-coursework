package org.robotdreams.patterns.coursework.constants;

public interface Constants {
    int LIVES = 20;
    String DAMN = "↓";
    String PRIVILEGE = "↑";
    String UNDERSCORE = "_";
    String NEWLINE = "\n";


    int ELF_WIZARD_DAMAGE = 10;
    int ELF_SHOOT_DAMAGE = 7;
    int ELF_SHOOT_ATTACK = 3;
    int ELF_WARRIOR_DAMAGE = 15;


    int UNDEAD_WIZARD_DAMAGE = 5;
    int UNDEAD_SHOOTER_SHOOT = 4;
    int UNDEAD_SHOOTER_DAMAGE = 2;
    int UNDEAD_WARRIOR_DAMAGE = 18;


    int HUMAN_WIZARD_DAMAGE = 4;
    int HUMAN_SHOOTER_SHOOT = 5;
    int HUMAN_SHOOTER_DAMAGE = 3;
    int HUMAN_WARRIOR_DAMAGE = 18;


    int ORC_SHOOTER_SHOOT = 3;
    int ORC_SHOOTER_DAMAGE = 2;
    int ORC_WARRIOR_DAMAGE = 20;

    interface Error {
        String ERROR = "error";
        String CHARACTER_NOT_IN_ENEMY_TEAM = "Character is not in enemy team!";
        String CHARACTER_NOT_IN_YOUR_TEAM = "Character is not in your team!";
        String VICTIM_IS_NULL = "Victim is null";
        String YOU_SHOULD_CHOOSE_VICTIM = "You should choose a victim";
    }
}
