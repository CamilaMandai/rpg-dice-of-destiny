package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.mapper;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.BattleResponseDTO;
import org.modelmapper.ModelMapper;

public class BattleMapper {
    public static BattleResponseDTO toDto(Battle battle) {
        return new ModelMapper().map(battle, BattleResponseDTO.class);
    }
}
