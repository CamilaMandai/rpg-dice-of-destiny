package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository.TurnRepository;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class TurnServiceImpl implements TurnService {

    private final TurnRepository turnRepository;
    private final BattleService battleService;

    @Override
    public Turn save(Integer round, Long battleid) {
        Battle battle = battleService.findById(battleid);
        Turn turn = new Turn();
        turn.setBattle(battle);
        turn.setRound(round);
        return turnRepository.save(turn);
    }

    @Override
    public Turn save(Turn turn) {
        return turnRepository.save(turn);
    }

    @Override
    public List<Turn> saveAll(List<Turn> turns) {
        return turnRepository.saveAll(turns);
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
