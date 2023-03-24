package org.robotdreams.patterns.coursework.controllers;

import org.robotdreams.patterns.coursework.characters.Character;
import org.robotdreams.patterns.coursework.characters.TeamManager;
import org.robotdreams.patterns.coursework.constants.Constants;
import org.robotdreams.patterns.coursework.constants.Names;
import org.robotdreams.patterns.coursework.exceptions.ExecResult;
import org.robotdreams.patterns.coursework.gameplay.GameEngine;
import org.robotdreams.patterns.coursework.types.ActionType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BattleController extends BaseController {
    private final GameEngine gameEngine;
    public BattleController(TeamManager teamManager, GameEngine gameEngine) {
        super(teamManager);
        this.gameEngine = gameEngine;
    }

    @PostMapping(value = "battle", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView turn(ModelAndView modelAndView,
                             @RequestBody MultiValueMap<String, String> formData) {
        List<Character> myTeam = teamManager.getMyTeam();
        List<Character> enemyTeam = teamManager.getEnemyTeam();

        try {
            int attackerId = Integer.parseInt(formData.get(Names.ATTACKER).get(0));
            Character attacker = teamManager.getById(myTeam, attackerId);

            if (!formData.containsKey(Names.VICTIM) && !formData.containsKey(Names.VICTIM_MY)) {
                modelAndView.addObject(Constants.Error.ERROR, Constants.Error.YOU_SHOULD_CHOOSE_VICTIM);
                return getModelAndView(modelAndView, myTeam, enemyTeam);
            }

            Character victim;
            if (formData.containsKey(Names.VICTIM)) {
                int enemyId = Integer.parseInt(formData.get(Names.VICTIM).get(0));
                victim = teamManager.getById(enemyTeam, enemyId);
            } else {
                int enemyId = Integer.parseInt(formData.get(Names.VICTIM_MY).get(0));
                victim = teamManager.getById(myTeam, enemyId);
            }

            ActionType actionType = ActionType.valueOf(formData.get(Names.ACTION).get(0));

            ExecResult execResult = gameEngine.turnNextManual(attacker, victim, actionType);

            if (!execResult.isSuccess()) {
                modelAndView.addObject(Constants.Error.ERROR, execResult.getMessage());
                return getModelAndView(modelAndView, myTeam, enemyTeam);
            }

            if (victim.getLives() <= 0) enemyTeam.remove(victim);

            int cursor = teamManager.getCursor();
            if (cursor < myTeam.size() - 1) {
                teamManager.setCursor(++cursor);
            } else {
                teamManager.setCursor(0);
                List<String> enemyTurn = gameEngine.turnNextAuto(enemyTeam, myTeam);
                modelAndView.addObject(Names.ENEMY_TURN, enemyTurn);
            }

            if (enemyTeam.size() == 0) {
                modelAndView.setViewName(Names.FINISH);
                modelAndView.addObject(Names.WINNER, teamManager.getMyTeamRace());
                return modelAndView;
            }

            if (myTeam.size() == 0) {
                modelAndView.setViewName(Names.FINISH);
                modelAndView.addObject(Names.WINNER, teamManager.getEnemyTeamRace());
                return modelAndView;
            }

            return getModelAndView(modelAndView, myTeam, enemyTeam);
        } catch (Exception e) {
            modelAndView.addObject(Constants.Error.ERROR, e.getMessage());
            return getModelAndView(modelAndView, myTeam, enemyTeam);
        }
    }
}
