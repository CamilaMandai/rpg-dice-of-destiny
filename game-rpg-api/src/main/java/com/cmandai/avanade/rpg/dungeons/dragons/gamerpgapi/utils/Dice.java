package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;

import java.util.Random;

public class Dice {
    public static Integer roll(Integer diceQuantity, Integer diceSides) {
        Integer maxDrawnValue = diceQuantity * diceSides;
        Random random = new Random();
        return random.nextInt(maxDrawnValue - diceQuantity + 1) + diceQuantity;
    }
    public static Integer diceAttack(Long attackPoints, Character character){
        Long diceResult = attackPoints - character.getStrength() - character.getAgility();
        return diceResult.intValue();
    }
    public static Integer diceDefense(Long defensePoints, Character character){
        Long diceResult = defensePoints - character.getDefense() - character.getAgility();
        return diceResult.intValue();
    }
    public static Integer diceDamage(Long damagePoints, Character character){
        Long diceResult = damagePoints - character.getStrength();
        return diceResult.intValue();
    }
}
