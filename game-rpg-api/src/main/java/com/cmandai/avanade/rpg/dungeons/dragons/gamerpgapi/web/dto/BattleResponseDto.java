package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BattleResponseDto {
    private String playerName;
    private Character playerCharacter;
    private Character botCharacter;
}
