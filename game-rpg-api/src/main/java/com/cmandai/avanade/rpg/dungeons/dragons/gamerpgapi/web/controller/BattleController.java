package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils.Dice;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.BattleCreateDto;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.BattleResponseDto;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.mapper.BattleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("battles")
public class BattleController {

    private final BattleService battleService;
    @PostMapping
    public BattleResponseDto save(@RequestBody BattleCreateDto battleDTO) {
        Battle battle = battleService.save(
                battleDTO.playerName(),
                battleDTO.playerCharacterId(),
                battleDTO.botCharacterId());
        return BattleMapper.toDto(battle);
    }
}
