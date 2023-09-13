package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository.TurnRepository;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.BattleLogsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service

public class TurnServiceImpl implements TurnService {

    private final TurnRepository turnRepository;
    private final BattleService battleService;

    @Transactional
    @Override
    public List<Turn> saveMany(List<Turn> turns) {
        return turnRepository.saveAll(turns);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Turn> findAllByBattleId(Long id) {
        return turnRepository.findAllByBattleId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Turn findByRoundAndBattle(Integer roundNumber, Long battleId) {
        Battle battle = battleService.findById(battleId);
        return turnRepository.findByRoundAndBattle(roundNumber, battle);
    }
}
