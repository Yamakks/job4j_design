create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);
insert into devices(name, price) values 
('Notebook', 5000.00), 
('Smartphone', 4000.00),
('Car', 50000.00),
('Motorbike', 30000.00),
('food', 500.95),
('drink', 50.45);

insert into people(name) values ('Аня'), ('Ваня'), ('Боря'),
('Петя'), ('Кристина'), ('Олег');

insert into devices_people(device_id, people_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
(2, 2), (2, 4), (2, 6),
(3, 6),
(4, 1), (4, 5),
(5, 1), (5, 1), (5, 1),
(6, 1), (2, 1), (4, 6);

