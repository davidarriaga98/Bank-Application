create schema if not exists account_david_db;
use account_david_db;

create table if not exists account (
                                      id bigint auto_increment primary key,
                                      account_number varchar(50) not null,
                                      account_type varchar(50) not null,
                                      client varchar(50) not null,
                                      client_id bigint not null,
                                      initial_balance decimal(15,4) not null,
                                      status bit not null default 0
);


create table if not exists movement (
                          id bigint auto_increment primary key ,
                          movement_date datetime not null,
                          movement_type varchar(50) not null,
                          amount decimal(15,4) default 0.0000 not null,
                          balance decimal(15,4) default 0.0000 not null,
                          account_id bigint not null ,
                          constraint fk_movement_account foreign key (account_id) references account(id)
);

create schema if not exists client_david_db;
use client_david_db;

create table if not exists client (
                                      id bigint auto_increment primary key,
                                      name varchar(50) not null,
                                      gender varchar(50) not null,
                                      age smallint not null,
                                      identification varchar(15) not null,
                                      address varchar(255) not null,
                                      phone varchar(20) not null,
                                      password varchar(255) not null,
                                      status bit not null default 0
);