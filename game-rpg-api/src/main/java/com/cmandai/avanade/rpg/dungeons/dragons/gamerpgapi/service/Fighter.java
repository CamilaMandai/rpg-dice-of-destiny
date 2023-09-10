package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service;

public interface Fighter {
    public Integer rollDiceToStart();
    public Long attack();
    public Long defend();
    public Long causeDamage();
}
