create table fauna(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);
insert into fauna (name, avg_age, discovery_date)
values ('bigFish', 5000, '1995-09-01'),
('smalFish', 12000, '1991-09-01'),
('bigspot', 10001, '1990-09-01'),
('kanibal', 26000, '1949-09-01'),
('123', 5000, '1995-09-01');
insert into fauna (name, avg_age)
values ('bigboom', 20000),
('smallboom', 18000),
('balloon', 12000);
select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';