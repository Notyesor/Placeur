create table PLACEUR_USER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NICKNAME varchar(255) not null,
    MAIL varchar(255) not null,
    NAME varchar(255) not null,
    SURNAME varchar(255),
    BIRTHDAY date not null,
    CITY_ID uuid not null,
    PICTURE_ID uuid,
    --
    primary key (ID)
);
