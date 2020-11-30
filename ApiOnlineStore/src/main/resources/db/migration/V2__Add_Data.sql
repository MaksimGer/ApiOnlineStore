INSERT into attributes (id, name) values
    (nextval('hibernate_sequence'), 'price'),
    (nextval('hibernate_sequence'), 'count'),
	(nextval('hibernate_sequence'), 'weight'),
	(nextval('hibernate_sequence'), 'colour'),
	(nextval('hibernate_sequence'), 'screen resolution');

INSERT into categories (id, name) values
    (nextval('hibernate_sequence'), 'TV'),
    (nextval('hibernate_sequence'), 'phone'),
    (nextval('hibernate_sequence'), 'headphones');


INSERT into categories_attributes (categories_id, attributes_id) values
    (6, 1),
    (6, 2),
    (6, 3),
    (6, 4),
	(6, 5),
	(7, 1),
    (7, 2),
    (7, 3),
    (7, 4),
	(7, 5),
	(8, 1),
    (8, 2),
    (8, 3),
    (8, 4);

INSERT into products (id, name, category_id) values
    (nextval('hibernate_sequence'), 'Samsung UE24H4070', 6),
    (nextval('hibernate_sequence'), 'LG 24TN510S-WZ', 6),
    (nextval('hibernate_sequence'), 'Samsung LT32E310EX', 6),
    (nextval('hibernate_sequence'), 'Samsung note 20', 7),
    (nextval('hibernate_sequence'), 'IPhone SE 2020', 7),
    (nextval('hibernate_sequence'), 'JBL T460BT', 8),
    (nextval('hibernate_sequence'), 'cg pods', 8);


INSERT into categories_products (category_id, products_id) values
    (6, 9),
    (6, 10),
    (6, 11),
    (7, 12),
	(7, 13),
	(8, 14),
    (8, 15);


INSERT into params (id, value, product_id, attribute_id) values
	(nextval('hibernate_sequence'), '14000',	 9, 1),
	(nextval('hibernate_sequence'), '10', 		 9, 2),
	(nextval('hibernate_sequence'), '1500',     9, 3),
	(nextval('hibernate_sequence'), 'black',    9, 4),
	(nextval('hibernate_sequence'), '1366х768', 9, 5),
	(nextval('hibernate_sequence'), '12000',    10, 1),
	(nextval('hibernate_sequence'), '7',        10, 2),
	(nextval('hibernate_sequence'), '1800',     10, 3),
	(nextval('hibernate_sequence'), 'green',    10, 4),
	(nextval('hibernate_sequence'), '1366x768', 10, 5),
	(nextval('hibernate_sequence'), '18500',    11, 1),
	(nextval('hibernate_sequence'), '4',        11, 2),
	(nextval('hibernate_sequence'), '1300', 	 11, 3),
	(nextval('hibernate_sequence'), 'black',    11, 4),
	(nextval('hibernate_sequence'), '1366x768', 11, 5),
	(nextval('hibernate_sequence'), '69 990',    12, 1),
	(nextval('hibernate_sequence'), '10', 		 12, 2),
	(nextval('hibernate_sequence'), '208',     12, 3),
	(nextval('hibernate_sequence'), 'blue',    12, 4),
	(nextval('hibernate_sequence'), '3200x1440', 12, 5),
	(nextval('hibernate_sequence'), '39990',    13, 1),
	(nextval('hibernate_sequence'), '12', 		 13, 2),
	(nextval('hibernate_sequence'), '148', 	 13, 3),
	(nextval('hibernate_sequence'), 'red', 	 13, 4),
	(nextval('hibernate_sequence'), '1334x750', 13, 5),
	(nextval('hibernate_sequence'), '1500',    14, 1),
	(nextval('hibernate_sequence'), '5', 		 14, 2),
	(nextval('hibernate_sequence'), '400', 	 14, 3),
	(nextval('hibernate_sequence'), 'white', 	 14, 4),
	(nextval('hibernate_sequence'), '3500',    15, 1),
	(nextval('hibernate_sequence'), '3', 		 15, 2),
	(nextval('hibernate_sequence'), '250', 	 15, 3),
	(nextval('hibernate_sequence'), 'black', 	 15, 4);

INSERT into usr (id, active, password, username) values
    (1, false, 'admin', 'admin');

INSERT into user_roles (user_id, roles) values
    (1, 'USER'),
    (1, 'ADMIN');