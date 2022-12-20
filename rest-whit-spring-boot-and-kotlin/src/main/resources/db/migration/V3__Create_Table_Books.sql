CREATE TABLE books (
  id serial PRIMARY KEY,
  author  varchar (255)NOT NULL,
  launch_date TIMESTAMP,
  price decimal(65,2) NOT NULL,
  title VARCHAR(255)  NOT NULL
)
