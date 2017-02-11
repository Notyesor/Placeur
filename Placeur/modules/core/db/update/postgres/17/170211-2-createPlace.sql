alter table PLACEUR_PLACE add constraint FK_PLACEUR_PLACE_CITY foreign key (CITY_ID) references PLACEUR_CITY(ID);
alter table PLACEUR_PLACE add constraint FK_PLACEUR_PLACE_LOCATION foreign key (LOCATION_ID) references PLACEUR_LOCATION(ID);
alter table PLACEUR_PLACE add constraint FK_PLACEUR_PLACE_PICTURE foreign key (PICTURE_ID) references PLACEUR_PICTURE(ID);
create index IDX_PLACEUR_PLACE_CITY on PLACEUR_PLACE (CITY_ID);
create index IDX_PLACEUR_PLACE_LOCATION on PLACEUR_PLACE (LOCATION_ID);
create index IDX_PLACEUR_PLACE_PICTURE on PLACEUR_PLACE (PICTURE_ID);
