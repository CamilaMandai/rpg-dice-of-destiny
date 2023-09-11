package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;

import java.util.List;

public interface CharacterService {
    public Character save(Character character);
    public Character findById(Long id);
    public List<Character> findAll();
    public List<Character> findAllMonsters();
    public Character update(Long id, Character character);
    public void delete(Long id);
}
