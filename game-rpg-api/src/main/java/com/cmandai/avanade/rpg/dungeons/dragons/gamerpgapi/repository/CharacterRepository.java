package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.repository;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Battle;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("SELECT c FROM Character c WHERE c.personality = 'MONSTER'")
    List<Character> findAllMonsters();

    @Query("SELECT c FROM Character c WHERE LOWER(c.name) LIKE %:searchTerm%")
    List<Character> findByNameTerm(@Param("searchTerm") String searchTerm);
}
