/** 
 * Tables
 * ------
 * MenuItem:item_name(PK),menu_type, food_type, price, inactive
 * Order: id(PK), total_value, placement_date, delivery_date, delivery_address_id(FK), user_id(FK), status
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
	id bigint identity(1,1) not null,
	item_name varchar(50) not null, 
	menu_type varchar(15) not null,-- Indian, Italian, Continental,.. others
	food_type varchar(15) not null,-- Food, Drink, Condiments,.. others
	price numeric(19,2) not null,
	inactive varchar(1) not null,
	Primary Key (id)
);

create table Orders (
	id bigint identity(1,1) not null, 
	placement_date date not null, 
	delivery_date date not null,
	status varchar(1) not null, -- O for Open, P for Processing, D for Delivered
	total_value numeric(19,2) not null,
	user_id varchar(20) null,  --//create an index for this column
	Primary Key (id)
);

create table Order_Item (
	id bigint identity(1,1) not null,
	order_id int not null, -- FK to Order.id
	item_id int not null , -- FK to MenuItem.id
	quantity int not null, 
	price numeric(19,2) not null, 
	notes varchar(250),
	Primary Key (id)
);

create table User (
	id bigint identity(1,1) not null,
	user_id varchar(250) not null unique,
	first_name varchar(250) not null,
	last_name varchar(250) not null,
	gender char(1) not null,
	Primary Key (id)
);

Alter table Orders
Add constraint order_User_FK
Foreign Key (user_id) References User(user_id);

Alter table Order_Item
Add constraint orderItem_Order_FK
Foreign Key (order_id) References Orders;

Alter table Order_Item
Add Constraint OrderItem_MenuItem_FK
Foreign Key (item_id) References MenuItem;
