-- left join #1
select * from employees e
         left join departments d on e.departments_id = d.id;
		 
-- left join #2
select * from departments d
         left join employees e on e.departments_id = d.id;
		
-- right join #1
select * from employees e
         right join departments d on e.departments_id = d.id;
		 
-- right join #2
select * from departments d
         right join employees e on e.departments_id = d.id;
		 
-- full join 
select * from employees e
         full join departments d on e.departments_id = d.id;
		 
-- cross join
select * from employees e
         cross join departments d;
		 
-- Используя left join найти департаменты, у которых нет работников
select * from departments d
         left join employees e on e.departments_id = d.id
		 where e.departments_id is null;
		 
-- Используя left и right join написать запросы, которые давали бы одинаковый результат.
--left join
select e.name Имя, d.name Отдел from employees e
         left join departments d on e.departments_id = d.id
		 where d.name is not null and e.name is not null;
--right join		 
select e.name Имя, d.name Отдел from employees e
         right join departments d on e.departments_id = d.id
		 where d.name is not null and e.name is not null;
-- Используя cross join составить все возможные разнополые пары.		 
select (t1.name , t2.name) as "Пара М+Ж"
from teen t1
cross join teen t2
where t1.gender <> t2.gender and t1.gender = 'М';
