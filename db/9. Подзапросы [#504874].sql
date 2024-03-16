-- Создаем таблицу заказчиков

CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

-- заполняем таблицу с заказчиками

insert into customers(first_name,
							last_name, age, country)
							values
('Олег', 'Олегов', 20, 'Россия'),
('Ольга', 'Олегова', 21, 'Россия'),
('Максим', 'Максимов', 22, 'Россия'),
('Максим', 'Петров', 23, 'Эстония'),
('Кирилл', 'Кириллов', 24, 'Чехия');

-- запрос на вывод самого молодого заказчика

select * from customers c
where c.age = (select min(age) from customers);

-- создаем таблицу заказов

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

-- заполняем таблицу заказов

insert into orders(amount, customer_id) values
(5, 1),
(15, 2),
(45, 3);

-- запрос, выводящий заказчиков, которые не успели

select * from customers c
where c.id not in (select customer_id from orders);
