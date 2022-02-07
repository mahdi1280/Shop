create table if not exists admin
(
    id           serial primary key,
    username     varchar(200),
    password     varchar(200),
    nationalCode char(11)
    );

create table if not exists category
(
    id          serial primary key,
    title       varchar(200),
    description text,
    category_id integer default null,
    constraint fk_category_id foreign key (category_id) references category (id)
    );

create table if not exists customer
(
    id       serial primary key,
    username varchar(200),
    password varchar(200),
    address  varchar(200)
    );
create table if not exists product
(
    id          serial primary key,
    name        varchar(200),
    description text,
    category_id integer,
    qty         integer,
    price       integer,
    constraint fk_category_id foreign key (category_id) references category (id)

    );

create table if not exists shopping_card
(
    id    serial primary key,
    date  date,
    payed boolean,
    customer_id integer,
    constraint fk_customer_id foreign key (customer_id) references customer (id)
    );
create table if not exists orders
(
    id               serial primary key,
    qty integer ,
    product_id       integer,
    customer_id      integer,
    shopping_card_id integer,
    constraint fk_product_id foreign key (product_id) references product (id),
    constraint fk_customer_id foreign key (customer_id) references customer (id),
    constraint fk_shopping_card_id foreign key (shopping_card_id) references shopping_card (id)
    );