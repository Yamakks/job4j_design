insert into categories (category) values ('pop'), ('rock'), ('disco');
insert into states (stat) values (true), (false);
insert into attach (attachment, item_id) values ('http://1.jpg', 1), ('http://2.jpg', 2);
insert into commentary (commentaries, item_id) values ('Good job', 1), ('На мыло такой контент', 2);
insert into roles (name) values ('admin'), ('user'), ('superuser');
insert into items (name, category_id, user_id, states_id) values ('Lutik', 2, 1), ('Star', 1, 2);
insert into users (name, roles_id) values ('Rockstar', 3), ('LoL', 2);
insert into rules (add, delete, comment) values (true, true, true), (true, false, false), (true, false, true);
insert into rules_roles (rules_id, roles_id) values (1, 1), (2, 2), (3, 3);
