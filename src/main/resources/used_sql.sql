
create database college_class_routine;
use college_class_routine;

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

create table if not exists day_details(
id SERIAL PRIMARY KEY,
day_abbr varchar(250)  NOT NULL,
day_desc varchar(250)  NOT NULL,
UNIQUE(day_abbr, day_desc)
);

create table if not exists room_details(
id SERIAL PRIMARY KEY,
room_abbr varchar(250) unique  NOT NULL,
room_desc varchar(250)  NOT NULL
);

create table if not exists batch_details(
id SERIAL PRIMARY KEY,
batch_abbr varchar(250) unique NOT NULL,
batch_desc varchar(250)  NOT NULL
);

create table if not exists batch_time_day_details(
id SERIAL PRIMARY KEY,
batch_abbr varchar(250) NOT NULL,
time_slot varchar(250)  NOT NULL,
mon varchar(250),
tue varchar(250),
wed varchar(250),
thu varchar(250),
fri varchar(250),
sat varchar(250),
sun varchar (250),
unique(batch_abbr,time_slot)
);

create table if not exists room_time_day_details(
id SERIAL PRIMARY KEY,
room_abbr varchar(250) NOT NULL,
time_slot varchar(250) NOT NULL,
mon varchar(250),
tue varchar(250),
wed varchar(250),
thu varchar(250),
fri varchar(250),
sat varchar(250),
sun varchar (250),
UNIQUE(room_abbr,time_slot)
);

create table if not exists faculty_time_day_details(
id SERIAL PRIMARY KEY,
faculty_abbr varchar(250) NOT NULL,
time_slot varchar(250)  NOT NULL,
mon varchar(250),
tue varchar(250),
wed varchar(250),
thu varchar(250),
fri varchar(250),
sat varchar(250),
sun varchar (250),
unique(faculty_abbr,time_slot)
);

--drop table faculty_time_day_details;


DELIMITER //

CREATE TRIGGER NEW_BATCH_TS_INSERTER
AFTER INSERT ON  batch_details
FOR EACH ROW
BEGIN
    -- Declare variables
    DECLARE i INT DEFAULT 0;
    DECLARE n INT;
	declare new_batch varchar(250);
    declare current_time_slot varchar(250);
    -- Set initial values
    SET n = (SELECT COUNT(*)  FROM class_time_slots);
    SET new_batch = NEW.batch_abbr;

    -- While loop to iterate through employees
    WHILE i < n DO
        -- Update salary if employee ID matches
        set current_time_slot = (select time_slot from class_time_slots limit i,1);
       insert into batch_time_day_details (batch_abbr,time_slot)values (new_batch,current_time_slot );
        SET i = i + 1;
    END WHILE;
END//
DELIMITER ;


DELIMITER //

CREATE TRIGGER NEW_ROOM_TS_INSERTER
AFTER INSERT ON  room_details
FOR EACH ROW
BEGIN
    -- Declare variables
    DECLARE i INT DEFAULT 0;
    DECLARE n INT;
	declare new_room varchar(250);
    declare current_time_slot varchar(250);
    -- Set initial values
    SET n = (SELECT COUNT(*)  FROM class_time_slots);
    SET new_room = NEW.room_abbr;

    -- While loop to iterate through employees
    WHILE i < n DO
        -- Update salary if employee ID matches
        set current_time_slot = (select time_slot from class_time_slots limit i,1);
       insert into room_time_day_details (room_abbr,time_slot)values (new_room,current_time_slot );
        SET i = i + 1;
    END WHILE;
END//


DELIMITER ;


DELIMITER //
CREATE TRIGGER NEW_faculty_TS_INSERTER
AFTER INSERT ON  faculty_details
FOR EACH ROW
BEGIN
    -- Declare variables
    DECLARE i INT DEFAULT 0;
    DECLARE n INT;
	declare new_faculty varchar(250);
    declare current_time_slot varchar(250);
    -- Set initial values
    SET n = (SELECT COUNT(*)  FROM class_time_slots);
    SET new_faculty = NEW.faculty_abbre;

   
    -- While loop to iterate through employees
    WHILE i < n DO
        -- Update salary if employee ID matches
        set current_time_slot = (select time_slot from class_time_slots limit i,1);
       insert into faculty_time_day_details (faculty_abbr,time_slot)values (new_faculty,current_time_slot );
        SET i = i + 1;
    END WHILE;
END//
DELIMITER ;



