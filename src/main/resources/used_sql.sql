
create database college_class_routine;
use college_class_routine;

create table if not exists `student_time_table`(   
`day` varchar(250)  NOT NULL default '',
`year` varchar(250)  NOT NULL default '',
`ts1` varchar(250)  NOT NULL default '',
`ts2` varchar(250)  NOT NULL default '',
`ts3` varchar(250)  NOT NULL default '',
`ts4` varchar(250)  NOT NULL default '',
`ts5` varchar(250)  NOT NULL default '',
`ts6` varchar(250)  NOT NULL default '',
`ts7` varchar(250)  NOT NULL default '',
`ts8` varchar(250)  NOT NULL default '',
`ts9` varchar(250)  NOT NULL default '',
`ts10` varchar(250)  NOT NULL default '',
`ts11` varchar(250)  NOT NULL default '',
`ts12` varchar(250)  NOT NULL default '' 

)engine=innodb character set utf8 collate utf8_unicode_ci;

create table if not exists `faculty_time_table`(
`faculty` varchar(250)  NOT NULL default '',
`day` varchar(250)  NOT NULL default '' ,
`ts1` varchar(250)  NOT NULL default '',
`ts2` varchar(250)  NOT NULL default '',
`ts3` varchar(250)  NOT NULL default '',
`ts4` varchar(250)  NOT NULL default '',
`ts5` varchar(250)  NOT NULL default '',
`ts6` varchar(250)  NOT NULL default '',
`ts7` varchar(250)  NOT NULL default '',
`ts8` varchar(250)  NOT NULL default '',
`ts9` varchar(250)  NOT NULL default '',
`ts10` varchar(250)  NOT NULL default '' ,
`ts11` varchar(250)  NOT NULL default '' ,
`ts12` varchar(250)  NOT NULL default '' 
)engine=innodb character set utf8 collate utf8_unicode_ci;


