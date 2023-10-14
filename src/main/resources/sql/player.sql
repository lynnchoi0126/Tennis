drop table if exists player;
create table player
(
    PLAYER_NAME varchar(50) not null,
    WINS int,
    GAMES_PLAYED int,
    player_id long not null auto_increment
)
