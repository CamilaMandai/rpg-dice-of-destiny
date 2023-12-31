package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.HistoryService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.BattleLogsDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.characterDTO.CharacterResponseDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.mapper.CharacterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("history-log")
public class HistoryController {

    private final HistoryService historyService;
    private final BattleService battleService;
    private final TurnService turnService;

    @GetMapping("all-battles")
    public ResponseEntity<List<Battle>> getAllBattles() {
        List<Battle> battles = battleService.findAll();
        return ResponseEntity.ok(battles);
    }

    @GetMapping("/battles")
    public ResponseEntity<Page<Battle>> getBattles(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(battleService.findAllBattlesByPage(pageable));
    }

    @GetMapping("battles/{id}")
    public ResponseEntity<Battle> getBattleById(@PathVariable Long id) {
        return ResponseEntity.ok(battleService.findById(id));
    }

    @GetMapping("battles/turns")
    public ResponseEntity<List<BattleLogsDTO>> getAllBattlesWithTurns(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(historyService.findAllBattlesWithTurns(pageable));
    }

    @GetMapping("battles/{id}/turns")
    public ResponseEntity<BattleLogsDTO> getRoundsByBattleId(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.findBattleByIdWithTurns(id));
    }

    @GetMapping("battles/{battleId}/turn-round/{roundNumber}")
    public Turn getByRoundAndBattle(@PathVariable Long battleId, @PathVariable Integer roundNumber) {
        return historyService.findBattleByIdAndTurnRound(roundNumber, battleId);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Battle>> getByNameTerm(@RequestParam(name = "player-name") String nameTerm) {
        List<Battle> battles = battleService.searchByPlayerName(nameTerm);
        return ResponseEntity.ok(battles);
    }
}
