alter table PLACEUR_CITY add column LATITUDE double precision not null default 0 ;
alter table PLACEUR_CITY add column LONGITUDE double precision not null default 0 ;
alter table PLACEUR_CITY drop column LOCATION_ID cascade ;
