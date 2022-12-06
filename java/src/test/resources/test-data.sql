BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');


INSERT INTO decks (deck_name, deck_description, accessible, creator_id) VALUES ('myDeck', 'deck', true, 1);
INSERT INTO decks (deck_name, deck_description, accessible, creator_id) VALUES ('myDeck2', 'deck', true, 1);
INSERT INTO decks (deck_name, deck_description, accessible, creator_id) VALUES ('myDeck3', 'deck', true, 1);
INSERT INTO decks (deck_name, deck_description, accessible, creator_id) VALUES ('myDeck4', 'deck', true, 2);

INSERT INTO cards (deck_id, front, back) VALUES (1, 'this is the front', 'this is the back');
INSERT INTO cards (deck_id, front, back) VALUES (1, 'front2', 'back2');
INSERT INTO cards (deck_id, front, back) VALUES (1, 'front3', 'back3');

INSERT INTO tags (tag_name) VALUES ('math');

INSERT INTO deck_tag (deck_id, tag_id) VALUES (4, 1);

INSERT INTO user_deck (deck_id, user_id) VALUES (1, 2);

COMMIT TRANSACTION;
