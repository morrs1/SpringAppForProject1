DROP TABLE IF EXISTS Person, Book;

CREATE TABLE Person
(
    person_id     SERIAL PRIMARY KEY,
    name          VARCHAR(100)                                    NOT NULL,
    surname       VARCHAR(100)                                    NOT NULL,
    year_of_birth INT CHECK (year_of_birth BETWEEN 1920 AND 2006) NOT NULL,
    UNIQUE (name, surname)
);

CREATE TABLE Book
(
    book_id   SERIAL PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    author    VARCHAR(100) NOT NULL,
    year      INT CHECK (year BETWEEN 1700 AND 2024),
    person_id INT,
    FOREIGN KEY (person_id) REFERENCES Person (person_id),
    UNIQUE (name, author)
);

INSERT INTO Person (name, surname, year_of_birth)
VALUES ('Иван', 'Иванов', 1985),
       ('Петр', 'Петров', 1990),
       ('Светлана', 'Сидорова', 1978),
       ('Анна', 'Антонова', 2000),
       ('Дмитрий', 'Дмитриев', 1982),
       ('Елена', 'Еленина', 1995),
       ('Алексей', 'Алексеев', 1970),
       ('Мария', 'Маркова', 1988),
       ('Николай', 'Николаев', 1965),
       ('Ольга', 'Ольгина', 2001);


INSERT INTO Book (name, author, year, person_id)
VALUES
    ('Война и мир', 'Лев Толстой', 1869, 1),  -- Выдано Ивану Иванову
    ('1984', 'Джордж Оруэлл', 1949, 2),        -- Выдано Петру Петрову
    ('Мастер и Маргарита', 'Михаил Булгаков', 1967, 1),  --
    ('Преступление и наказание', 'Федор Достоевский', 1866, 3),  -- Выдано Светлане Сидоровой
    ('Гарри Поттер и философский камень', 'Дж.К. Роулинг', 1997, NULL),  -- Не выдана
    ('Убить пересмешника', 'Харпер Ли', 1960, 4),  -- Выдано Анне Антоновой
    ('Тихий Дон', 'Михаил Шолохов', 1928, NULL),  -- Не выдана
    ('Собачье сердце', 'Михаил Булгаков', 1925, 5),  -- Выдано Дмитрию Дмитриеву
    ('Анна Каренина', 'Лев Толстой', 1877, NULL),  -- Не выдана
    ('Маленький принц', 'Антуан де Сент-Экзюпери', 1943, 6);  -- Выдано Елене Елениной