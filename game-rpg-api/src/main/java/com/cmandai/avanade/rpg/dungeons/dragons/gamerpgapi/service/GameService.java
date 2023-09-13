package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import org.springframework.transaction.annotation.Transactional;

public interface GameService {
    public Battle play(String playerName, Character iPlayer, Character iBot);
    public Long attack(Long battleId, Integer turnRound);
    public Long defend(Long battleId, Integer turnRound);
    public Long damage(Long battleId, Integer turnRound);

}
