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
);
