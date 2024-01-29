-- 1. Запрос на получение всех продуктов с типом 'Сыр'
Select p.name Наименование, p.price Цена, p.expired_date Срок_годности, t.name Тип_товара
from product p
join type t
on p.type_id = t.id
where t.name = 'Сыр';

-- 2. Запрос на получение всех товаров у кого в имени есть слово "мороженое"
Select p.name Наименование, p.price Цена, p.expired_date Срок_годности, t.name Тип_товара
from product p
join type t
on p.type_id = t.id
where p.name like '%мороженое%';

-- 3. Запрос на вывод товаров с истекшим сроком годности
Select p.name Наименование, p.price Цена, p.expired_date Срок_годности, t.name Тип_товара
from product p
join type t
on p.type_id = t.id
where p.expired_date <= CURRENT_DATE;

-- 4. Запрос который выводит самый дорогой продукт по типам 
Select p.name Наименование, p.price Цена, t.name Тип_товара
from product p
join type t
on p.type_id = t.id
join (
  select
    type_id,
    MAX(price) AS max_price
  from
    product
  group by
    type_id)
 max_prices ON p.type_id = max_prices.type_id AND p.price = max_prices.max_price;
 
 -- 5.Вывод для каждого типа продуктов количество
Select t.name Тип_товара, count(p.name) Количество
from product p
join type t
on p.type_id = t.id
group by t.name;

-- 6. Получение всех продуктов с типом сыр и молоко
Select p.name Наименование, p.price Цена, p.expired_date Срок_годности, t.name Тип_товара
from product p
join type t
on p.type_id = t.id
where t.name in ('Сыр', 'Молоко');

-- 7. Запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
Select t.name Тип_товара
from product p
join type t
on p.type_id = t.id
group by t.name
having count(p.name) < 10;

-- 8. Вывести все продукты и их тип
Select p.name Наименование, p.price Цена, p.expired_date Срок_годности, t.name Тип_товара
from product p
join type t
on p.type_id = t.id;





 

