insert into Orders(order_id, placement_date, delivery_date, status, total_value) values (1, '10-Jun-2016', '11-JUN-2016', 'D',30.00 )
insert into Orders(order_id, placement_date, delivery_date, status, total_value) values (2, '10-Jun-2016', '11-JUN-2016', 'D',90.00 )

insert into Order_Item(order_item_id, order_id, item_id, quantity, price, notes) values (1, 1, 1, 1, 30.00, 'Mild Spicy')
insert into Order_Item(order_item_id, order_id, item_id, quantity, price, notes) values (2, 2, 1, 1, 30.00, 'Mild Spicy')
insert into Order_Item(order_item_id, order_id, item_id, quantity, price, notes) values (3, 2, 2, 2, 30.00, 'Mild Spicy')
