package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository.TurnRepository;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.TurnCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("turns")
public class TurnController {
    private final TurnService turnService;

    @PostMapping
    public Turn save(@RequestBody TurnCreateDto turnDTO) {
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
}
