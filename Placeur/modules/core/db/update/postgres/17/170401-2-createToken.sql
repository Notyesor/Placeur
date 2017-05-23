alter table PLACEUR_TOKEN add constraint FK_PLACEUR_TOKEN_USER foreign key (USER_ID) references PLACEUR_USER(ID);
create unique index IDX_PLACEUR_TOKEN_UK_TOKEN on PLACEUR_TOKEN (TOKEN) where DELETE_TS is null ;
create unique index IDX_PLACEUR_TOKEN_UK_USER_ID on PLACEUR_TOKEN (USER_ID) where DELETE_TS is null ;
create index IDX_PLACEUR_TOKEN_USER on PLACEUR_TOKEN (USER_ID);
