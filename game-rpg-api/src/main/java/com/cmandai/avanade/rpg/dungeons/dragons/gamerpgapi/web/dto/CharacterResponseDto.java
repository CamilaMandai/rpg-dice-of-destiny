package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CharacterResponseDto {
    private Long id;
    private String name;
    private Long lifePoints;
    private Long strength;
    private Long defense;
    private Long agility;
    private String dice;
    private Character.Personality personality;
}
