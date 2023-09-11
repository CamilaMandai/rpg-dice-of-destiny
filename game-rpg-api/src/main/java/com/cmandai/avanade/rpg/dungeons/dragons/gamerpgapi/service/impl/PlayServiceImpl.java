package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Fighter;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.PlayService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayServiceImpl implements PlayService {

    private final BattleService battleService;
    private final TurnService turnService;

    private final Fighter player;
    private final Fighter bot;
    private Battle battle;
    private Integer round;

    @Override
    public Fighter startGame(String playerName, Fighter iPlayer, Fighter iBot) {
        player.setLifePoints(iPlayer.getCharacter().getLifePoints());
        player.setCharacter(iPlayer.getCharacter());
        bot.setLifePoints(iBot.getCharacter().getLifePoints());
        bot.setCharacter(iBot.getCharacter());
        round=1;
        battle = battleService.save(playerName, player.getCharacter(), bot.getCharacter());
        boolean playerStarts = player.rollDiceToStart()>bot.rollDiceToStart();
        battle.setWhoStarts(playerStarts ? Battle.WhoStarts.PLAYER : Battle.WhoStarts.BOT);
        if (playerStarts) {
            playGame(player, bot);
        } else {
            playGame(bot, player);
        }
        return whoLost();
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
        saveTurn(attack, defense, damagePoints);
        round+=1;
    }

    private void saveTurn(Long attack, Long defense, Long damagePoints) {
        Turn turn = new Turn(round, battle);
        turn.setAtackPoints(attack);
        turn.setDefensePoints(defense);
        turn.setDamage(damagePoints);
        turnService.save(turn);
    }

    private boolean isGameOver() {
        return player.getLifePoints() <= 0 || bot.getLifePoints() <= 0;
    }

    private Fighter whoLost() {
        if (player.getLifePoints() <= 0) {
            player.setLifePoints(0L);
            return player;
        }
        bot.setLifePoints(0L);
        return bot;
    }
}
