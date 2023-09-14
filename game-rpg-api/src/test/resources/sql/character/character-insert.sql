CREATE TABLE characters(
    id SERIAL NOT NULL,
    agility bigint NOT NULL,
    defense bigint NOT NULL,
    dice_quantity integer NOT NULL,
    dice_sides integer NOT NULL,
    life_points bigint NOT NULL,
    name character varying(100) NOT NULL,
    personality character varying(45) NOT NULL,
    strength bigint NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO characters (name, life_points, strength,defense, agility, dice_quantity, dice_sides, personality)
VALUES
    ('Guerreiro', 20, 7, 5, 6, 1,12, 'HERO'),
    ('Mago', 15, 3, 2, 8, 1,6, 'HERO'),
    ('Arqueiro', 18, 5, 3, 7, 1,8, 'HERO'),
    ('Cavaleiro', 26, 6, 8, 3, 2,6, 'HERO'),
    ('Orc', 42, 7, 1, 2, 3,4, 'MONSTER'),
    ('Gigante', 34, 10, 4, 4, 2,6, 'MONSTER'),
    ('Lobisomem', 34, 7, 4, 7, 2,2, 'HERO');
