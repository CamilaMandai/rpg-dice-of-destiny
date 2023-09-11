package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.GameService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.PlayRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("game")
public class GameController {

    private final CharacterService characterService;
    private final GameService gameService;
    @PostMapping("play")
    public Battle play(@RequestBody PlayRequestDto dto) {
        Character playerCharacter = characterService.findById(dto.playerCharacterId());
        if(dto.botCharacterId() == null){
            return gameService.play(dto.playerName(), playerCharacter, null);
        }
        Character botCharacter = characterService.findById(dto.botCharacterId());
        return gameService.play(dto.playerName(), playerCharacter, botCharacter);
    };
}
