insert into role(role) values ('ADMIN')
insert into role(role) values ('USER')

insert into User(id, user_id, password, first_name, last_name, gender, enabled) values (1, 'a', 'p', 'A', 'AL','M', true)

insert into user_role(id, user_id, role) values (1, 'a' , 'USER')
