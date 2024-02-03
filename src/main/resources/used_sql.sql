
create database college_class_routine;
use college_class_routine;

create table if not exists student_time_table(   
day varchar(250)  NOT NULL default '',
year varchar(250)  NOT NULL default '',
ts1 varchar(250)  NOT NULL default '',
ts2 varchar(250)  NOT NULL default '',
ts3 varchar(250)  NOT NULL default '',
ts4 varchar(250)  NOT NULL default '',
ts5 varchar(250)  NOT NULL default '',
ts6 varchar(250)  NOT NULL default '',
ts7 varchar(250)  NOT NULL default '',
ts8 varchar(250)  NOT NULL default '',
ts9 varchar(250)  NOT NULL default '',
ts10 varchar(250)  NOT NULL default '',
ts11 varchar(250)  NOT NULL default '',
ts12 varchar(250)  NOT NULL default '' 

)engine=innodb character set utf8 collate utf8_unicode_ci;

create table if not exists faculty_time_table(
faculty varchar(250)  NOT NULL default '',
day varchar(250)  NOT NULL default '' ,
ts1 varchar(250)  NOT NULL default '',
ts2 varchar(250)  NOT NULL default '',
ts3 varchar(250)  NOT NULL default '',
ts4 varchar(250)  NOT NULL default '',
ts5 varchar(250)  NOT NULL default '',
ts6 varchar(250)  NOT NULL default '',
ts7 varchar(250)  NOT NULL default '',
ts8 varchar(250)  NOT NULL default '',
ts9 varchar(250)  NOT NULL default '',
ts10 varchar(250)  NOT NULL default '' ,
ts11 varchar(250)  NOT NULL default '' ,
ts12 varchar(250)  NOT NULL default '' 
)engine=innodb character set utf8 collate utf8_unicode_ci;

--for mysql 
create table if not exists faculty_details(
id int AUTO_INCREMENT PRIMARY KEY,
faculty_fName varchar(250)  NOT NULL,
faculty_lName varchar(250)  NOT NULL,
faculty_abbre varchar(250)  NOT NULL
);

ALTER TABLE faculty_details AUTO_INCREMENT = 100;

--for postgres
create table if not exists faculty_details(
id SERIAL PRIMARY KEY,
faculty_fName varchar(250)  NOT NULL,
faculty_lName varchar(250)  NOT NULL,
faculty_abbre varchar(250)  NOT NULL,
UNIQUE(faculty_fName, faculty_lName,faculty_abbre)
);
ALTER SEQUENCE faculty_details_id_seq RESTART WITH 100;

create table if not exists class_time_slots(
id SERIAL PRIMARY KEY,
time_slot varchar(250)  NOT NULL UNIQUE
);

