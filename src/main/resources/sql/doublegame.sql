drop table if exists doublegame;
create table doublegame
(
    GAMEDATE date not null,
    winner1 varchar(20) not null,
    winner2 varchar(20) not null,
    loser1 varchar(20) not null,
    loser2 varchar(20) not null,
    winpoint int not null,
    losepoint int not null,
    id long not null auto_increment
)