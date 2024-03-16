create table films (
	id         serial primary key,
    name VARCHAR(50)
);
create table customer (
	id         serial primary key,
    name VARCHAR(50),
	adress VARCHAR(100),
	sex VARCHAR(7)
);

create table video_rent (
	id         serial primary key,
    film_id int references films(id),
	customer_id int references customer(id)
);

insert into films(name) values
('Пираты Карибского моря'),
('Матрица: Революция'),
('Человек, который изменил все'),
('Интерстеллар');

insert into customer(name, adress, sex) values
('Ольга Егорова', '1 Казанский переулок, дом 14', 'Женский'),
('Иванов Сергей', 'Ул. Центральная, д. 40, кв. 74', 'Мужской'),
('Иванов Сергей', 'Ул. Ленина, д. 7, кв. 130', 'Мужской');

insert into video_rent(film_id, customer_id) values
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(2, 3);