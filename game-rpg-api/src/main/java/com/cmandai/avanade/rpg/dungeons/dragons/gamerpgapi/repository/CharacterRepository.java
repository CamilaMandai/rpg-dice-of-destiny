package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
