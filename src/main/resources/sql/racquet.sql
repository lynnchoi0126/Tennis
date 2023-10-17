drop table if exists racquet;
create table racquet(
    id long not null auto_increment,
    name varchar(20) not null,
    onRent boolean not null
);