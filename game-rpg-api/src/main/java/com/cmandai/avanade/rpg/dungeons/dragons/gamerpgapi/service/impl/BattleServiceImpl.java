package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.EntityNotFoundException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.PaginationArgumentsViolationException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Turn;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository.BattleRepository;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.BattleService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.CharacterService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.TurnService;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.BattleLogsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BattleServiceImpl implements BattleService {

    private final BattleRepository battleRepository;
    private final CharacterService characterService;

    @Override
    @Transactional
    public Battle save(Battle battle) {
        return battleRepository.save(battle);
    }

    @Transactional
    @Override
    public Battle save(String playerName, Long playerCharacterId, Long botCharacterId) {
        Character playerCharacter = characterService.findById(playerCharacterId);
        Character botCharacter = characterService.findById(botCharacterId);
        Battle battle = new Battle(playerName, playerCharacter, botCharacter);
        return battleRepository.save(battle);
    }

    @Transactional
    @Override
    public Battle save(String playerName, Character playerCharacter, Character botCharacter) {
        Battle battle = new Battle(playerName, playerCharacter, botCharacter);
        return battleRepository.save(battle);
    }

    @Transactional(readOnly = true)
    @Override
    public Battle findById(Long id) {
        return battleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Battle with id %s does not exist", id))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<Battle> findAll(){
        return battleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Battle> findAllBattlesByPage(Pageable pageable) {
        try {
            return battleRepository.findAll(pageable);
        } catch(java.lang.IllegalArgumentException exception){
            throw new PaginationArgumentsViolationException(
                    exception.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<Battle> searchByPlayerName(String playerName) {
        return battleRepository.searchByPlayerName(playerName.toLowerCase());
    }

}
