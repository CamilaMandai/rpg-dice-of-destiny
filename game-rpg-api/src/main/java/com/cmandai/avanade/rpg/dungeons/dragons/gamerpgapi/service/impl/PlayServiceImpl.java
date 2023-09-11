package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Fighter;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.PlayService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayServiceImpl implements PlayService {

    private final BattleService battleService;
    private final TurnService turnService;

    private Fighter player;
    private Fighter bot;
    private Battle battle;
    private List<Turn> turns = new ArrayList<Turn>();
    private Integer round;

    @Override
    public Battle startGame(String playerName, Character iPlayer, Character iBot) {
        player = new Fighter(iPlayer);
        bot = new Fighter(iBot);
        round=1;
        battle = battleService.save(playerName, player.getCharacter(), bot.getCharacter());
        boolean playerStarts = player.rollDiceToStart()>bot.rollDiceToStart();
        battle.setWhoStarts(playerStarts ? Battle.WhoStarts.PLAYER : Battle.WhoStarts.BOT);
        if (playerStarts) {
            playGame(player, bot);
        } else {
            playGame(bot, player);
        }
        turnService.saveAll(turns);
        return battle;
    }

    private void playGame(Fighter firstPlayer, Fighter secondPlayer) {
        do {
            takeTurn(firstPlayer, secondPlayer);
            if (isGameOver()) {
                break;
            }
            takeTurn(secondPlayer, firstPlayer);
        } while (!isGameOver());
    }

    private void takeTurn(Fighter attacker, Fighter defender){
        Long attack = attacker.attack();
        Long defense = defender.defend();
        Long damagePoints = (attack > defense) ? attacker.causeDamage() : 0L;
        defender.loseLifePoints(damagePoints);
        addTurn(attack, defense, damagePoints, attacker.getCharacter().getName());
        round+=1;
    }

    private void addTurn(Long attack, Long defense, Long damagePoints, String characterName) {
        Turn turn = new Turn(round, attack, defense,damagePoints,battle);
        turns.add(turn);
    }

    private boolean isGameOver() {
        return player.getLifePoints() == 0 || bot.getLifePoints() == 0;
    }

}
