package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.*;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.mapper.CharacterMapper;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> create(@Valid @RequestBody CharacterCreateDTO characterDTO){
        Character newCharacter = characterService.save(CharacterMapper.toCharacter(characterDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(CharacterMapper.toDTO(newCharacter));
    }

    @GetMapping("{id}")
    public ResponseEntity<CharacterResponseDTO> getById(@PathVariable Long id){
        Character character = characterService.findById(id);
        return ResponseEntity.ok(CharacterMapper.toDTO(character));
    }

    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> getAll(){
        List<Character> characters = characterService.findAll();
        return ResponseEntity.ok(CharacterMapper.toListDTO(characters));
    }

    @PutMapping("{id}")
    public ResponseEntity<CharacterResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CharacterUpdateDTO dto) {
        Character updatedCharacter = characterService.update(id, CharacterMapper.toCharacter(dto));
        return ResponseEntity.ok(CharacterMapper.toDTO(updatedCharacter));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
