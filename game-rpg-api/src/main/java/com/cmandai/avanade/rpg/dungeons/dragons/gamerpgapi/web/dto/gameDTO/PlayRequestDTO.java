package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.gameDTO;

import jakarta.validation.constraints.Min;

public record PlayRequestDTO(
        String playerName,
        @Min(value = 1, message = "Character Id should be at least one")
        Long playerCharacterId,
        @Min(value = 1, message = "Character Id should be at least one")
        Long botCharacterId){};

