INSERT into attributes (id, name) values
	(nextval('hibernate_sequence'), 'weight'),
	(nextval('hibernate_sequence'), 'colour'),
	(nextval('hibernate_sequence'), 'screen resolution');

INSERT into categories (id, name) values
    (nextval('hibernate_sequence'), 'TV'),
    (nextval('hibernate_sequence'), 'phone'),
    (nextval('hibernate_sequence'), 'headphones');


INSERT into categories_attributes (categories_id, attributes_id) values
    (4, 1),
    (4, 2),
    (4, 3),
	(5, 1),
    (5, 2),
    (5, 3),
	(6, 1),
    (6, 2);

INSERT into products (id, name, price, count, category_id) values
    (nextval('hibernate_sequence'), 'Samsung UE24H4070', 14000.0, 10, 4),
    (nextval('hibernate_sequence'), 'LG 24TN510S-WZ', 12000.0, 7, 4),
    (nextval('hibernate_sequence'), 'Samsung LT32E310EX', 18500.0, 4, 4),
    (nextval('hibernate_sequence'), 'Samsung note 20', 69990.0, 10, 5),
    (nextval('hibernate_sequence'), 'IPhone SE 2020', 39990.0, 12, 5),
    (nextval('hibernate_sequence'), 'JBL T460BT', 1500.0, 5, 6),
    (nextval('hibernate_sequence'), 'cg pods', 3500, 3, 6);


INSERT into categories_products (category_id, products_id) values
    (4, 7),
    (4, 8),
    (4, 9),
    (5, 10),
	(5, 11),
	(6, 12),
    (6, 13);

INSERT into params (id, value, product_id, attribute_id) values
	(nextval('hibernate_sequence'), '1500',     7, 1),
	(nextval('hibernate_sequence'), 'black',    7, 2),
	(nextval('hibernate_sequence'), '1366х768', 7, 3),
	(nextval('hibernate_sequence'), '1800',     8, 1),
	(nextval('hibernate_sequence'), 'green',    8, 2),
	(nextval('hibernate_sequence'), '1366x768', 8, 3),
	(nextval('hibernate_sequence'), '1300', 	9, 1),
	(nextval('hibernate_sequence'), 'black',    9, 2),
	(nextval('hibernate_sequence'), '1366x768', 9, 3),
	(nextval('hibernate_sequence'), '208',     10, 1),
	(nextval('hibernate_sequence'), 'blue',    10, 2),
	(nextval('hibernate_sequence'), '3200x1440', 10, 3),
	(nextval('hibernate_sequence'), '148', 	 11, 1),
	(nextval('hibernate_sequence'), 'red', 	 11, 2),
	(nextval('hibernate_sequence'), '1334x750', 11, 3),
	(nextval('hibernate_sequence'), '400', 	 12, 1),
	(nextval('hibernate_sequence'), 'white', 	 12, 2),
	(nextval('hibernate_sequence'), '250', 	 13, 1),
	(nextval('hibernate_sequence'), 'black', 	 13, 2);

INSERT into usr (id, active, password, username) values
    (1, false, '$2y$12$lHcgzUJzqlg56mcSeO.qPugoKl5os7X.cib0UUHKpBdwyKCv6820u', 'admin'), /*admin:admin*/
    (2, false, '$2y$12$hNhdFmaKIjeSGQNUYVxj6.wyHxKY/w4HkS/QsHmmV1hGuO14s6es.', 'guest'); /*guest:password*/

INSERT into user_roles (user_id, roles) values
    (1, 'USER'),
    (1, 'ADMIN'),
    (2, 'USER');