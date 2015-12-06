CREATE DATABASE IF NOT EXISTS php;

USE php;

CREATE TABLE IF NOT EXISTS users(
user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
username VARCHAR(40) UNIQUE NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(40) NOT NULL,
email VARCHAR(60) NOT NULL,
password CHAR(40) NOT NULL,
PRIMARY KEY(user_id),
UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS events(
event_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
event_name VARCHAR(40) NOT NULL,
location VARCHAR(40) NOT NULL,
start_date DATE NOT NULL,
end_date DATE NOT NULL,
repeat_interval INT,
time TIME NOT NULL,
PRIMARY KEY(event_id)
);

CREATE TABLE IF NOT EXISTS follows(
user_id INT UNSIGNED NOT NULL,
event_id INT UNSIGNED,
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (event_id) REFERENCES events(event_id)
);

CREATE TABLE IF NOT EXISTS owns(
user_id INT UNSIGNED NOT NULL,
event_id INT UNSIGNED,
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (event_id) REFERENCES events(event_id)
);

INSERT INTO users (username, first_name, last_name, email, password) VALUES (
"grahamb",
"Graham",
"Burek",
"graham.burek1@gmail.com",
"password"
);

INSERT INTO events (event_name, location, start_date, end_date, repeat_interval, time) VALUES (
"poop",
"poopville",
'1969-02-15',
'1922-21-12',
"21",
'03:30:15'
);

INSERT INTO events (event_name, location, start_date, end_date, repeat_interval, time) VALUES (
"dota",
"the jungle",
'2000-22-05',
'1822-11-14',
"420",
'23:00:00'
);

INSERT INTO events (event_name, location, start_date, end_date, repeat_interval, time) VALUES (
"smorc",
"hunterville",
'1299-01-21',
'1772-21-22',
"666",
'15:00:00'
);


EXPLAIN users;
EXPLAIN events;
EXPLAIN follows;
EXPLAIN owns;

SELECT * FROM events;
