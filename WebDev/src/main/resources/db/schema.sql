/** 
 * Tables
 * ------
 * MenuItem:item_name(PK),menu_type, food_type, price, inactive
 * Order: id(PK), total_value, order_date, delivery_date, delivery_address_id(FK), user_id(FK), status
 * User:user_id(PK), registered_address_id(FK), password, email, active, creation_date, 
 * Address: id(PK), address_id, added_date, verified
 * 
 * mapping tables
 * --------------
 * Order_Item: order_id(PK),item_name(PK), price, quantity
 * 
 * Relationships
 * -------------
 * Order:MenuItem - N:N
 * User:Order - 1:N
 * 
 */
create table MenuItem (
	item_name varchar(50) not null, 
	menu_type varchar(15) not null,-- Indian, Italian, Continental,.. others
	food_type varchar(15) not null,-- Food, Drink, Condiments,.. others
	price numeric(19,2) not null,
	inactive varchar(1) not null,
	Primary Key (item_name)
);

