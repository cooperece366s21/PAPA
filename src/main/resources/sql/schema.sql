CREATE database if not exists ece366 ;
USE ece366;
CREATE TABLE IF NOT EXISTS users
(
    ID              VARCHAR(256)    PRIMARY KEY NOT NULL,
    name            VARCHAR(36)     NOT NULL,
    password        VARCHAR(64)     NOT NULL
);

CREATE TABLE IF NOT EXISTS lobbies
(
    ID              VARCHAR(256)    PRIMARY KEY NOT NULL,
    code            VARCHAR(36)     NOT NULL,
    owner           VARCHAR(256)    NOT NULL,
    FOREIGN KEY (owner) REFERENCES users(ID)
);

CREATE TABLE IF NOT EXISTS lobbyUsers
(
    lobbyID         VARCHAR(256)    NOT NULL,
    userID          VARCHAR(256)    NOT NULL,
    FOREIGN KEY (lobbyID) REFERENCES lobbies(ID),
    FOREIGN KEY (userID) REFERENCES users(ID),
    PRIMARY KEY (lobbyID, userID)
);

CREATE TABLE IF NOT EXISTS restaurants
(
    ID              VARCHAR(256)	PRIMARY KEY NOT NULL,
    alias           VARCHAR(128)    NOT NULL,
    name            VARCHAR(256)    NOT NULL,
    isOpenNow		BOOLEAN,
    phone           VARCHAR(16)     NOT NULL,
    displayPhone    VARCHAR(16),
    cuisineID       VARCHAR(300),
    rating          DOUBLE(3,2)     NOT NULL,
    locationID      INTEGER,
    price           VARCHAR(8)     	NOT NULL,
    hourID          VARCHAR(32),
    yelpInfo		VARCHAR(4096)   NOT NULL
);
CREATE TABLE IF NOT EXISTS cuisine
(
    restaurantID    VARCHAR(256)    NOT NULL,
    cuisineID       INTEGER  		AUTO_INCREMENT PRIMARY KEY NOT NULL,
    cuisineAlias    VARCHAR(64)     NOT NULL,
    cuisineTitle    VARCHAR(64)     NOT NULL,
    FOREIGN KEY (restaurantID) REFERENCES restaurants(ID),
    UNIQUE (restaurantID, cuisineTitle)
);
CREATE TABLE IF NOT EXISTS location
(
    restaurantID    VARCHAR(256) 	UNIQUE NOT NULL,
    locationID      INTEGER			PRIMARY KEY NOT NULL AUTO_INCREMENT,
    address1        VARCHAR(256) 	NOT NULL,
    address2        VARCHAR(256),
    address3        VARCHAR(256),
    city            VARCHAR(64) 	NOT NULL,
    zip             VARCHAR(16) 	NOT NULL,
    country         VARCHAR(32) 	NOT NULL,
    state           VARCHAR(32) 	NOT NULL,
    displayAddress  VARCHAR(1024) 	NOT NULL,
    latitude        DOUBLE(15,10),
    longitude       DOUBLE(15,10),
    FOREIGN KEY (restaurantID) REFERENCES restaurants(ID)
);

CREATE TABLE IF NOT EXISTS hours
(
    restaurantID	VARCHAR(256)	NOT NULL,
    hourID			INTEGER			PRIMARY KEY NOT NULL AUTO_INCREMENT,
    hourType		ENUM('regular', 'special')		NOT NULL,
    dayWeek			INTEGER			NOT NULL,
    startTime		VARCHAR(4)		NOT NULL,
    endTime			VARCHAR(4)		NOT NULL,
    isOvernight		BOOLEAN			NOT NULL,
    FOREIGN KEY (restaurantID) REFERENCES restaurants(ID),
    UNIQUE (restaurantID, dayWeek)
);

CREATE TABLE IF NOT EXISTS user_preferred_restaurants
(
    userID          VARCHAR(256)    NOT NULL,
    restaurantID    VARCHAR(256)    NOT NULL,
    preference      ENUM('dislike', 'like'),
    PRIMARY KEY (userID, restaurantID),
    FOREIGN KEY (userID) references users (ID)
);

CREATE TABLE IF NOT EXISTS lobby_preferred_restaurants
(
    lobbyID         VARCHAR(256)     NOT NULL,
    restaurantID    VARCHAR(256)     NOT NULL,
    vote            INTEGER          not null DEFAULT 0,
    PRIMARY KEY (lobbyID, restaurantID),
    FOREIGN KEY (lobbyID) REFERENCES lobbies (ID),
    FOREIGN KEY (restaurantID) REFERENCES restaurants(ID)
);