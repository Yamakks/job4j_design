create table licence(
    id serial primary key,
    partnum varchar(255)
);

create table software(
    id serial primary key,
    name varchar(255),
    licence_id int references licence(id)
);

insert into licence(partnum) values ('123');
insert into software(name, licence_id) VALUES ('Microsoft Office', 1);

select * from software;

select * from licence where id in (select licence_id from software);