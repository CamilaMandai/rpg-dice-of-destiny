package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository.TurnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class TurnServiceImpl implements TurnService{

    private final TurnRepository turnRepository;
    private final BattleService battleService;

    @Override
    public Turn save(Integer round, Long battleid) {
        Battle battle = battleService.findById(battleid);
        return turnRepository.save(new Turn(round, battle));
    }

    @Override
    public List<Turn> findAll() {
        return turnRepository.findAll();
    }

    @Override
    public Turn findByRoundAndBattle(Integer round, Long battleId) {
        Battle battle = battleService.findById(battleId);
        return turnRepository.findByRoundAndBattle(round, battle);
    }
}
