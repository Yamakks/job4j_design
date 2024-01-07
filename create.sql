 create table categories(
	 id serial primary key,
     category varchar(255)
 );
 create table states(
	 id serial primary key,
     stat boolean
 );
  create table roles(
	 id serial primary key,
     name varchar(255)
 );
 create table rules(
	 id serial primary key,
     add boolean,
	 delete boolean,
	 comment boolean
 );
  create table rules_roles(
	 id serial primary key,
     rules_id int references rules(id),
	 roles_id int references roles(id)
 );
 create table users(
     id serial primary key,
     name varchar(255),
	 roles_id int references roles(id)
 );
  create table items(
	 id serial primary key,
     name varchar(255),
	 category_id int references categories(id),
	 user_id int references users(id),
	 states_id int references states(id)
 );
 create table attach(
	 id serial primary key,
     attachment varchar(255),
	 item_id int references items(id)
 );
 create table commentary(
	 id serial primary key,
     commentaries varchar(255),
	 item_id int references items(id)
 );
 
 
 