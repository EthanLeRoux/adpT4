CREATE TABLE CarVotes (
    carvote_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    carname VARCHAR(255),
    votes INT
);

INSERT INTO cars (car_name) VALUES ('Rolls Royce Ghost');
INSERT INTO cars (car_name) VALUES ('BMW i7 xDrive60');
INSERT INTO cars (car_name) VALUES ('Mercedis AMG G63S');
INSERT INTO cars (car_name) VALUES ('Bentley Continental GT');
INSERT INTO cars (car_name) VALUES ('Audi Q8');