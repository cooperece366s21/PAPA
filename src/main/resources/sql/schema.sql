CREATE database if not exists ece366 ;
USE ece366;
create table if not exists users
(
    ID       varchar(256) primary key not null,
    name     varchar(36) not null
);

create table if not exists lobbies
(
    ID       varchar(256) primary key not null,
    code     varchar(36) not null
    # Come ack and put in list of users in lobby and restaurants in lobby
);

create table if not exists restaurants
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
create table if not exists cuisine
(
    restaurantID    VARCHAR(256)    NOT NULL,
    cuisineID       INTEGER  		AUTO_INCREMENT PRIMARY KEY NOT NULL,
    cuisineAlias    VARCHAR(64)     NOT NULL,
    cuisineTitle    VARCHAR(64)     NOT NULL,
    FOREIGN KEY (restaurantID) REFERENCES restaurants(ID),
    UNIQUE (restaurantID, cuisineTitle)
);
create table if not exists location
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

create table if not exists user_preferred_restaurants
(
    user_id varchar(36) not null,
    rest_id varchar(36) not null,
    preference enum('dislike', 'like'),
    primary key (user_id, rest_id),
    foreign key (user_id) references users (ID)
);

create table if not exists lobby_preferred_restaurants
(
    lobby_id varchar(36) not null,
    rest_id varchar(36) not null,
    votes varchar(36) not null DEFAULT 0,
    primary key (lobby_id, rest_id),
    foreign key (lobby_id) references users (ID)
);