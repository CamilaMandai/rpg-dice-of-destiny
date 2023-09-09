package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;

import java.util.List;

public interface CharacterService {
    Character save(Character character);
    Character findById(Long id);
    List<Character> findAll();
    Character update(Long id, Character character);
    void delete(Long id);
}
