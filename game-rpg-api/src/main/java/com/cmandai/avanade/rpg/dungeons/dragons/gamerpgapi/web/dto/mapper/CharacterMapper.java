package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.mapper;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.CharacterRequest;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.CharacterResponseDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CharacterMapper {
    public static Character toCharacter(CharacterRequest dto){
        return new ModelMapper().map(dto, Character.class);
    }
    public static CharacterResponseDTO toDTO(Character character){
        String dice = character.getDiceQuantity() + "d" + character.getDiceSides();
        CharacterResponseDTO dto = new CharacterResponseDTO(
                character.getId(),
                character.getName(),
                character.getLifePoints(),
                character.getStrength(),
                character.getDefense(),
                character.getAgility(),
                dice,
                character.getPersonality());
        return dto;
    }

    public static List<CharacterResponseDTO> toListDTO(List<Character> characters){
        List<CharacterResponseDTO> charactersDTO = characters.stream().map(
                character -> toDTO(character)
        ).toList();
        return charactersDTO;
    }
}
