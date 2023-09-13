package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.BattleLogsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoryService {
    public List<BattleLogsDTO> findAllBattlesWithTurns(Pageable pageable);
    public BattleLogsDTO findBattleByIdWithTurns(Long id);
    public Turn findBattleByIdAndTurnRound(Integer round, Long battleId);
}
