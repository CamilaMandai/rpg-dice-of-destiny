package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.AttackReturnDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.CreatedBattleDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.DamageReturnDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.DefenseReturnDTO;
import org.springframework.transaction.annotation.Transactional;

public interface GameService {
    public CreatedBattleDTO play(String playerName, Character iPlayer, Character iBot);
    public AttackReturnDTO attack(Long battleId, Integer turnRound);
    public DefenseReturnDTO defend(Long battleId, Integer turnRound);
    public DamageReturnDTO damage(Long battleId, Integer turnRound);

}
