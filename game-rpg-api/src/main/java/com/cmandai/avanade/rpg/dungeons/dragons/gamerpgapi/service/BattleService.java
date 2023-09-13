package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BattleService {
    public Battle save(String playerName, Long playerCharacterId, Long botCharacterId);
    public Battle save(String playerName, Character playerCharacter, Character botCharacter);
    public Battle save(Battle battle);
    public Battle findById(Long id);
    public List<Battle> findAll();
    public Page<Battle> findAllBattlesByPage(Pageable pageable);
    public List<Battle> searchByPlayerName(String playerName);
}
