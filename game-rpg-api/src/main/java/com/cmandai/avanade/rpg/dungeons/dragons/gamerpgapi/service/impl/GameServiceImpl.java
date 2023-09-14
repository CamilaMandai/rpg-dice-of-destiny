package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.EntityNotFoundException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.*;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.*;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.AttackReturnDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.CreatedBattleDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.DamageReturnDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.DefenseReturnDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils.Dice;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils.GameMove;
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
    public CreatedBattleDTO play(String playerName, Character iPlayer, Character iBot) {
        player = new Fighter(iPlayer);
        bot = iBot == null ? new Fighter(Rand.randomMonster(characterService)) : new Fighter(iBot);
        battle = battleService.save(playerName, player.getCharacter(), bot.getCharacter());
        Integer playerDice = player.rollDiceToStart();
        Integer botDice = bot.rollDiceToStart();
        boolean playerStarts = playerDice>botDice;
        battle.setWhoStarts(playerStarts ? Battle.WhoStarts.PLAYER : Battle.WhoStarts.BOT);
        if (playerStarts) {
            playGame(player, bot);
        } else {
            playGame(bot, player);
        }
        turnService.saveMany(turns);
        return new CreatedBattleDTO(playerDice, botDice, battle);
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

    @Override
    @Transactional(readOnly = true)
    public AttackReturnDTO attack(Long battleId, Integer turnRound) {
        List<Turn> turns = turnService.findAllByBattleId(battleId);
        GameMove.roundValidation(turnRound, turns);
        Turn turn = turns.stream().filter(t -> t.getRound() == turnRound).findFirst().get();
        return GameMove.getAttack(turns,turnRound, turn.getAtackPoints());
    }

    @Override
    @Transactional(readOnly = true)
    public DefenseReturnDTO defend(Long battleId, Integer turnRound) {
        List<Turn> turns = turnService.findAllByBattleId(battleId);
        GameMove.roundValidation(turnRound, turns);
        Turn turn = turns.stream().filter(t -> t.getRound() == turnRound).findFirst().get();
        return GameMove.getDefense(turns, turnRound, turn.getDefensePoints());
    }

    @Override
    @Transactional(readOnly = true)
    public DamageReturnDTO damage(Long battleId, Integer turnRound) {
        List<Turn> turns = turnService.findAllByBattleId(battleId);
        GameMove.roundValidation(turnRound, turns);
        Turn turn = turns.stream().filter(t -> t.getRound() == turnRound).findFirst().get();
        return GameMove.getDamage(turns, turnRound, turn.getDamage());
    }

}
