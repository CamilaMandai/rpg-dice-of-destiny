package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("battles")
public class BattleController {

    private final BattleService battleService;
//    @PostMapping
//    public BattleResponseDto save(@RequestBody BattleCreateDto battleDTO) {
//        Battle battle = battleService.save(
//                battleDTO.playerName(),
//                battleDTO.playerCharacterId(),
//                battleDTO.botCharacterId());
//        return BattleMapper.toDto(battle);
//    }

    @GetMapping
    public ResponseEntity<List<Battle>> getAll() {
        return ResponseEntity.ok(battleService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Battle> getById(@PathVariable Long id) {
        return ResponseEntity.ok(battleService.findById(id));
    }
}
