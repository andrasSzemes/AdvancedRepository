drop table if exists category_tag;
drop table if exists custom_tag;
drop table if exists favorite_shop;
drop table if exists group_account;
drop table if exists line_item;
drop table if exists list_line_items;
drop table if exists member;
drop table if exists member_groups;
drop table if exists product;
drop table if exists products_custom_tags;
drop table if exists saved_products;
drop table if exists shopping_list;

create table custom_tag
(
  id serial,
  name varchar
);


create table category_tag
(
  id serial,
  name varchar
);


create table product
(
  id serial,
  name varchar,
  category_tag_id int,
  picture_URL varchar
);


create table products_custom_tags
(
  product_id int,
  custom_tag_id int
);


create table group_account
(
  id serial,
  name varchar,
  picture_URL varchar
);


create table saved_products
(
  product_id int,
  group_id int
);


create table member
(
  id serial,
  name varchar,
  email_address varchar,
  hashed_password varchar,
  status varchar
);


create table member_groups
(
  group_id int,
  member_id int
);


create table shopping_list
(
  id serial,
  associated_shop_id int,
  archived boolean,
  member_id int,
  group_id int
);


create table line_item
(
  id serial,
  quantity varchar,
  archived boolean,
  product_id int
);


create table list_line_items
(
  shopping_list_id int,
  line_item_id int
);


create table favorite_shop
(
  id serial,
  name varchar,
  group_id int,
  address varchar,
  personal_note varchar
)