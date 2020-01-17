create schema wbhz collate gbk_chinese_ci;

create table adminster
(
	id varchar(20) not null
		primary key,
	user_name varchar(20) not null,
	user_password varchar(20) not null,
	name varchar(20) charset utf8 not null,
	phone_number varchar(20) not null
)
charset=utf8;

create table flighttable
(
	id int(20) auto_increment
		primary key,
	flight_number varchar(20) not null,
	take_off_time datetime not null,
	flying_time int(10) not null,
	start_place varchar(20) charset utf8 not null,
	end_place varchar(20) charset utf8 not null,
	tickets int(20) not null,
	price float not null,
	alltickets int(20) not null
)
charset=utf8;

create table linkuser
(
	id int auto_increment
		primary key,
	user_id varchar(45) not null,
	pass_word varchar(45) default '12345' not null,
	user_name varchar(45) not null,
	sex varchar(45) charset utf8 default '无' not null,
	phone_number varchar(45) not null,
	count int default 0 not null,
	email varchar(45) default 'XXX@XXX.com' not null,
	address varchar(45) charset utf8 default '无' not null,
	name varchar(45) not null,
	buy_name varchar(45) charset utf8 default '0' not null
)
charset=utf8;

create table record
(
	id int(20) auto_increment,
	name varchar(20) charset utf8 not null,
	user_id varchar(20) not null,
	order_number varchar(100) not null,
	start_place varchar(20) charset utf8 not null,
	end_place varchar(20) charset utf8 not null,
	price float not null,
	buy_time datetime not null,
	take_off_time datetime not null,
	return_ticket_time datetime not null,
	user_name varchar(45) not null,
	flight_number varchar(45) not null,
	seat int(10) default 0 not null,
	can_used varchar(35) charset utf8 default '未使用' not null,
	buy_name varchar(45) charset utf8 default '0' not null,
	primary key (id, flight_number)
)
charset=utf8;

create table user
(
	id int(20) auto_increment
		primary key,
	user_id varchar(20) charset latin1 not null,
	name varchar(20) not null,
	user_name varchar(20) not null,
	pass_word varchar(20) charset latin1 not null,
	sex varchar(20) not null,
	phone_number varchar(20) charset latin1 not null,
	count int default 0 not null,
	email varchar(45) not null,
	address varchar(45) not null
)
charset=utf8;

INSERT INTO wbhz.adminster (id, user_name, user_password, name, phone_number) VALUES ('1', 'admin', 'cwj199804024818', '陈伟杰', '18252601398');
INSERT INTO wbhz.flighttable (id, flight_number, take_off_time, flying_time, start_place, end_place, tickets, price, alltickets) VALUES (1, 'as1234', '2020-01-03 00:00:00', 6, '天朝', '泰州', 38, 800, 40);
INSERT INTO wbhz.linkuser (id, user_id, pass_word, user_name, sex, phone_number, count, email, address, name, buy_name) VALUES (1, '321202199804024818', '12345', '321202199804024818', '无', '18252601398', 0, 'XXX@XXX.com', '无', '死胖子', '陈伟杰');
INSERT INTO wbhz.record (id, name, user_id, order_number, start_place, end_place, price, buy_time, take_off_time, return_ticket_time, user_name, flight_number, seat, can_used, buy_name) VALUES (1, '陈伟杰', '321202199804024819', '8998361559', '天朝', '泰州', 800, '2020-01-14 18:39:22', '2020-01-03 00:00:00', '2020-01-02 22:00:00', 'xiaoaoao', 'as1234', 1, '未使用', '陈伟杰');
INSERT INTO wbhz.record (id, name, user_id, order_number, start_place, end_place, price, buy_time, take_off_time, return_ticket_time, user_name, flight_number, seat, can_used, buy_name) VALUES (2, '死胖子', '321202199804024818', '8999006706', '天朝', '泰州', 800, '2020-01-14 18:50:07', '2020-01-03 00:00:00', '2020-01-02 22:00:00', 'xiaoaoao', 'as1234', 2, '未使用', '陈伟杰');
INSERT INTO wbhz.user (id, user_id, name, user_name, pass_word, sex, phone_number, count, email, address) VALUES (1, '321202199804024818', '陈伟杰', 'xiaoao', 'cwj19980402', '男', '18252601398', 4, 'j11k22@126.com', '地球');
INSERT INTO wbhz.user (id, user_id, name, user_name, pass_word, sex, phone_number, count, email, address) VALUES (2, '321202199804024819', '陈伟杰', 'xiaoaoaoao', 'cwj19980402', '男', '18252601398', 0, 'J11K22@126.com', '火星');