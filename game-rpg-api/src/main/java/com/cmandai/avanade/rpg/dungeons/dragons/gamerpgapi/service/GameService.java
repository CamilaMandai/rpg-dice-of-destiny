package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import org.springframework.transaction.annotation.Transactional;

public interface GameService {
    public Battle play(String playerName, Character iPlayer, Character iBot);

    Long attack(Long battleId, Integer turnRound);
}
