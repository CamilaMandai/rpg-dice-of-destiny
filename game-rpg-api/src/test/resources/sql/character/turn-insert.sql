CREATE TABLE turns(
    id SERIAL NOT NULL,
    atack_points bigint,
    damage bigint,
    defense_points bigint,
    round integer NOT NULL,
    battle_id bigint NOT NULL,
    PRIMARY KEY(id),
    FOREIGN key(battle_id) REFERENCES battles(id)
);

insert into
  turns ( atack_points,damage, defense_points, round, battle_id )
values
  ( 24, 16, 12, 1, 1),
  ( 12, 0, 14, 2, 1),
  ( 17, 18, 15, 3, 1),
  ( 17, 0, 19, 4, 1),
  ( 24, 19, 12, 5, 1);