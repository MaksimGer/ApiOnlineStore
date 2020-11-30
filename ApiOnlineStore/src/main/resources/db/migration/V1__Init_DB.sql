create sequence hibernate_sequence start 1 increment 1;

create table attributes (
    id int8 not null,
    name varchar(255),
    primary key (id)
);

create table categories (
    id int8 not null,
    name varchar(255),
    primary key (id)
);

create table categories_attributes (
    categories_id int8 not null,
    attributes_id int8 not null,
    primary key (categories_id, attributes_id)
);

create table categories_products (
    category_id int8 not null,
    products_id int8 not null,
    primary key (category_id, products_id)
);

create table params (
    id int8 not null,
    value varchar(255),
    attribute_id int8,
    product_id int8,
    primary key (id)
);

create table products (
    id int8 not null,
    name varchar(255),
    category_id int8,
    primary key (id)
);

create table user_roles (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    active boolean not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table if exists categories_products
    add constraint UK_rnlo43ssc3pd408u62he9udsb
    unique (products_id);

alter table if exists categories_attributes
    add constraint categoryAttributes_attribute_fk
    foreign key (attributes_id) references attributes;

alter table if exists categories_attributes
    add constraint categoryAttributes_category_fk
    foreign key (categories_id) references categories;

alter table if exists categories_products
    add constraint categoryProducts_product_fk
    foreign key (products_id) references products;

alter table if exists categories_products
    add constraint categortProducts_category_fk
    foreign key (category_id) references categories;

alter table if exists params
    add constraint params_attributes_fk
    foreign key (attribute_id) references attributes;

alter table if exists params
    add constraint params_products_fk
    foreign key (product_id) references products;

alter table if exists products
    add constraint products_categories_fk
    foreign key (category_id) references categories;

alter table if exists user_roles
    add constraint userRoles_usr_fk
    foreign key (user_id) references usr;