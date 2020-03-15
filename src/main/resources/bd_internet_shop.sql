SET GLOBAL time_zone = '+3:00';
drop database if exists internet_shop;
create database internet_shop;
use internet_shop;


create table users(
id int not null primary key auto_increment,
user_email varchar(50),
user_name varchar(50),
user_surname varchar(50),
user_role varchar(50),
user_password varchar(20) not null
);

create table products(
id int not null primary key auto_increment,
product_name varchar(50),
product_description text,
price decimal(6,3)
);

create table buckets(
id int not null primary key auto_increment,
user_id int,
product_id int,
purchase_date date,
foreign key (user_id) references users(id),
foreign key (product_id) references products(id)
);

insert into users(user_email,user_name,user_surname,user_role,user_password)
values
("Ivanko@mail","Petro","Ivanlov","admin","1217");

select * from users;