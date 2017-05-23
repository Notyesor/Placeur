alter table PLACEUR_USER add column SIMILARITY integer not null default 0 ;
alter table PLACEUR_USER drop column MAIL cascade ;
alter table PLACEUR_USER drop column NAME cascade ;
alter table PLACEUR_USER drop column SURNAME cascade ;
