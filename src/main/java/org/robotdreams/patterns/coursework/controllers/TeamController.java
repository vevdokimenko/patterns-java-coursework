package org.robotdreams.patterns.coursework.controllers;

import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.TeamManager;
import org.robotdreams.patterns.coursework.types.Race;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TeamController extends BaseController {
    public TeamController(TeamManager teamManager) {
        super(teamManager);
    }

    @ModelAttribute
    @GetMapping("humans")
    public ModelAndView humans(ModelAndView modelAndView) {
        List<Character> myTeam = teamManager.createMyTeam(Race.HUMAN);
        List<Character> enemyTeam = teamManager.getEnemyTeam();
        teamManager.setCursor(0);
        return getModelAndView(modelAndView, myTeam, enemyTeam);
    }

    @ModelAttribute
    @GetMapping("/elfs")
    public ModelAndView elfs(ModelAndView modelAndView) {
        List<Character> myTeam = teamManager.createMyTeam(Race.ELF);
        List<Character> enemyTeam = teamManager.getEnemyTeam();
        teamManager.setCursor(0);
        return getModelAndView(modelAndView, myTeam, enemyTeam);
    }

    @ModelAttribute
    @GetMapping("/orcs")
    public ModelAndView orcs(ModelAndView modelAndView) {
        List<Character> myTeam = teamManager.createMyTeam(Race.ORC);
        List<Character> enemyTeam = teamManager.getEnemyTeam();
        teamManager.setCursor(0);
        return getModelAndView(modelAndView, myTeam, enemyTeam);
    }

    @ModelAttribute
    @GetMapping("/undeads")
    public ModelAndView undeads(ModelAndView modelAndView) {
        List<Character> myTeam = teamManager.createMyTeam(Race.UNDEAD);
        List<Character> enemyTeam = teamManager.getEnemyTeam();
        teamManager.setCursor(0);
        return getModelAndView(modelAndView, myTeam, enemyTeam);
    }
}
