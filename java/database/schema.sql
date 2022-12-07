BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE decks (
    deck_id SERIAL,
    deck_name varchar(50) NOT NULL,
    deck_description varchar(200),
    accessible boolean default false,
    creator_id int NOT NULL,
    CONSTRAINT PK_deck PRIMARY KEY (deck_id),
    CONSTRAINT FK_deck_user FOREIGN KEY (creator_id) REFERENCES users(user_id)
);

CREATE TABLE user_deck (
    deck_id int NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT PK_user_deck PRIMARY KEY (deck_id, user_id),
    CONSTRAINT FK_user_deck_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
    CONSTRAINT FK_user_deck_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE tags (
    tag_id SERIAL,
    tag_name varchar(50) NOT NULL,
    CONSTRAINT PK_tag PRIMARY KEY (tag_id)
);

CREATE TABLE deck_tag (
    deck_id int NOT NULL,
    tag_id int NOT NULL,
    CONSTRAINT PK_deck_tag PRIMARY KEY (deck_id, tag_id),
    CONSTRAINT FK_deck_tag_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
    CONSTRAINT FK_deck_tag_tag FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
);


CREATE TABLE cards (
    card_id SERIAL,
    deck_id int NOT NULL,
    front varchar(500) NOT NULL,
    back varchar(500) NOT NULL,
    user_id int NOT NULL,
    confidence int,
    CONSTRAINT PK_card PRIMARY KEY (card_id),
    CONSTRAINT FK_card_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
    CONSTRAINT FK_card_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE study_session (
    study_session_id SERIAL,
    user_id int NOT NULL,
    deck_id int NOT NULL,
    number_of_cards int NOT NULL,
    number_correct int NOT NULL,
    number_green int NOT NULL,
    number_yellow int NOT NULL,
    number_red int NOT NULL,
    confidence_percent int NOT NULL,
    correct_percent int NOT NULL,

    CONSTRAINT PK_study_session PRIMARY KEY (study_session_id),
    CONSTRAINT FK_study_session_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_study_session_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id)
);

COMMIT TRANSACTION;