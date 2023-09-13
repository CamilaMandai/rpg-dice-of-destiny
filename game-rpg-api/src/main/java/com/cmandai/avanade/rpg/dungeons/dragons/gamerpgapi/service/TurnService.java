package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.BattleLogsDTO;

import java.util.List;

public interface TurnService {
    public List<Turn> saveMany(List<Turn> turns);

    public Turn findByRoundAndBattle(Integer round, Long battleId);

    public List<Turn> findAllByBattleId(Long id);
}
