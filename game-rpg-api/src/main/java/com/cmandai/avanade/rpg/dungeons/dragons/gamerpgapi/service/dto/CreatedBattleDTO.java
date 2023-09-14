package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;

public record CreatedBattleDTO(Integer dicePlayer, Integer diceBot, Battle battle) {
}
