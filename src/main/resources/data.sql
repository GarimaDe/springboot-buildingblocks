
create table user(
id int primary key, USER_NAME varchar(50) not null unique, FIRST_NAME varchar(50), LAST_NAME varchar(50),
EMAIL_ADDRESS varchar(50), ROLE varchar(50), SSN varchar(50) not null unique);

insert into user values(101, 'abc@gmail.com','Garima','De','admin','ssn01','garima');

insert into user values(102, 'def@gmail.com','Ria','De','tester','ssn02','ria');

insert into user values(103, 'punit@gmail.com','Punit','Pathak','developer','ssn03','punit');
