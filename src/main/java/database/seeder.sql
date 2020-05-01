# TODO: Create a new database adlister_db and user
#  that only has permissions on that database
drop database if exists adlister_db;

create database adlister_db;

use adlister_db;

CREATE USER 'billy'@'localhost' IDENTIFIED BY 'billysSecretP@ass123';
GRANT ALL ON adlister_db.* TO 'billy'@'localhost';

# TODO: Create a migration script for two tables, users, and ads.
#  They should have the following structure:
# +----------+             +-------------+
# |  users   |             |    ads      |
# +----------+             +-------------+
# | id       |<------,     | id          |
# | username |       `-----| user_id     | <-- foreign key to users table
# | email    |             | title       |
# | password |             | description |
# +----------+             +-------------+

CREATE TABLE IF NOT EXISTS users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR (50),
    email VARCHAR (50),
    password VARCHAR (30)
);

CREATE TABLE IF NOT EXISTS ads (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (username, email, password)
VALUES ('jameson', 'dog@bark.com', 'bowwow');

INSERT INTO ads (user_id, title, description)
VALUES (1, 'teepee city', 'a city of magical natives');