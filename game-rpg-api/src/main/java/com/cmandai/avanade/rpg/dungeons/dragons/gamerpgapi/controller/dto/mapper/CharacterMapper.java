package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.controller.dto.mapper;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.controller.dto.CharacterRequest;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.controller.dto.CharacterResponseDto;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CharacterMapper {
    public static Character toCharacter(CharacterRequest dto){
        return new ModelMapper().map(dto, Character.class);
    }
    public static CharacterResponseDto toDTO(Character character){
        String dice = character.getDiceQuantity() + "d" + character.getDiceSides();
        CharacterResponseDto dto = new CharacterResponseDto(
                character.getName(),
                character.getLifePoints(),
                character.getStrength(),
                character.getDefense(),
                character.getAgility(),
                dice,
                character.getPersonality());
        return dto;
    }

    public static List<CharacterResponseDto> toListDTO(List<Character> characters){
        List<CharacterResponseDto> charactersDTO = characters.stream().map(
                character -> toDTO(character)
        ).toList();
        return charactersDTO;
    }
}
