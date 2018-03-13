# --- Sample dataset

# --- !Ups

delete from project;
delete from department;


insert into department (id,name) values ( 1,'Multimedia' );

insert into department (id,name) values ( 2,'Fiance' );

insert into department (id,name) values ( 3,'HR' );

insert into department (id,name) values ( 4,'IT Management and Security' );

insert into department (id,name) values ( 5,'IT Hardware' );

insert into department (id,name) values ( 6,'IT Software' );

insert into department (id,name) values ( 7,'Cleaning' );

insert into department (id,name) values ( 8,'Cookery' );

insert into project (id,department_id,name,description) values ( 1,5,'Project Keep Us Running','Keeps Figures in order');

insert into project (id,department_id,name,description) values ( 2,5,'Project Wellbeing','Keeping Work a Firendly Happy Place');

insert into project (id,department_id,name,description) values ( 3,1,'Project Website','Maintain and Design our Webpage and Servers');

insert into project (id,department_id,name,description) values ( 4,3,'Project Collaborate','Work Together for Projects');

insert into project (id,department_id,name,description) values ( 5,3,'Project App','Maintain and Design our Application (availble on android and ios)');

insert into project (id,department_id,name,description) values ( 6,8,'Project BackUp & Security','Keeping your files safe and away from idiots');

insert into project (id,department_id,name,description) values ( 7,6,'Project Program','Program used in our braches to keep operations going');

insert into project (id,department_id,name,description) values ( 8,7,'Project Scrubs','Cleaning up after all you jerks');

