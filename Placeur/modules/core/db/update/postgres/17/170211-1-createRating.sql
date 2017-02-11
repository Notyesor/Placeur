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
    IS_RECOMENDED boolean not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
);
