drop table if exists Rent;
create table rent
(
    id long auto_increment,
    date date not null,
    racquet_no varchar(10) not null,
    name varchar(20) not null,
    rent_or_return varchar(20) not null
)