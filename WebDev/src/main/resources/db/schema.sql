create table MenuItem (
	item_name varchar(50) not null, 
	menu_type varchar(15) not null,-- Indian, Italian, Continental,.. others
	food_type varchar(15) not null,-- Food, Drink, Condiments,.. others
	price numeric(19,2) not null,
	inactive varchar(1) not null,
	Primary Key (item_name)
);
