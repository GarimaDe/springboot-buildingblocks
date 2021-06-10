
create table users(
id int primary key,ADDRESS varchar(50),USER_NAME varchar(50) not null unique, FIRST_NAME varchar(50), LAST_NAME varchar(50),
EMAIL_ADDRESS varchar(50), ROLE varchar(50), SSN varchar(50) not null unique);

insert into users values(101, 'India','abc@gmail.com','Garima','De','admin','ssn01','garima');

insert into users values(102, 'New York','def@gmail.com','Ria','De','tester','ssn02','ria');

insert into users values(103, 'London','punit@gmail.com','Punit','Pathak','developer','ssn03','punit');
--
--insert into orders values(2001,'order11',101);
--
--insert into orders values(2002,'order12',101);
--insert into orders values(2003,'order13',101);
--
--insert into orders values(2004,'order21',102);
--insert into orders values(2005,'order22',102);
--insert into orders values(2006,'order23',102);