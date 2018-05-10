insert into role(role) values ('ADMIN');
insert into role(role) values ('USER');

insert into Users( user_id, password, first_name, last_name, gender, enabled) values ('a', 'p', 'A', 'AL','M', true);
insert into user_role( user_id, role) values ('a' , 'USER');

insert into Users( user_id, password, first_name, last_name, gender, enabled) values ('b', 'bp', 'B', 'BL','M', true);
insert into user_role(user_id, role) values ('b' , 'USER');

/*

insert into Users(id, user_id, password, first_name, last_name, gender, enabled) values (1, 'a', 'p', 'A', 'AL','M', true);
insert into user_role(id, user_id, role) values (1, 'a' , 'USER');

insert into Users(id, user_id, password, first_name, last_name, gender, enabled) values (2, 'b', 'bp', 'B', 'BL','M', true);
insert into user_role(id, user_id, role) values (2, 'b' , 'USER');

*/