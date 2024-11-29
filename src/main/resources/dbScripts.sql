create table users (
        id bigint not null auto_increment,
        address varchar(1000) not null,
        birth_date datetime(6) not null,
        email varchar(255) not null,
        gender varchar(255) not null,
        mobile_number varchar(10) not null,
        password varchar(255) not null,
        role enum ('ADMIN','CUSTOMER','DRIVER','FARMER','TRANSPORTER') not null,
        user_id bigint not null,
        user_name varchar(255) not null,
        primary key (id)
    )