package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.controller.dto;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class CharacterCreateDto extends CharacterRequest{
    @NotBlank
    private String name;
    @Min(value = 1, message = "Life Points should be at least 1")
    private Long lifePoints;
    @Min(value = 1, message = "Strength should be at least 1")
    private Long strength;
    @Min(value = 1, message = "Defense should be at least 1")
    private Long defense;
    @Min(value = 1, message = "Agility should be at least 1")
    private Long agility;
    @Min(value = 1, message = "Dice quantity should be at least 1")
    private Integer diceQuantity;
    @Min(value = 4, message = "The number of sides of the dice(s) should be at least 4")
    @Max(value = 100, message = "The number of sides of the dices should not exceed 100")
    private Integer diceSides;
    @NotNull
    private Character.Personality personality;
}
