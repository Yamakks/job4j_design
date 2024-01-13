create table company (
	id serial primary key,
    name varchar(255)
);
create table vacancy(
	id serial primary key,
    name varchar(255),
	price DECIMAL(8, 2),
	company_id int references company(id)
);

insert into company(name) values ('Металлург');
insert into company(name) values ('Автосервис');
insert into company(name) values ('Софт');

insert into vacancy(name, price, company_id) values ('Engeener', 50000.00, 1);
insert into vacancy(name, price, company_id) values ('Cleaner', 30000.00, 2);
insert into vacancy(name, price, company_id) values ('Director', 150000.00, 1);
insert into vacancy(name, price, company_id) values ('Junior', 25000.00, 3);
insert into vacancy(name, price, company_id) values ('HR', 60000.00, 3);
insert into vacancy(name, price, company_id) values ('Driver', 55000.00, 2);
insert into vacancy(name, price, company_id) values ('Engeener', 250000.00, 3);

select c.name, v.name, v.price
from company as c join vacancy as v on v.company_id = c.id;

select c.name as Название_компании, v.name as Вакансия, v.price as Оффер
from company as c join vacancy as v on v.company_id = c.id;

select c.name as "Название компании", v.name Вакансия, v.price Оффер
from company as c join vacancy as v on v.company_id = c.id;


