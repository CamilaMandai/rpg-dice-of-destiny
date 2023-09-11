package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.utils.Dice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter @NoArgsConstructor
@Component
public class Fighter {

    private Character character;
    private Long lifePoints;

    public Fighter(Character character) {
        this.character = character;
        this.lifePoints = character.getLifePoints();
    }

    public Integer rollDiceToStart() {
        return Dice.roll(1,20);
    }

    public Long attack() {
        Long drawnValue = Dice.roll(1,12).longValue();
        return drawnValue + character.getStrength() + character.getAgility();
    }

    public Long defend() {
        Long drawnValue = Dice.roll(1,12).longValue();
        return drawnValue + character.getDefense() + character.getAgility();
    }

    public Long causeDamage() {
        Long diceDamage = Dice.roll(character.getDiceQuantity(),character.getDiceSides()).longValue();
        return diceDamage + character.getStrength();
    }

    public void loseLifePoints(Long points){
        lifePoints-=points;
    }

}
