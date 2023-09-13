package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.controller;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.characterDTO.CharacterCreateDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.characterDTO.CharacterResponseDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.characterDTO.CharacterUpdateDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.mapper.CharacterMapper;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("characters")
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> create(
            @Valid @RequestBody CharacterCreateDTO characterDTO,
            UriComponentsBuilder uriBuilder){
        Character newCharacter = characterService.save(CharacterMapper.toCharacter(characterDTO));
        URI uri = uriBuilder.path("characters/{id}").buildAndExpand(newCharacter.getId()).toUri();
        return ResponseEntity.created(uri).body(CharacterMapper.toDTO(newCharacter));
    }

    @GetMapping("{id}")
    public ResponseEntity<CharacterResponseDTO> getById(@PathVariable Long id){
        Character character = characterService.findById(id);
        return ResponseEntity.ok(CharacterMapper.toDTO(character));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CharacterResponseDTO>> getByNameTerm(@RequestParam(name = "name") String nameTerm) {
        List<Character> characters = characterService.findByTermName(nameTerm);
        return ResponseEntity.ok(CharacterMapper.toListDTO(characters));
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
