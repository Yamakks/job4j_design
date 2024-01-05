create table people(
id serial primary key,
name varchar(255),
age smallint,
full_age boolean);
insert into people(name, age, full_age) values ('Max', 19, true), ('Igor', 19, true);
select * from people;
update people set name = 'Maxim', age = 32, full_age = false;
select * from people;
delete from people;
select * from people;