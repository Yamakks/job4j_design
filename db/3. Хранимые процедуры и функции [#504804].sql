-- Чистим таблицу продактс с предыдущего задания
delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

-- Добавляем процедуру вставки из урока
create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

-- Добавляем процедуру обновления из урока
create
or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
        end if;
        if
            tax > 0 THEN
            update products
            set price = price + price * tax;
        end if;
    END;
$$;

-- Добавляем функцию вставки из урока
create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

-- Добавляем функцию обновления из урока
create
or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
            select into result count
            from products
            where id = u_id;
        end if;
        if tax > 0 THEN
            update products
            set price = price + price * tax;
            select into result sum(price)
            from products;
        end if;
        return result;
    end;
$$;

-- Выполняем вставки с помощью процедуры
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_2', 'producer_2', 8, 115);
call insert_data('product_3', 'producer_3', 8, 115);

-- выполняем вставки с помощью функции
select f_insert_data('product_4', 'producer_4', 25, 50);
select f_insert_data('product_5', 'producer_5', 60, 70);
select f_insert_data('product_6', 'producer_6', 70, 70);

-- проверяем заполнение таблицы
select * from products;

-- иобновляем таблицу с помощью процедуры
call update_data(0, 0.2, 0);

-- обновляем таблицу с помощью функции
select f_update_data(0, 0.5, 0);

-- создадим процедуру удаления, если стоимость больше указанной
create
or replace procedure delete_data(cost integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products 
		where price > cost;
    END;
$$;

-- удалим строки дороже 200
call delete_data(200);

-- создадим функцию удаления с выводом оставшихся строк
create
or replace function f_delete_data(cost integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
            delete from products
			where price > cost;
            select into result count(id)
            from products;
        return result;
    end;
$$;
-- удаляем с помощью функции
select f_delete_data(200);