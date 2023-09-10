package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.exception.ErrorMessage;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
