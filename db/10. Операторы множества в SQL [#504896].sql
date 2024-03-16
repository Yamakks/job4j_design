-- создаем таблицу фильмов
CREATE TABLE movie
(
    id       SERIAL PRIMARY KEY,
    name     text,
    director text
);

-- создаем таблицу книг
CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  text,
    author text
);

-- заполняем таблицу фильмов
INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');
	   
-- заполняем таблицу книг 
INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');
	   
-- выполняем запрос, показывающий фильмы-экранизации

select name from movie
intersect
select title from book;

-- выполняем запрос, показывающий книги без экранизации

select title from book
except
select name from movie;

-- выполняем запрос, показывающий только уникальные записи
(select title from book
except
select name from movie)
union all
(select name from movie
except
select title from book);