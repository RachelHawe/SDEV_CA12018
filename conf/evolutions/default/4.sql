# --- !Ups

update project set department_id = 1 where department_id is null;

# --- !Ups
delete from user;

insert into user (email,name,password,role) values ( 'admin@projects.com', 'Alice Admin', 'password', 'admin' );

insert into user (email,name,password,role) values ( 'manager@projects.com', 'Bob Manager', 'password', 'manager' );

insert into user (email,name,password,role) values ( 'customer@projects.com', 'Charlie Customer', 'password', 'customer' );