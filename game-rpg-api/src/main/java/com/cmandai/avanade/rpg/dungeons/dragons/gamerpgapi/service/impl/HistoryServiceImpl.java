package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.HistoryService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.BattleLogsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final TurnService turnService;
    private final BattleService battleService;

    @Transactional(readOnly = true)
    @Override
    public List<BattleLogsDTO> findAllBattlesWithTurns() {
        List<Battle> battles = battleService.findAll();
        List<BattleLogsDTO> turns =
                battles.stream()
                        .map(battle -> findBattleByIdWithTurns(battle.getId()))
                        .collect(Collectors.toList());
        return turns;
    }

    @Transactional(readOnly = true)
    @Override
    public BattleLogsDTO findBattleByIdWithTurns(Long id) {
        Battle battle = battleService.findById(id);
        List<Turn> turns = turnService.findAllByBattleId(id);
        return new BattleLogsDTO(battle, turns);
    }
    @Transactional(readOnly = true)
    @Override
    public Turn findBattleByIdAndTurnById(Integer round, Long battleId) {
        return turnService.findByRoundAndBattle(round, battleId);
    }



    }
