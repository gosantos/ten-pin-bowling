INSERT INTO GAME (ID) VALUES (1);

INSERT INTO FRAME (ID, NUMBER, GAME_ID) VALUES (2, 1, 1);
INSERT INTO FRAME (ID, NUMBER, GAME_ID) VALUES (4, 2, 1);
INSERT INTO FRAME (ID, NUMBER, GAME_ID) VALUES (7, 3, 1);

INSERT INTO ROLL (ID, PINS_HIT, FRAME_ID) VALUES (3, 10, 2);
INSERT INTO ROLL (ID, PINS_HIT, FRAME_ID) VALUES (5, 3, 4);
INSERT INTO ROLL (ID, PINS_HIT, FRAME_ID) VALUES (6, 7, 4);
INSERT INTO ROLL (ID, PINS_HIT, FRAME_ID) VALUES (8, 0, 7);
INSERT INTO ROLL (ID, PINS_HIT, FRAME_ID) VALUES (9, 10, 7);