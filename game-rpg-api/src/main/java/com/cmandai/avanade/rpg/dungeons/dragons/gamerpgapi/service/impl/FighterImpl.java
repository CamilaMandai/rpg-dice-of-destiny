package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.impl;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.Fighter;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils.Dice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class FighterImpl implements Fighter {

    private Character character;
    private Long lifePoints;

    public FighterImpl(Character character) {
        this.character = character;
        this.lifePoints = character.getLifePoints();
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
