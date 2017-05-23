alter table PLACEUR_RATING add column IS_RECOMMENDED boolean not null default false ;
alter table PLACEUR_RATING drop column IS_RECOMENDED cascade ;
