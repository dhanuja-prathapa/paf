create database healthcare;

use healthcare;

create table hospital(id int, name varchar(32), type varchar(32), description varchar(64), address varchar(64), phone varchar(16));
SET GLOBAL time_zone = '+5:30';
select * from hospital;


SET SQL_SAFE_UPDATES=0;
delete from hospital where id=0;
SET SQL_SAFE_UPDATES=1;