create table gosnumber(
    id serial primary key,
    number varchar(255),
    region int
);

create table car(
    id serial primary key,
    name varchar(255),
	color varchar(255),
    gosnumber_id int references gosnumber(id) unique
);