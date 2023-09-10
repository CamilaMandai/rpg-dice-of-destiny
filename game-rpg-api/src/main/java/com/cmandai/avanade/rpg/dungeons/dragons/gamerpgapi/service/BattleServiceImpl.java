package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.EntityNotFoundException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository.BattleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BattleServiceImpl implements BattleService{

    private final BattleRepository battleRepository;
    private final CharacterService characterService;

    @Transactional
    @Override
    public Battle save(String playerName, Long playerCharacterId, Long botCharacterId) {
        Character playerCharacter = characterService.findById(playerCharacterId);
        Character botCharacter = characterService.findById(botCharacterId);
        Battle battle = new Battle(playerName, playerCharacter, botCharacter);
        return battleRepository.save(battle);
    }

    @Transactional(readOnly = true)
    @Override
    public Battle findById(Long id) {
        return battleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Battle with id %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<Battle> findAll(){
        return battleRepository.findAll();
    }

}
