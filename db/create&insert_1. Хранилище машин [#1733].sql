Создание таблицы кузовов машин
create table car_bodies 
(
	id    serial primary key,
    name  varchar(50)
);

-- Создание таблицы двигателей машин
create table car_engines 
(
	id    serial primary key,
    name  varchar(50)
);

-- Создание таблицы трансмиссий машин
create table car_transmissions 
(
	id    serial primary key,
    name  varchar(50)
);

-- Создание таблицы хранилища машин
create table cars 
(
	id    serial primary key,
    name  varchar(50),
	id_body int references car_bodies(id),
	id_engine int references car_engines(id),
	id_transmission int references car_transmissions(id)
);

-- Создание таблицы названий машин для генерации с помощью cross
create table cars_name 
(
	id    serial primary key,
    name  varchar(50)
);

-- Вставка данных в таблицу кузовов
insert into car_bodies(name) values
('Седан'),
('Стретч'),
('Хэтчбэк 3 дв.'),
('Хэтчбэк 5 дв.'),
('Универсал'),
('Кабриолет'),
('Купэ'),
('Родстер'),
('Фургон'),
('Купе');

-- Вставка данных в таблицу двигателей
insert into car_engines(name) values
('1 цилиндровый'),
('4 цилиндровый 8 клапанов'),
('4 цилиндровый 16 клапанов'),
('4 цилиндровый турбированный'),
('4 цилиндровый битурбо'),
('6 цилиндровый V образный');

-- Вставка данных в таблицу трансмиссий
insert into car_transmissions(name) values
('4 ступени механика'),
('4 ступени автомат'),
('5 ступеней механика'),
('Вариатор'),
('6 ступеней робот');

-- Заполнение данных в таблицу машин
 insert into cars(name, id_body, id_engine, id_transmission) values
('Москвич', 1 , 1, 1 ),
('Вольво', 1 , 2, 1 ),
('Мерс2', 1 , 3, null ),
('Вольво', 1 , 1, 5 ),
('Сузуки', 4 , 4, 2 ),
('Волга2', 2 , null, 2 ),
('Вольво', 2 , 2, 2 ),
('Лада2', null , 5, 3 ),
('Ягуар2', null , 1, null ),
('Рено', 7 , 2, 1 );
