insert into categories (category) values ('pop'), ('rock'), ('disco');
insert into states (stat) values (true), (false);
insert into attach (attachment, categories_id, states_id) values ('http://1.jpg', 1, 1), ('http://2.jpg', 2, 2);
insert into commentary (commentaries) values ('Good job'), ('На мыло такой контент');
insert into roles (name) values ('admin'), ('user'), ('superuser');
insert into items (name, commentary_id, attach_id) values ('Lutik', 2, 2), ('Star', 1, 1);
insert into users (name, roles_id, items_id) values ('Rockstar', 3, 2), ('LoL', 2, 1);
insert into rules (add, delete, comment) values (true, true, true), (true, false, false), (true, false, true);
insert into rules_roles (rules_id, roles_id) values (1, 1), (2, 2), (3, 3);
