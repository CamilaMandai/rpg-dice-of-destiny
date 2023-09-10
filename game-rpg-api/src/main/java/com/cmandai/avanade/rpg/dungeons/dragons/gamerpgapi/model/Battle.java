package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="battles")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Battle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="player_name", nullable = false, length = 100)
    private String playerName;

    @Column(name="who_starts")
    @Enumerated(EnumType.STRING)
    private WhoStarts whoStarts;
    public enum WhoStarts {
        PLAYER, BOT
    }

    @ManyToOne
    @JoinColumn(name = "character_player_id", nullable = false)
    private Character playerCharacter;

    @ManyToOne
    @JoinColumn(name = "character_bot_id", nullable = false)
    private Character botCharacter;

    public Battle(String playerName, Character playerCharacter, Character botCharacter){
        this.playerName = playerName;
        this.playerCharacter = playerCharacter;
        this.botCharacter = botCharacter;
    }
}
