select ROUND(AVG(d.price)::numeric, 2) "Средняя цена" from devices d;

select p.name "Имя", ROUND(AVG(d.price)::numeric, 2) "Средняя стоимость девайсов"
from devices_people as dp 
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
order by ROUND(AVG(d.price)::numeric, 2);

select p.name "Имя", ROUND(AVG(d.price)::numeric, 2) "Средняя стоимость девайсов"
from devices_people as dp 
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
having ROUND(AVG(d.price)::numeric, 2) > 5000
order by ROUND(AVG(d.price)::numeric, 2);