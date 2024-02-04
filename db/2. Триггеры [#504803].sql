-- Создаем таблицу продуктов
create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- Создаем функцию NDS()
create
or replace function NDS()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';


-- Создаем триггер начисления НДС который действует на запрос
create trigger NDS_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure NDS();

-- Делаем вставку в таблицу продуктов
insert into products (name, producer, count, price)
VALUES 
('product_1', 'producer_1', 3, 50);

-- Делаем вставку в таблицу продуктов
insert into products (name, producer, count, price)
VALUES 
('product_2', 'producer_2', 3, 70);

-- Проверяем результат
select * from products;

-- Выключаем NDS_trigger
alter table products disable trigger NDS_trigger;

-- Делаем вставку в таблицу продуктов
insert into products (name, producer, count, price)
VALUES 
('product_3', 'producer_3', 3, 80);

-- Проверяем результат
select * from products;


-- Создаем функцию NDS2()
create
or replace function NDS2()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

-- Создаем триггер начисления НДС который действует на запись
create trigger NDS2_trigger
    after insert
    on products
    for each row
    execute procedure NDS2();
	
-- Делаем вставку в таблицу продуктов
insert into products (name, producer, count, price)
VALUES 
('product_4', 'producer_4', 3, 70),
('product_5', 'producer_5', 3, 90),
('product_6', 'producer_6', 3, 100);

-- Проверяем результат
select * from products;

-- Создаем таблицу истории цен
create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);
	
-- Создаем функцию history()
create
or replace function history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
		select new.name, new.price, now();
		Return null;
    END;
$$
LANGUAGE 'plpgsql';

-- Создаем триггер заполнения таблицы history_of_price
create trigger history_upd
    after insert
    on products
    for each row
    execute procedure history();
	
-- Делаем вставку в таблицу продуктов
insert into products (name, producer, count, price)
VALUES 
('product_7', 'producer_7', 3, 150),
('product_8', 'producer_8', 3, 200),
('product_9', 'producer_9', 3, 300);

-- Проверяем таблицу продуктов
select * from products;

-- Проверяем таблицу истории
select * from history_of_price;







