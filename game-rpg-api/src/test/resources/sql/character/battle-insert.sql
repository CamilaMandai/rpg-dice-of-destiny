CREATE TABLE battles(
    id SERIAL NOT NULL,
    player_name character varying(100) NOT NULL,
    who_starts character varying(255),
    character_bot_id bigint NOT NULL,
    character_player_id bigint NOT NULL,
    PRIMARY KEY(id),
    FOREIGN key(character_bot_id) REFERENCES characters(id), FOREIGN key(character_player_id) REFERENCES characters(id)
);

INSERT INTO battles(player_name, who_starts, character_player_id, character_bot_id)
    VALUES('Camila', 'PLAYER', 1, 5);