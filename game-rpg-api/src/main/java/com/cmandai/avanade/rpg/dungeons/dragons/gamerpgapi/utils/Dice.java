package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils;

import java.util.Random;

public class Dice {
    public static Integer roll(Integer diceQuantity, Integer diceSides) {
        Integer maxDrawnValue = diceQuantity * diceSides;
        Random random = new Random();
        return random.nextInt(maxDrawnValue - diceQuantity + 1) + diceQuantity;
    }
}
