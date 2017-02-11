alter table PLACEUR_USER add constraint FK_PLACEUR_USER_CITY foreign key (CITY_ID) references PLACEUR_CITY(ID);
alter table PLACEUR_USER add constraint FK_PLACEUR_USER_PICTURE foreign key (PICTURE_ID) references PLACEUR_PICTURE(ID);
create unique index IDX_PLACEUR_USER_UK_MAIL on PLACEUR_USER (MAIL) where DELETE_TS is null ;
create unique index IDX_PLACEUR_USER_UK_NICKNAME on PLACEUR_USER (NICKNAME) where DELETE_TS is null ;
create index IDX_PLACEUR_USER_CITY on PLACEUR_USER (CITY_ID);
create index IDX_PLACEUR_USER_PICTURE on PLACEUR_USER (PICTURE_ID);
