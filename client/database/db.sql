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
)