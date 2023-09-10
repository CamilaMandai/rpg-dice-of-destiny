package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="turns")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Turn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="round", nullable = false)
    private Integer round;

    @Column(name="atack_points")
    private Long atackPoints;

    @Column(name="defense_points")
    private Long defensePoints;

    @Column(name="damage")
    private Long damage;

    @ManyToOne
    @JoinColumn(name="battle_id", nullable = false)
    private Battle battle;

    public Turn(Integer round, Battle battle) {
        this.round = round;
        this.battle = battle;
    }
}
