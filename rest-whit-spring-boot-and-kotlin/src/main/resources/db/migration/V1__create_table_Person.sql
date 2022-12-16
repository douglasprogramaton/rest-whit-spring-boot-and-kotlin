CREATE TABLE IF NOT EXISTS Person
(
    id serial primary key NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    gernde VARCHAR(255) NOT NULL

);