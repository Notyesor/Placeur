-- begin PLACEUR_PICTURE
create unique index IDX_PLACEUR_PICTURE_UK_PATH on PLACEUR_PICTURE (PATH) where DELETE_TS is null ^
-- end PLACEUR_PICTURE
-- begin PLACEUR_CITY
alter table PLACEUR_CITY add constraint FK_PLACEUR_CITY_LOCATION foreign key (LOCATION_ID) references PLACEUR_LOCATION(ID)^
create unique index IDX_PLACEUR_CITY_UK_LOCATION_ID on PLACEUR_CITY (LOCATION_ID) where DELETE_TS is null ^
create index IDX_PLACEUR_CITY_LOCATION on PLACEUR_CITY (LOCATION_ID)^
-- end PLACEUR_CITY
-- begin PLACEUR_PLACE
alter table PLACEUR_PLACE add constraint FK_PLACEUR_PLACE_CITY foreign key (CITY_ID) references PLACEUR_CITY(ID)^
alter table PLACEUR_PLACE add constraint FK_PLACEUR_PLACE_LOCATION foreign key (LOCATION_ID) references PLACEUR_LOCATION(ID)^
alter table PLACEUR_PLACE add constraint FK_PLACEUR_PLACE_PICTURE foreign key (PICTURE_ID) references PLACEUR_PICTURE(ID)^
create index IDX_PLACEUR_PLACE_CITY on PLACEUR_PLACE (CITY_ID)^
create index IDX_PLACEUR_PLACE_LOCATION on PLACEUR_PLACE (LOCATION_ID)^
create index IDX_PLACEUR_PLACE_PICTURE on PLACEUR_PLACE (PICTURE_ID)^
-- end PLACEUR_PLACE
-- begin PLACEUR_USER
alter table PLACEUR_USER add constraint FK_PLACEUR_USER_CITY foreign key (CITY_ID) references PLACEUR_CITY(ID)^
alter table PLACEUR_USER add constraint FK_PLACEUR_USER_PICTURE foreign key (PICTURE_ID) references PLACEUR_PICTURE(ID)^
create unique index IDX_PLACEUR_USER_UK_MAIL on PLACEUR_USER (MAIL) where DELETE_TS is null ^
create unique index IDX_PLACEUR_USER_UK_NICKNAME on PLACEUR_USER (NICKNAME) where DELETE_TS is null ^
create index IDX_PLACEUR_USER_CITY on PLACEUR_USER (CITY_ID)^
create index IDX_PLACEUR_USER_PICTURE on PLACEUR_USER (PICTURE_ID)^
-- end PLACEUR_USER
-- begin PLACEUR_RATING
alter table PLACEUR_RATING add constraint FK_PLACEUR_RATING_USER foreign key (USER_ID) references PLACEUR_USER(ID)^
alter table PLACEUR_RATING add constraint FK_PLACEUR_RATING_PLACE foreign key (PLACE_ID) references PLACEUR_PLACE(ID)^
create index IDX_PLACEUR_RATING_PLACE on PLACEUR_RATING (PLACE_ID)^
create index IDX_PLACEUR_RATING_USER on PLACEUR_RATING (USER_ID)^
-- end PLACEUR_RATING
