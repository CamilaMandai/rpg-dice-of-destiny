package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;

import java.util.List;

public interface TurnService {
    public Turn save(Integer round, Long battleId);

    public Turn save(Turn turn);

    public Turn findByRoundAndBattle(Integer round, Long battleId);

    public List<Turn> findAll();
}
