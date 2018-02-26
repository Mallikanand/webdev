--insert into Orders(id, placement_date, delivery_date, status, total_value, user_id) values (2, '2016-06-10', '2016-07-10', 'D',90.00 ,'a')
--insert into MenuItem(id,item_name, menu_type, food_type, price, inactive) values (1,'VEG BIRGYANI', 'INDIAN', 'FOOD', 35.00, 'Y')
insert into User(id, user_id, first_name, last_name, gender) values (1, 'a', 'A', 'AL','M')
--insert into User(id, user_id, first_name, last_name, gender) values (2, 'b', 'B', 'BL','F')
--insert into User(id, user_id, first_name, last_name, gender) values (3, 'c', 'C', 'CL','M')

/*create table User (
	id bigint identity(1,1) not null,
	user_id varchar(250) not null unique,
	first_name varchar(250) not null,
	last_name varchar(250) not null,
	gender char(1) not null
	Primary Key (id)
);*/