package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService{

    private final CharacterRepository characterRepository;
    @Override
    @Transactional
    public Character save(Character character) {
        return characterRepository.save(character);
    }

    @Override
    @Transactional(readOnly = true)
    public Character findById(Long id) {
        return characterRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Personagem não encontrado")
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
        characterRepository.deleteById(id);
    }
}
