create table if not exists account (
                                      id bigint auto_increment primary key,
                                      account_number varchar(50) not null,
                                      account_type varchar(50) not null,
                                      client varchar(50) not null,
                                      initial_balance double not null,
                                      status bit not null default 0
)
