package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BattleLogsDTO {
    private Battle battle;
    private List<Turn> turns;
}
