CREATE TABLE actionlist(
  action_id INT NOT NULL,
  actionName TEXT NOT NULL,
  actionGiftsNumber INT
);

CREATE TABLE giftlist(
  gift_id INT NOT NULL,
  giftName TEXT NOT NULL,
  giftsNumber INT
);

INSERT INTO actionlist(action_id, actionName, actionGiftsNumber) VALUES ('1', '������ ����', '2');
INSERT INTO actionlist(action_id, actionName, actionGiftsNumber) VALUES ('2', '������ �����', '2');
INSERT INTO giftlist(gift_id, giftName, giftsNumber) VALUES ('1', '����� ����', '1');
INSERT INTO giftlist(gift_id, giftName, giftsNumber) VALUES ('2', '����� ���', '2');
INSERT INTO giftlist(gift_id, giftName, giftsNumber) VALUES ('3', '����� ���', '3');