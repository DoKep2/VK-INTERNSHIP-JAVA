create table album
(
    id      bigint not null
        primary key,
    title   varchar(255),
    user_id bigint
);

alter table album
    owner to postgres;

create table my_user
(
    id       bigint not null
        primary key,
    email    varchar(255),
    name     varchar(255),
    phone    varchar(255),
    username varchar(255),
    website  varchar(255)
);

alter table my_user
    owner to postgres;

create table post
(
    id      bigint not null
        primary key,
    body    varchar(255),
    title   varchar(255),
    user_id bigint
);

alter table post
    owner to postgres;

create table role
(
    id   bigserial
        primary key,
    name varchar(255)
        constraint uk_8sewwnpamngi6b1dwaa88askk
            unique
);

alter table role
    owner to postgres;

create table user_account
(
    id       bigserial
        primary key,
    password varchar(255),
    username varchar(255)
        constraint uk_castjbvpeeus0r8lbpehiu0e4
            unique
);

alter table user_account
    owner to postgres;

create table user_roles
(
    user_id bigint not null
        constraint fk10xshnvsvc4prd1ldgoh0v8oa
            references user_account,
    role_id bigint not null
        constraint fkrhfovtciq1l558cw6udg0h0d3
            references role,
    primary key (user_id, role_id)
);

alter table user_roles
    owner to postgres;

