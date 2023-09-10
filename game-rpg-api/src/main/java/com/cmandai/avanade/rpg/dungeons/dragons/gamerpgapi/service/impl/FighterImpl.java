package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.Fighter;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils.Dice;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FighterImpl implements Fighter {

    Character character;
    Long lifePoints;
    private WhoPlays player;
    public enum WhoPlays {
        HUMAN_PLAYER, BOT_PLAYER
    }

    public FighterImpl(Character character, WhoPlays player) {
        this.character = character;
        this.lifePoints = character.getLifePoints();
        this.player = player;
    }

    @Override
    public Integer rollDiceToStart() {
        return Dice.roll(1,20);
    }

    @Override
    public Long attack() {
        Long drawnValue = Dice.roll(1,12).longValue();
        return drawnValue + character.getStrength() + character.getAgility();
    }

    @Override
    public Long defend() {
        Long drawnValue = Dice.roll(1,12).longValue();
        return drawnValue + character.getDefense() + character.getAgility();
    }

    @Override
    public Long causeDamage() {
        Long diceDamage = Dice.roll(character.getDiceQuantity(),character.getDiceSides()).longValue();
        return diceDamage + character.getStrength();
    }
}
