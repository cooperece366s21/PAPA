create table if not exists users
(
    id       varchar(256) primary key not null,
    name     varchar(36) not null
    );

create table if not exists lobbies
(
    id       varchar(256) primary key not null,
    code     varchar(36) not null
    # Come ack and put in list of users in lobby and restaurants in lobby
    );

create table if not exists restaurants
(
    id       varchar(256) primary key not null,
    name     varchar(36) not null
    );

create table if not exists user_preferred_restaurants
(
    user_id varchar(36) not null,
    rest_id varchar(36) not null,
    preference enum('dislike', 'like'),
    primary key (user_id, rest_id),
    foreign key (user_id) references users (id)
    );

create table if not exists lobby_preferred_restaurants
(
    lobby_id varchar(36) not null,
    rest_id varchar(36) not null,
    votes varchar(36) not null DEFAULT 0,
    primary key (lobby_id, rest_id),
    foreign key (lobby_id) references users (id)
    );