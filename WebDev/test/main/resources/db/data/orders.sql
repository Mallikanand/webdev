insert into Orders( placement_date, delivery_date, status, total_value, user_id) values ('2016-06-10', '2016-06-10', 'D',30.00 ,'a');
insert into Orders( placement_date, delivery_date, status, total_value, user_id) values ('2016-06-10', '2016-07-10', 'D',90.00 ,'a');
insert into Orders( placement_date, delivery_date, status, total_value, user_id) values ('2016-06-10', '2016-06-10', 'D',30.00 ,'a');

insert into Order_Item( order_id, item_id, quantity, price, notes) values (1, 1, 1, 30.00, 'Mild Spicy');
insert into Order_Item( order_id, item_id, quantity, price, notes) values (2, 1, 1, 30.00, 'Mild Spicy');
insert into Order_Item( order_id, item_id, quantity, price, notes) values (2, 2, 2, 30.00, 'Mild Spicy');
insert into Order_Item( order_id, item_id, quantity, price, notes) values (3, 4, 2, 30.00, 'Mild Spicy');

/*
insert into Orders(id, placement_date, delivery_date, status, total_value, user_id) values (1, '2016-06-10', '2016-06-10', 'D',30.00 ,'a');
insert into Orders(id, placement_date, delivery_date, status, total_value, user_id) values (2, '2016-06-10', '2016-07-10', 'D',90.00 ,'a');
insert into Orders(id, placement_date, delivery_date, status, total_value, user_id) values (3, '2016-06-10', '2016-06-10', 'D',30.00 ,'a');

insert into Order_Item(id, order_id, item_id, quantity, price, notes) values (1, 1, 1, 1, 30.00, 'Mild Spicy');
insert into Order_Item(id, order_id, item_id, quantity, price, notes) values (2, 2, 1, 1, 30.00, 'Mild Spicy');
insert into Order_Item(id, order_id, item_id, quantity, price, notes) values (3, 2, 2, 2, 30.00, 'Mild Spicy');
insert into Order_Item(id, order_id, item_id, quantity, price, notes) values (4, 3, 4, 2, 30.00, 'Mild Spicy');

*/