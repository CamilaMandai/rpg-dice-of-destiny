package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.GameService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.*;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.gameDTO.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("game")
public class GameController {

    private final CharacterService characterService;
    private final GameService gameService;
    @PostMapping("play")
    public ResponseEntity<CreatedBattleDTO> play(@Valid @RequestBody PlayRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Character playerCharacter = characterService.findById(dto.playerCharacterId());
        Character botCharacter = (dto.botCharacterId() != null) ? characterService.findById(dto.botCharacterId()) : null;
        CreatedBattleDTO battle = gameService.play(dto.playerName(), playerCharacter, botCharacter);
        URI uri = uriBuilder.path("battles/{id}").buildAndExpand(battle.battle().getId()).toUri();
        return ResponseEntity.created(uri).body(battle);
    }

    @PostMapping("attack")
    public ResponseEntity<AttackReturnDTO> attack(@Valid @RequestBody MoveRequestDTO dto) {
        AttackReturnDTO responseDTO = gameService.attack(dto.battleId(),dto.round());
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("defense")
    public ResponseEntity<DefenseReturnDTO> defense(@Valid @RequestBody MoveRequestDTO dto) {
        DefenseReturnDTO responseDTO = gameService.defend(dto.battleId(),dto.round());
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping("calculate-damage")
    public ResponseEntity<DamageReturnDTO> damage(@Valid @RequestBody MoveRequestDTO dto) {
        DamageReturnDTO responseDTO = gameService.damage(dto.battleId(),dto.round());
        return ResponseEntity.ok(responseDTO);
    }
}
