 create table people(
     id serial primary key,
     name varchar(255)
 );
 
 create table food(
     id serial primary key,
     name varchar(255)
 );
 
 create table people_eat(
     id serial primary key,
     people_id int references people(id),
     food_id int references food(id)
);
insert into people(name) values ('Ivan'), ('Kirill'), ('Roman');

insert into food(name) values ('Картошка'), ('Бургер'), ('Помидор');

insert into people_eat(people_id, food_id) values (1, 1), (1, 2),
(1, 3), (2, 1), (2, 2), (3, 3);