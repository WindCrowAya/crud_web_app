CREATE SCHEMA users DEFAULT CHARACTER SET utf8;

CREATE TABLE users.userslist (
  id INT(11) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  age INT(11) NOT NULL,
  email VARCHAR(255) NOT NULL,
  is_admin TINYINT(1) NOT NULL,
  created_date DATE NOT NULL,
  PRIMARY KEY (id));

INSERT INTO users.userslist (first_name, last_name, age, email, is_admin, created_date) VALUES ('Reimu', 'Hakurei', 21, 'shrinemaiden@gmail.com', 1, now());
INSERT INTO users.userslist (first_name, last_name, age, email, is_admin, created_date) VALUES ('Marisa', 'Kirisame', 22, 'forestwitch@yandex.ru', 0, now());
INSERT INTO users.userslist (first_name, last_name, age, email, is_admin, created_date) VALUES ('Sanae', 'Kochiya', 20, 'moriyashrine@mail.ru', 0, now());
INSERT INTO users.userslist (first_name, last_name, age, email, is_admin, created_date) VALUES ('Aya', 'Shameimaru', 23, 'youkaimountain@yandex.ru', 1, now());
INSERT INTO users.userslist (first_name, last_name, age, email, is_admin, created_date) VALUES ('Hatate', 'Himekaidou', 25, 'spiritphotography@gmail.com', 0, now());
INSERT INTO users.userslist (first_name, last_name, age, email, is_admin, created_date) VALUES ('Reiuji', 'Utsuho', 23, 'nuclearblast@gmail.com', 0, now());
