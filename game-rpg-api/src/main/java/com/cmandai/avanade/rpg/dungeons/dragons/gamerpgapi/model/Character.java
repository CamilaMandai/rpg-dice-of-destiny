package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="characters")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Character implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "life_points", nullable = false)
    private Long lifePoints;

    @Column(name = "strength", nullable = false)
    private Long strength;

    @Column(name = "defense", nullable = false)
    private Long defense;

    @Column(name = "agility", nullable = false)
    private Long agility;

    @Column(name = "dice_quantity", nullable = false)
    private Integer diceQuantity;

    @Column(name = "dice_sides", nullable = false)
    private Integer diceSides;

    @Enumerated(EnumType.STRING)
    @Column(name = "personality", nullable = false, length = 45)
    private Personality personality;

    public enum Personality {
        HERO, MONSTER
    }
}
