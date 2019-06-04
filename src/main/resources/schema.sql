CREATE TABLE  IF NOT EXISTS orders  (
 id bigint primary key auto_increment not null ,
 name varchar(255) not null
);


CREATE TABLE  IF NOT EXISTS product  (
    id bigint primary key auto_increment not null ,
    name varchar(255) not null,
    price double not null
);

//truncate orders ;