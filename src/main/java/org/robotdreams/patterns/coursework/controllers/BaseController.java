package org.robotdreams.patterns.coursework.controllers;

import lombok.AllArgsConstructor;
import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.TeamManager;
import org.robotdreams.patterns.coursework.constants.Names;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class BaseController {
    protected TeamManager teamManager;
    protected ModelAndView getModelAndView(ModelAndView modelAndView, List<Character> myTeam, List<Character> enemyTeam) {
        modelAndView.addObject(Names.ATTACKER, teamManager.getMyTeam().get(teamManager.getCursor()).getId());
        modelAndView.addObject(Names.MY_TEAM, myTeam);
        modelAndView.addObject(Names.ENEMY_TEAM, enemyTeam);
        modelAndView.addObject(Names.MY_TEAM_RACE, teamManager.getMyTeamRace());
        modelAndView.addObject(Names.ENEMY_TEAM_RACE, teamManager.getEnemyTeamRace());
        modelAndView.setViewName(Names.TEAM);
        return modelAndView;
    }
}
