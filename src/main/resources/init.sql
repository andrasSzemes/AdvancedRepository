create table Custom_tag
(
  id serial,
  name varchar
);


create table Category_tag
(
  id serial,
  name varchar
);


create table Product
(
  id serial,
  name varchar,
  category_tag_id int,
  picture_URL varchar
);


create table Products_custom_tags
(
  product_id int,
  custom_tag_id int
);


create table Group_account
(
  id serial,
  name varchar,
  picture_URL varchar
);


create table Saved_products
(
  product_id int,
  group_id int
);


create table Member
(
  id serial,
  name varchar,
  email_address varchar,
  hashed_password varchar,
  status varchar
);


create table Member_groups
(
  group_id int,
  member_id int
);


create table Shopping_list
(
  id serial,
  associated_shop_id int,
  archived boolean,
  member_id int,
  group_id int
);


create table Line_item
(
  id serial,
  quantity varchar,
  archived boolean,
  product_id int
);


create table List_line_items
(
  shopping_list_id int,
  line_item_id int
);


create table Favorite_shop
(
  id serial,
  name varchar,
  group_id int,
  address varchar,
  personal_note varchar
)