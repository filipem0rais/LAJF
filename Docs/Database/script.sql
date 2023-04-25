-- *********************************************
-- * Standard SQL generation                   
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 14 2021              
-- * Generation date: Tue Apr 25 10:33:48 2023 
-- * LUN file: C:\DATA\LAJF\Docs\Database\LAJF.lun 
-- * Schema: LAJF/SQL 
-- ********************************************* 


-- Database Section
-- ________________ 

drop database if exists LAJF;
create database LAJF;
use LAJF;

-- DBSpace Section
-- _______________


-- Tables Section
-- _____________ 

create table Categories (
     idCategory int not null auto_increment,
     catName varchar(100) not null,
     catParent int,
     constraint ID_Categories_ID primary key (idCategory));

create table Items (
     idItem int not null auto_increment,
     iteDescription varchar(255) not null,
     iteInitialValue decimal(15,2) not null,
     iteOnSale bool not null default 1,
     iteDatePublication datetime not null default current_timestamp,
     idUser int not null,
     idCategory int not null,
     constraint ID_Items_ID primary key (idItem));

create table Bid (
     idBid int not null auto_increment,
     bidAmount decimal(15,2) not null,
     idUser int not null,
     idItem int not null,
     constraint ID_Bid_ID primary key (idBid));

create table Sale (
     idSale int not null auto_increment,
     idItem int not null,
     salPickedUp bool not null default 0,
     idUser int not null,
     constraint ID_Sale_ID primary key (idSale),
     constraint SID_Sale_Items_ID unique (idItem));

create table Users (
     idUser int not null auto_increment,
     useName varchar(100) not null,
     useLastName varchar(100) not null,
     useEmail varchar(255) not null,
     usePassword varchar(255) not null,
     useCredit decimal(15,2) not null default 0.00,
     constraint ID_Users_ID primary key (idUser));


-- Constraints Section
-- ___________________ 

alter table Categories add constraint REF_Categ_Categ_FK
     foreign key (catParent)
     references Categories (idCategory);

alter table Items add constraint REF_Items_Users_FK
     foreign key (idUser)
     references Users (idUser);

alter table Items add constraint REF_Items_Categ_FK
     foreign key (idCategory)
     references Categories (idCategory);

alter table Bid add constraint REF_Bid_Users_FK
     foreign key (idUser)
     references Users (idUser);

alter table Bid add constraint REF_Bid_Items_FK
     foreign key (idItem)
     references Items (idItem);

alter table Sale add constraint REF_Sale_Users_FK
     foreign key (idUser)
     references Users (idUser);

alter table Sale add constraint SID_Sale_Items_FK
     foreign key (idItem)
     references Items (idItem);


-- Index Section
-- _____________ 

create unique index ID_Categories_IND
     on Categories (idCategory);

create index REF_Categ_Categ_IND
     on Categories (catParent);

create unique index ID_Items_IND
     on Items (idItem);

create index REF_Items_Users_IND
     on Items (idUser);

create index REF_Items_Categ_IND
     on Items (idCategory);

create unique index ID_Bid_IND
     on Bid (idBid);

create index REF_Bid_Users_IND
     on Bid (idUser);

create index REF_Bid_Items_IND
     on Bid (idItem);

create unique index ID_Sale_IND
     on Sale (idSale);

create index REF_Sale_Users_IND
     on Sale (idUser);

create unique index SID_Sale_Items_IND
     on Sale (idItem);

create unique index ID_Users_IND
     on Users (idUser);

