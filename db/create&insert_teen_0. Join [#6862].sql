-- Создание таблицы людей
create table teen
(
	id    serial primary key,
    name  varchar(50),
	gender varchar(1)
);

-- Заполнение таблицы людей
insert into teen(name, gender) values
('Коля', 'М'),
('Вася', 'М'),
('Петя', 'М'),
('Юра', 'М'),
('Маша', 'Ж'),
('Даша', 'Ж'),
('Ира', 'Ж'),
('Юля', 'Ж');