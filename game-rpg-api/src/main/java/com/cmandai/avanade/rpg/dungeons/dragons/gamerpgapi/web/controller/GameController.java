package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.GameService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.*;
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
    public ResponseEntity<Battle> play(@RequestBody PlayRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Character playerCharacter = characterService.findById(dto.playerCharacterId());
        Character botCharacter = (dto.botCharacterId() != null) ? characterService.findById(dto.botCharacterId()) : null;
        Battle battle = gameService.play(dto.playerName(), playerCharacter, botCharacter);
        URI uri = uriBuilder.path("battles/{id}").buildAndExpand(battle.getId()).toUri();
        return ResponseEntity.created(uri).body(battle);
    }

    @PostMapping("attack")
    public ResponseEntity<AttackResponseDTO> attack(@Valid @RequestBody MoveRequestDTO dto) {
        AttackResponseDTO responseDTO = new AttackResponseDTO(
                gameService.move(dto.battleId(),
                        dto.round(),
                        "attack"));
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("defense")
    public ResponseEntity<DefenseResponseDTO> defense(@Valid @RequestBody MoveRequestDTO dto) {
        DefenseResponseDTO responseDTO = new DefenseResponseDTO(
                gameService.move(dto.battleId(),
                        dto.round(),
                        "defend"));
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping("calculate-damage")
    public ResponseEntity<DamageResponseDTO> damage(@Valid @RequestBody MoveRequestDTO dto) {
        DamageResponseDTO responseDTO = new DamageResponseDTO(
                gameService.move(dto.battleId(),
                        dto.round(),
                        "damage"));
        return ResponseEntity.ok(responseDTO);
    }
}
