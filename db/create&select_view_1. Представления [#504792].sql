--выбираем все машины. Дополнил запрос предыдущего дополнительными условиями. 
select c.id id,c.name car_name, b.name 
body_name, e.name
engine_name, t.name transmission_name
from cars c
left join car_bodies b on c.id_body = b.id
left join car_engines e on c.id_engine = e.id
left join car_transmissions t on c.id_transmission = t.id
where c.id_body between 1 and 3 and c.id_engine between 2 and 4 and 
c.id_transmission between 3 and 5;

-- Создаем представление
create view show_car_with_special_criteries
as
select c.id id,c.name car_name, b.name 
body_name, e.name
engine_name, t.name transmission_name
from cars c
left join car_bodies b on c.id_body = b.id
left join car_engines e on c.id_engine = e.id
left join car_transmissions t on c.id_transmission = t.id
where c.id_body between 1 and 3 and c.id_engine between 2 and 4 and 
c.id_transmission between 3 and 5;

-- вызываем запрос с созданным ранее представлением
select * from show_car_with_special_criteries;