DELIMITER //
CREATE TRIGGER NEW_TIM_SLOT_INSERTER
AFTER INSERT ON  class_time_slots
FOR EACH ROW
BEGIN
    -- Declare variables
    DECLARE i INT DEFAULT 0;
    DECLARE n INT;
        declare current_batch varchar(250);
       declare current_room varchar(250);
    declare current_faculty varchar(250);   	
    declare new_time_slot varchar(250);
    -- Set initial values
    SET n = (SELECT COUNT(*)  FROM faculty_details);
    SET new_time_slot = NEW.time_slot;

    -- While loop to iterate through employees
    WHILE i < n DO
        -- Update salary if employee ID matches
        set current_faculty = (select faculty_abbre from faculty_details limit i,1);
       insert into faculty_time_day_details (faculty_abbr,time_slot)values (current_faculty,new_time_slot );
        SET i = i + 1;
    END WHILE;
    
    
    SET i = 0;
 
    SET n = (SELECT COUNT(*)  FROM room_details);
    SET new_time_slot = NEW.time_slot;

    -- While loop to iterate through employees
    WHILE i < n DO
        -- Update salary if employee ID matches
        set current_room = (select room_abbr from room_details limit i,1);
       insert into room_time_day_details (room_abbr,time_slot)values (current_room,new_time_slot );
        SET i = i + 1;
    END WHILE;
    
    SET i = 0;

    SET n = (SELECT COUNT(*)  FROM batch_details);
    SET new_time_slot = NEW.time_slot;

    -- While loop to iterate through employees
    WHILE i < n DO
        -- Update salary if employee ID matches
        set current_batch = (select batch_abbr from batch_details limit i,1);
       insert into batch_time_day_details (batch_abbr,time_slot)values (current_batch,new_time_slot );
        SET i = i + 1;
    END WHILE;
    
END//
DELIMITER ;



--postgres1 starts--

CREATE OR REPLACE FUNCTION NEW_BATCH_TS_INSERTER_function()
RETURNS TRIGGER AS $$
DECLARE
    time_slot_set RECORD;
BEGIN
    -- Iterate over all employees
    FOR time_slot_set IN SELECT * FROM class_time_slots LOOP
        insert into batch_time_day_details (batch_abbr,time_slot)values (NEW.batch_abbr,time_slot_set.time_slot );
    END LOOP;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER NEW_BATCH_TS_INSERTER_trigger
AFTER INSERT ON batch_details
FOR EACH ROW
EXECUTE FUNCTION NEW_BATCH_TS_INSERTER_function();
--postgres1 ends--

--postgres2 starts--
CREATE OR REPLACE FUNCTION NEW_ROOM_TS_INSERTER_function()
RETURNS TRIGGER AS $$
DECLARE
    time_slot_set RECORD;
BEGIN
    -- Iterate over all employees
    FOR time_slot_set IN SELECT * FROM class_time_slots LOOP
        insert into room_time_day_details (room_abbr,time_slot)values (NEW.room_abbr,time_slot_set.time_slot );
    END LOOP;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER NEW_ROOM_TS_INSERTER_trigger
AFTER INSERT ON room_details
FOR EACH ROW
EXECUTE FUNCTION NEW_ROOM_TS_INSERTER_function();
--postgres2 ends--


--postgres3 starts--

CREATE OR REPLACE FUNCTION NEW_faculty_TS_INSERTER_function()
RETURNS TRIGGER AS $$
DECLARE
    time_slot_set RECORD;
BEGIN
    -- Iterate over all employees
    FOR time_slot_set IN SELECT * FROM class_time_slots LOOP
        insert into faculty_time_day_details (faculty_abbr,time_slot)values (NEW.faculty_abbre,time_slot_set.time_slot );
    END LOOP;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER NEW_faculty_TS_INSERTER_trigger
AFTER INSERT ON faculty_details
FOR EACH ROW
EXECUTE FUNCTION NEW_faculty_TS_INSERTER_function();
--postgres3 ends--

--postgres4 starts--

CREATE OR REPLACE FUNCTION NEW_TIM_SLOT_INSERTER_function()
RETURNS TRIGGER AS $$
DECLARE
     batch_detail_set RECORD;
     faculty_detail_set RECORD;
     room_detail_set record;
    
BEGIN
    -- Iterate over all employees
    FOR batch_detail_set IN SELECT * FROM batch_details LOOP
        insert into batch_time_day_details (batch_abbr,time_slot)values (batch_detail_set.batch_abbr,NEW.time_slot );
    END LOOP;
    
     FOR faculty_detail_set IN SELECT * FROM faculty_details LOOP
        insert into faculty_time_day_details (faculty_abbr,time_slot)values (faculty_detail_set.faculty_abbre,NEW.time_slot );
    END LOOP;
    
     FOR room_detail_set IN SELECT * FROM room_details LOOP
        insert into room_time_day_details (room_abbr,time_slot)values (room_detail_set.room_abbr,NEW.time_slot );
    END LOOP;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE or replace TRIGGER NEW_TIM_SLOT_INSERTER_trigger
AFTER INSERT ON class_time_slots
FOR EACH ROW
EXECUTE FUNCTION NEW_TIM_SLOT_INSERTER_function();
--postgres4 ends--

--postgres5 starts--

CREATE OR REPLACE FUNCTION NEW_BATCH_TS_INSERTER_function()
RETURNS TRIGGER AS $$
DECLARE
    time_slot_set RECORD;
BEGIN
    -- Iterate over all employees
    FOR time_slot_set IN SELECT * FROM class_time_slots LOOP
        insert into batch_time_day_details (batch_abbr,time_slot)values (NEW.batch_abbr,time_slot_set.time_slot );
    END LOOP;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER NEW_BATCH_TS_INSERTER_trigger
AFTER INSERT ON batch_details
FOR EACH ROW
EXECUTE FUNCTION NEW_BATCH_TS_INSERTER_function();
--postgres5 ends--

