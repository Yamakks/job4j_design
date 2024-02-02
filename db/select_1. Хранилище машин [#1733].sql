--выбираем все машины
select c.id id,c.name car_name, b.name 
body_name, e.name
engine_name, t.name transmission_name
from cars c
left join car_bodies b on c.id_body = b.id
left join car_engines e on c.id_engine = e.id
left join car_transmissions t on c.id_transmission = t.id;

/*--выбираем неиспользуемые кузова
select b.name body_unUse
from car_bodies b
left join cars c on b.id = c.id_body
where c.id_body is null
group by b.name;

--выбираем неиспользуемые двигатели
select e.name engine_unUse
from car_engines e
left join cars c on e.id = c.id_engine
where c.id_engine is null
group by e.name;

--выбираем неиспользуемые трансмиссии
select t.name transmission_unUse
from car_transmissions t
left join cars c on t.id = c.id_transmission
where c.id_transmission is null
group by t.name;*/