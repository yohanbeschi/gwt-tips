drop all objects;

-- User Sequence
create sequence if not exists SEQ_GWTTIPS_ID start with 1000 increment by 1 cache 20;

-- User 
create table R_USER(
    USR_ID                bigint not null,
    USR_LOGIN             varchar (32) not null,
    USR_PASSWORD          varchar (32) not null,
    USR_LAST_NAME         varchar (250),
    USR_FIRST_NAME        varchar (250),
    USR_EMAIL             varchar (250) not null,

    primary key (USR_ID)
);