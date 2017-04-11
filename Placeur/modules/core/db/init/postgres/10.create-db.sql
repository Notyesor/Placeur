-- begin PLACEUR_CITY
create table PLACEUR_CITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(50) not null,
    LATITUDE double precision not null,
    LONGITUDE double precision not null,
    --
    primary key (ID)
)^
-- end PLACEUR_CITY
-- begin PLACEUR_PLACE
create table PLACEUR_PLACE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(50) not null,
    DESCRIPTION varchar(255),
    ADDRESS varchar(255) not null,
    CITY_ID uuid not null,
    LATITUDE double precision not null,
    LONGITUDE double precision not null,
    --
    primary key (ID)
)^
-- end PLACEUR_PLACE
-- begin PLACEUR_USER
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
    NICKNAME varchar(20) not null,
    MAIL varchar(255) not null,
    NAME varchar(50) not null,
    SURNAME varchar(50),
    CITY_ID uuid not null,
    PASSWORD varchar(255) not null,
    --
    primary key (ID)
)^
-- end PLACEUR_USER
-- begin PLACEUR_RATING
create table PLACEUR_RATING (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID uuid not null,
    PLACE_ID uuid not null,
    MARK double precision not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end PLACEUR_RATING
-- begin PLACEUR_TOKEN
create table PLACEUR_TOKEN (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID uuid not null,
    TOKEN varchar(255) not null,
    --
    primary key (ID)
)^
-- end PLACEUR_TOKEN
