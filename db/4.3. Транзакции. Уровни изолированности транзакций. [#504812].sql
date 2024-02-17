-- создаем таблицу машин
create table vehicle(
	id serial primary key,
	name varchar(30),
	color varchar(30)
);

-- заполняем таблицу
insert into vehicle (name, color) values
('Lada', 'black'),
('Vaz', 'black'),
('BMW', 'black'),
('Audi', 'blue'),
('Lotus', 'red');

-- запускаем транзакцию
begin transaction isolation level serializable;