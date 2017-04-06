-- begin PLACEUR_PICTURE
create unique index IDX_PLACEUR_PICTURE_UK_PATH on PLACEUR_PICTURE (PATH) where DELETE_TS is null ^
-- end PLACEUR_PICTURE

-- begin PLACEUR_PLACE
alter table PLACEUR_PLACE add constraint FK_PLACEUR_PLACE_CITY1 foreign key (CITY_ID) references PLACEUR_CITY(ID)^
alter table PLACEUR_PLACE add constraint FK_PLACEUR_PLACE_PICTURE1 foreign key (PICTURE_ID) references PLACEUR_PICTURE(ID)^
create index IDX_PLACEUR_PLACE_CITY on PLACEUR_PLACE (CITY_ID)^
create index IDX_PLACEUR_PLACE_PICTURE on PLACEUR_PLACE (PICTURE_ID)^
-- end PLACEUR_PLACE
-- begin PLACEUR_USER
alter table PLACEUR_USER add constraint FK_PLACEUR_USER_CITY1 foreign key (CITY_ID) references PLACEUR_CITY(ID)^
create unique index IDX_PLACEUR_USER_UK_MAIL1 on PLACEUR_USER (MAIL) where DELETE_TS is null ^
create unique index IDX_PLACEUR_USER_UK_NICKNAME1 on PLACEUR_USER (NICKNAME) where DELETE_TS is null ^
create index IDX_PLACEUR_USER_CITY on PLACEUR_USER (CITY_ID)^
-- end PLACEUR_USER
-- begin PLACEUR_RATING
alter table PLACEUR_RATING add constraint FK_PLACEUR_RATING_USER1 foreign key (USER_ID) references PLACEUR_USER(ID)^
alter table PLACEUR_RATING add constraint FK_PLACEUR_RATING_PLACE1 foreign key (PLACE_ID) references PLACEUR_PLACE(ID)^
create index IDX_PLACEUR_RATING_PLACE on PLACEUR_RATING (PLACE_ID)^
create index IDX_PLACEUR_RATING_USER on PLACEUR_RATING (USER_ID)^
-- end PLACEUR_RATING
-- begin PLACEUR_TOKEN
alter table PLACEUR_TOKEN add constraint FK_PLACEUR_TOKEN_USER foreign key (USER_ID) references PLACEUR_USER(ID)^
create unique index IDX_PLACEUR_TOKEN_UK_TOKEN on PLACEUR_TOKEN (TOKEN) where DELETE_TS is null ^
create unique index IDX_PLACEUR_TOKEN_UK_USER_ID on PLACEUR_TOKEN (USER_ID) where DELETE_TS is null ^
create index IDX_PLACEUR_TOKEN_USER on PLACEUR_TOKEN (USER_ID)^
-- end PLACEUR_TOKEN
