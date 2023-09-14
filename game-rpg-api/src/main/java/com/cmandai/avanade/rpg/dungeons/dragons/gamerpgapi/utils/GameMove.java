package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.EntityNotFoundException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.*;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GameMove {
    public static void roundValidation(Integer turnRound, List<Turn> turns) {
        if (turnRound > turns.size()) {
            throw new EntityNotFoundException(
                    String.format("Game over at round %s", turns.size())
            );
        }
    }
    public static AttackReturnDTO getAttack(
            List<Turn> turns,
            Integer turnRound,
            Long attackPoints) {
        Battle battle = turns.get(0).getBattle();
        Battle.WhoStarts whoStarted = battle.getWhoStarts();
        Character whoStartedCharacter = whoStarted.equals(Battle.WhoStarts.PLAYER) ? battle.getPlayerCharacter() : battle.getBotCharacter();
        Character whoNotStartedCharacter = whoStarted.equals(Battle.WhoStarts.PLAYER) ? battle.getBotCharacter() : battle.getPlayerCharacter();
        if(turnRound%2!=0){
            return new AttackReturnDTO(whoStartedCharacter.getName(), Dice.diceAttack(attackPoints, whoStartedCharacter), attackPoints);
        } else{
            return new AttackReturnDTO(whoNotStartedCharacter.getName(), Dice.diceAttack(attackPoints, whoNotStartedCharacter), attackPoints);
        }
    }
    public static DefenseReturnDTO getDefense(
            List<Turn> turns,
            Integer turnRound,
            Long defensePoints) {
        Battle battle = turns.get(0).getBattle();
        Battle.WhoStarts whoStarted = battle.getWhoStarts();
        Character whoStartedCharacter = whoStarted.equals(Battle.WhoStarts.PLAYER) ? battle.getPlayerCharacter() : battle.getBotCharacter();
        Character whoNotStartedCharacter = whoStarted.equals(Battle.WhoStarts.PLAYER) ? battle.getBotCharacter() : battle.getPlayerCharacter();
        if(turnRound%2!=0){
            return new DefenseReturnDTO(whoNotStartedCharacter.getName(), Dice.diceDefense(defensePoints, whoNotStartedCharacter), defensePoints);
        } else{
            return new DefenseReturnDTO(whoStartedCharacter.getName(), Dice.diceDefense(defensePoints, whoStartedCharacter), defensePoints);
        }
    }
    public static DamageReturnDTO getDamage(
            List<Turn> turns,
            Integer turnRound,
            Long damagePoints) {
        Battle battle = turns.get(0).getBattle();
        Battle.WhoStarts whoStarted = battle.getWhoStarts();
        Character whoStartedCharacter = whoStarted.equals(Battle.WhoStarts.PLAYER) ? battle.getPlayerCharacter() : battle.getBotCharacter();
        Character whoNotStartedCharacter = whoStarted.equals(Battle.WhoStarts.PLAYER) ? battle.getBotCharacter() : battle.getPlayerCharacter();
        if(turnRound%2!=0){
            return new DamageReturnDTO(whoStartedCharacter.getName(), Dice.diceDamage(damagePoints, whoStartedCharacter), damagePoints);
        } else{
            return new DamageReturnDTO(whoNotStartedCharacter.getName(), Dice.diceDamage(damagePoints, whoNotStartedCharacter), damagePoints);
        }
    }
}
