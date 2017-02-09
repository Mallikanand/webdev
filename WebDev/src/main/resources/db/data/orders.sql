insert into Orders(id, placement_date, delivery_date, status, total_value) values (1, '2016-06-10', '2016-06-10', 'D',30.00 )
insert into Orders(id, placement_date, delivery_date, status, total_value) values (2, '2016-06-10', '2016-07-10', 'D',90.00 )

insert into Order_Item(id, order_id, item_id, quantity, price, notes) values (1, 1, 1, 1, 30.00, 'Mild Spicy')
insert into Order_Item(id, order_id, item_id, quantity, price, notes) values (2, 2, 1, 1, 30.00, 'Mild Spicy')
insert into Order_Item(id, order_id, item_id, quantity, price, notes) values (3, 2, 2, 2, 30.00, 'Mild Spicy')
