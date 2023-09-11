package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Fighter;

public interface PlayService {
    public Fighter startGame(String playerName, Fighter player, Fighter bot);
}
