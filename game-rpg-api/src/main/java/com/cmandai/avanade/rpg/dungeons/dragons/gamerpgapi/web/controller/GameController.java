package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.GameService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.PlayRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("game")
public class GameController {

    private final CharacterService characterService;
    private final GameService gameService;
    @PostMapping("play")
    public ResponseEntity<Battle> play(@RequestBody PlayRequestDto dto, UriComponentsBuilder uriBuilder) {
        Character playerCharacter = characterService.findById(dto.playerCharacterId());
        Character botCharacter = (dto.botCharacterId() != null) ? characterService.findById(dto.botCharacterId()) : null;
        Battle battle = gameService.play(dto.playerName(), playerCharacter, botCharacter);
        URI uri = uriBuilder.path("battles/{id}").buildAndExpand(battle.getId()).toUri();
        return ResponseEntity.created(uri).body(battle);
    };
}
