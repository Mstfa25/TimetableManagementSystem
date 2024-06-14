create database if not exists timeTableManagementSystem;

use timeTableManagementSystem;

create table if not exists branch(
	id int primary key auto_increment,
	name varchar(255)not null
);

CREATE TABLE IF NOT EXISTS branchesintimeintimetable (
    timeInTimetableId int not null references timesintimetable(id),
    branchId int not null references branch(id)
);

CREATE TABLE IF NOT EXISTS coursesectionroomtypes (
	courseId int not null references courses(id),
    roomTypeId int not null references roomstypes(id)
);

create TABLE IF NOT EXISTS courseswithmorethansemester(
	courseId int not null references courses(id),
	semesterId int not null references semester(id)
);
    
    
create table if not exists faculty(	
	id int primary key auto_increment,
	name varchar(255) not null
);
    
create table if not exists studyplan(
	id int primary key auto_increment,
	name varchar(255) not null,
    facultyId int not null references faculty(id)
);

create table if not exists semester(
	id int primary key auto_increment,
	number int not null,
    StudyPlanId int not null references studyplan(id)
);
    
create table if not exists JobType(
	Id int primary key auto_increment,
	name varchar(255) not null
);
    
create table if not exists staff(
	id int primary key auto_increment,
	name varchar(255) not null,
    JobTypeId int not null references JobType(id),
    branchId int not null references branch(id)
); 
    
create table if not exists roomsTypes(
	id int primary key auto_increment,
	RoomType varchar(255) not null
);

create table if not exists rooms(
	id int primary key auto_increment ,
	name varchar(255) not null,
	capacity int not null,
	TypeId int not null references roomsTypes,
    branchId int not null references branch(id)
);

create table if not exists roomsintimeintimetable(
	timeInTimetableId int NOT NULL references timesintimetable(id),
	roomId int NOT NULL references rooms(id)
);

create table if not exists lecturegroups(
	id int primary key auto_increment,
    name varchar(255) not null
);

create table if not exists lecgroup(
	id int primary key auto_increment,
    name varchar(255) not null,
    lecturegroupId int not null references lecturegroups(id)
);

create table if not exists lecgroupbranches(
	lecGroupId int not null references lecgroup(id),
    branchId int not null references branch (id)
);

create table if not exists sectionsnumberofgroups(
	id int primary key not null auto_increment,
    name varchar(255) not null
);

create table if not exists sectionsfgroups(
	sectionsnumberofgroupsId int not null references sectionsnumberofgroups(id),
    branchId int not null references branch(id),
    numberOfGroups int not null 
);

create table if not exists sectiongroupnamefortimeintimetable(
	timeInTimetableId int NOT NULL references timesintimetable(id),
	name varchar(5) NOT NULL
);

create table if not exists sectiongroupsstaffs(
	courseId int NOT NULL references courses(id),
	staffId int NOT NULL references staff(id),
	GroupNumber int NOT NULL DEFAULT '0',
	branchId int not null references branch(id)
);

create table if not exists courses(
	id int primary key auto_increment,
    name varchar(255) not null,
    code varchar(255) not null,
    lecHours int not null,
    labHours int not null,
    SemesterId int not null references semester(id),
    StudyplanId int not null references studyplan(id),
    facultyId int not null references faculty(id),
    lectureGroupId int not null references lecturegroups(id),
    SectionsNumberOfGroupsID int not null references SectionsNumberOfGroups(id)
);

create table if not exists coursesstaff(
	courseId int not null references courses(id),
	staffId int not null references staff(id),
	BranchId int not null references branch(id)
);

create table if not exists days(
	id int primary key,
    day varchar(50) not null
);

create table if not exists freeTimeForRooms(
	id int primary key AUTO_INCREMENT,
	RoomId int not null references rooms(id),
    DayId int not null references days(id),
    startingTime int not null ,
    enddingTime int not null
);

create table if not exists freeTimeForStaff(
	id int primary key AUTO_INCREMENT,
	StaffId int not null references Staff(id),
    DayId int not null references days(id),
    startingTime int not null ,
    enddingTime int not null
);

create table if not exists timetabletypes(
	id int primary key auto_increment,
    Type varchar(255) not null
);

create table if not exists timetable(
	Id int primary key auto_increment,
	name varchar (255) not null,
    TypeId int not null references timetabletypes(id)
); 

create table if not exists timesintimetable(
	id int primary key auto_increment,
    timeTableId int not null references timetable(id),
    courseId int not null references courses(id),
    StaffId int not null references staff(id),
    hostingRoomID int NOT NULL references rooms(id),
	hostingBranchID int NOT NULL references branch(id),
    DayId int not null references days(id),
    startingTime int not null,
    enddingTime int not null,
    lecGroupId int not null references lecgroup(id)
);

create table if not exists subadmin(
	id int not null references staffId(id),
    branchId int not null references branch (id)
);

create table if Not exists  log(
	id int primary key auto_increment,
    oprationMade varchar(255) not null,
    userId int not null references staff(id)
);

create table if not exists login(
	userId int not null primary key references staff(id),
    username varchar(255) not null,
    password varchar(255) not null,
    role int not null
);

create table if not exists roletype(
	id int primary key AUTO_INCREMENT,
	RoleName varchar(50) not null
);