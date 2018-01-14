CREATE TABLE actionlist(
  id SERIAL PRIMARY KEY,
  action_id INT NOT NULL,
  actionName TEXT NOT NULL,
  actionGiftsNumber INT
);

CREATE TABLE giftlist(
  id SERIAL PRIMARY KEY,
  gift_id INT NOT NULL,
  giftName TEXT NOT NULL,
  giftsNumber INT
);

INSERT INTO actionlist(action_id, actionName, actionGiftsNumber) VALUES ('1', 'Летняя жара', '2');
INSERT INTO actionlist(action_id, actionName, actionGiftsNumber) VALUES ('2', 'Зимняя стужа', '2');
INSERT INTO giftlist(gift_id, giftName, giftsNumber) VALUES ('1', 'Товар один', '1');
INSERT INTO giftlist(gift_id, giftName, giftsNumber) VALUES ('2', 'Товар два', '2');
INSERT INTO giftlist(gift_id, giftName, giftsNumber) VALUES ('3', 'Товар три', '3');