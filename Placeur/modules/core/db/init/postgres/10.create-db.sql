-- begin PLACEUR_LOCATION
create table PLACEUR_LOCATION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    LATITUDE double precision not null,
    LONGITUDE double precision not null,
    --
    primary key (ID)
)^
-- end PLACEUR_LOCATION
-- begin PLACEUR_PICTURE
create table PLACEUR_PICTURE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PATH varchar(255) not null,
    --
    primary key (ID)
)^
-- end PLACEUR_PICTURE
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
    TITLE varchar(255) not null,
    LOCATION_ID uuid not null,
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
    TITLE varchar(255) not null,
    DESCRIPTION varchar(255),
    ADDRESS varchar(255) not null,
    CITY_ID uuid not null,
    LOCATION_ID uuid not null,
    PICTURE_ID uuid,
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
    NICKNAME varchar(255) not null,
    MAIL varchar(255) not null,
    NAME varchar(255) not null,
    SURNAME varchar(255),
    BIRTHDAY date not null,
    CITY_ID uuid not null,
    PICTURE_ID uuid,
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
    IS_RECOMMENDED boolean not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end PLACEUR_RATING
