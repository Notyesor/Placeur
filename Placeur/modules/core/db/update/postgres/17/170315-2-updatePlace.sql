alter table PLACEUR_PLACE add column LATITUDE double precision not null default 0 ;
alter table PLACEUR_PLACE add column LONGITUDE double precision not null default 0 ;
alter table PLACEUR_PLACE drop column LOCATION_ID cascade ;
