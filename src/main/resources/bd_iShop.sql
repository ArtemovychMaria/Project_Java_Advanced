SET GLOBAL time_zone = '+3:00';
drop database if exists internet_shop;
create database internet_shop;
use internet_shop;


create table users(
id int not null primary key auto_increment,
user_email varchar(50) not null unique,
user_name varchar(50) not null,
user_surname varchar(50) not null,
user_role varchar(50) not null,
user_password varchar(20) not null unique
);

create table products(
id int not null primary key auto_increment,
product_name varchar(50) not null,
product_description text,
price decimal(6,3) not null
);

create table bucket(
id int not null primary key auto_increment,
user_id int not null,
product_id int not null,
purchase_date date not null,
foreign key (user_id) references users(id),
foreign key (product_id) references products(id)
);

insert into users(user_email,user_name,user_surname,user_role,user_password)
values
("Ivanko@mail","Petro","Ivanlov","ADMIN","1217");

select * from users;
select * from products;
select * from bucket;


