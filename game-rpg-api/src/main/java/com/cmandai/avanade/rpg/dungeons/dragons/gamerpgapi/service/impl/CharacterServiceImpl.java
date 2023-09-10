package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.CharacterUniqueViolationException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.EntityNotFoundException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository.CharacterRepository;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    @Override
    @Transactional
    public Character save(Character character) {
        try{
            return characterRepository.save(character);
        } catch (DataIntegrityViolationException exception) {
            throw new CharacterUniqueViolationException(
                    String.format(
                            "Character '%s' already exists", character.getName()
                    ));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Character findById(Long id) {
        return characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Character with id %s not found", id))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    @Transactional
    public Character update(Long id, Character character) {
        Character currentCharacter = findById(id);
        BeanUtils.copyProperties(currentCharacter, character);
        return characterRepository.save(currentCharacter);
    }

    @Transactional
    @Override
    public void delete(Long id) {
            if(characterRepository.existsById(id)) {
                characterRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException(
                        String.format("Character id %s does not exist", id)
                );
            }
    }
}
