-- Начинаем транзакцию
begin transaction;

select * from vehicle;
insert into vehicle (name, color) values
('Mazeratti', 'black'),
('Jaguar', 'black');
savepoint firstsavepoint;
delete from vehicle where color = 'black';
insert into vehicle (name, color) values
('Mazeratti', 'blue');
savepoint secondsavepoint;
select * from vehicle;
drop table vehicle;
rollback to secondsavepoint;
delete from vehicle where color = 'blue';
select * from vehicle;
rollback to firstsavepoint;
commit;
--проверяем
select * from vehicle;
