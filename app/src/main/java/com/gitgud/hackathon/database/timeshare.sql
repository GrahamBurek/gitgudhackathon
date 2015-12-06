DROP DATABASE php;

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
owner_id INT UNSIGNED NOT NULL,
PRIMARY KEY(event_id),
FOREIGN KEY (owner_id) REFERENCES users(user_id)
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
"graham",
"Graham",
"Burek",
"graham.burek1@gmail.com",
"password"
),(
"liam",
"Liam",
"Harwood",
"liam.harwood1@marist.edu",
"password"
),(
"maxim",
"Maxim",
"Vitkin",
"maxim.vitkin1@marist.edu",
"password"
);



INSERT INTO events (event_name, location, start_date, end_date, repeat_interval, time,owner_id) VALUES (
"Block Party",
"Poughkeepsie,NY",
'2015-12-6',
'2015-12-6',
"0",
'05:30:00',
1
);

INSERT INTO events (event_name, location, start_date, end_date, repeat_interval, time,owner_id) VALUES (
"Calculus II",
"HC 2023",
'2015-12-07',
'2015-12-07',
"0",
'13:00:00',
2
);

INSERT INTO events (event_name, location, start_date, end_date, repeat_interval, time,owner_id) VALUES (
"Work in Office",
"Staten Island, NY",
'2015-12-8',
'2015-12-8',
"20",
'15:00:00',
3
);


INSERT INTO follows (user_id, event_id) VALUES (1,2),(2,3),(3,1),(1,3),(2,1),(3,2);


EXPLAIN users;
EXPLAIN events;
EXPLAIN follows;
EXPLAIN owns;

SELECT * FROM events;
