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
);
