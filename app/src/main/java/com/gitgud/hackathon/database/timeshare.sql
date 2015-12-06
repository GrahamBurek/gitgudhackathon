CREATE DATABASE IF NOT EXISTS timeshare;

USE timeshare;

CREATE TABLE IF NOT EXISTS users(
user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
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

INSERT INTO users(first_name,last_name,email,password) VALUES (


);

EXPLAIN users;
EXPLAIN events;
EXPLAIN follows;
EXPLAIN owns;
