package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MoveRequestDTO(
        @NotNull
        @Min(value = 1, message = "Round number should be at least 1")
        Long battleId,
        @NotNull
        @Min(value = 1, message = "Round number should be at least 1")
        Integer round) {
}
