package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;

import java.util.List;

public interface BattleService {
    public Battle save(String playerName, Long playerCharacterId, Long botCharacterId);
    public Battle save(String playerName, Character playerCharacter, Character botCharacter);
    public Battle findById(Long id);
    public List<Battle> findAll();
}
