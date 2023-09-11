package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;

import java.util.List;
import java.util.Random;

public class Rand {
    public static Character randomMonster(CharacterService service) {
        List<Character> monsters = service.findAllMonsters();
        Random random = new Random();
        int randomIndex = random.nextInt(monsters.size());
        return monsters.get(randomIndex);
    }
}
