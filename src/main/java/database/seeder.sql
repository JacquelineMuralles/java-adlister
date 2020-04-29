drop database if exists contacts_db;

create database contacts_db;

use contacts_db;

create table contacts (
                          id int unsigned not null auto_increment primary key,
                          first_name varchar(50),
                          last_name varchar(50),
                          phone_number varchar(30)
);