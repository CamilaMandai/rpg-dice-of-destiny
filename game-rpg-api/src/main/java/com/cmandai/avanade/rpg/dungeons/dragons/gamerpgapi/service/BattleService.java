package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;

import java.util.List;

public interface BattleService {
    Battle save(String playerName, Long playerCharacterId, Long botCharacterId);
    Battle findById(Long id);
    List<Battle> findAll();
}
