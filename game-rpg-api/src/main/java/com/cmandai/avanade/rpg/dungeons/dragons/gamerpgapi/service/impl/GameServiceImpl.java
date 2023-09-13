package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.EntityNotFoundException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.*;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.*;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils.Rand;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final BattleService battleService;
    private final TurnService turnService;
    private final CharacterService characterService;

    private Fighter player;
    private Fighter bot;
    private Battle battle;
    private List<Turn> turns = new ArrayList<Turn>();
    private Integer round=1;

    @Override
    public Battle play(String playerName, Character iPlayer, Character iBot) {
        player = new Fighter(iPlayer);
        bot = iBot == null ? new Fighter(Rand.randomMonster(characterService)) : new Fighter(iBot);
        battle = battleService.save(playerName, player.getCharacter(), bot.getCharacter());
        boolean playerStarts = player.rollDiceToStart()>bot.rollDiceToStart();
        battle.setWhoStarts(playerStarts ? Battle.WhoStarts.PLAYER : Battle.WhoStarts.BOT);
        if (playerStarts) {
            playGame(player, bot);
        } else {
            playGame(bot, player);
        }
        turnService.saveMany(turns);
        return battle;
    }

    @Override
    @Transactional(readOnly = true)
    public Long attack(Long battleId, Integer turnRound) {
        List<Turn> turns = turnService.findAllByBattleId(battleId);
        if(turnRound > turns.size()){
            throw new EntityNotFoundException(
                    String.format("Game over at round %s", turns.size())
            );
        }
        return turnService.findByRoundAndBattle(turnRound, battleId).getAtackPoints();
    }


    @Override
    @Transactional(readOnly = true)
    public Long defend(Long battleId, Integer turnRound) {
        List<Turn> turns = turnService.findAllByBattleId(battleId);
        if(turnRound > turns.size()){
            throw new EntityNotFoundException(
                    String.format("Game over at round %s", turns.size())
            );
        }
        return turnService.findByRoundAndBattle(turnRound, battleId).getDefensePoints();
    }

    @Override
    @Transactional(readOnly = true)
    public Long damage(Long battleId, Integer turnRound) {
        List<Turn> turns = turnService.findAllByBattleId(battleId);
        if(turnRound > turns.size()){
            throw new EntityNotFoundException(
                    String.format("Game over at round %s", turns.size())
            );
        }
        return turnService.findByRoundAndBattle(turnRound, battleId).getDamage();
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
