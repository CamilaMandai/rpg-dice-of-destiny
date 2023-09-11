package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.BattleLogsDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.TurnCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("turns")
public class TurnController {
    private final TurnService turnService;

    @PostMapping
    public Turn save(@RequestBody TurnCreateDTO turnDTO) {
        return turnService.save(turnDTO.round(), turnDTO.battleId());
    }

    @GetMapping
    public List<Turn> getAll() {
        return turnService.findAll();
    }

    @GetMapping("{battleId}/{roundNumber}")
    public Turn getByRoundAndBattle(@PathVariable Long battleId, @PathVariable Integer roundNumber) {
        return turnService.findByRoundAndBattle(roundNumber, battleId);
    }

    @GetMapping("{battleId}")
    public ResponseEntity<BattleLogsDTO> getRoundsByBattleId(@PathVariable Long battleId) {
        return ResponseEntity.ok(turnService.findTurnsByBattleId(battleId));
    }

    @GetMapping("battles")
    public ResponseEntity<List<BattleLogsDTO>> getAllBattlesWithTurns() {
        return ResponseEntity.ok(turnService.findAllWithBattle());
    }
}
