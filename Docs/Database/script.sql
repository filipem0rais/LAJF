drop database if exists bidhub;
create database bidhub;

use bidhub;

create table categories
(
    idCategory int auto_increment
        primary key,
    catName    varchar(100) not null,
    catParent  int          null,
    constraint ID_Categories_IND
        unique (idCategory),
    constraint REF_Categ_Categ_FK
        foreign key (catParent) references categories (idCategory)
);

create index REF_Categ_Categ_IND
    on categories (catParent);

create table users
(
    idUser      int auto_increment
        primary key,
    useName     varchar(100)                not null,
    useLastName varchar(100)                not null,
    useEmail    varchar(255)                not null,
    usePassword varchar(255)                not null,
    useCredit   decimal(15, 2) default 0.00 not null,
    constraint ID_Users_IND
        unique (idUser)
);

create table items
(
    idItem             int auto_increment
        primary key,
    iteDescription     varchar(255)                         not null,
    iteInitialValue    decimal(15, 2)                       not null,
    iteOnSale          tinyint(1) default 1                 not null,
    iteDatePublication datetime   default CURRENT_TIMESTAMP not null,
    idUser             int                                  not null,
    idCategory         int                                  not null,
    iteName             varchar(100)                         not null,
    iteState             varchar(100)                         not null,
    itePicture             varchar(255)                         not null,
    constraint ID_Items_IND
        unique (idItem),
    constraint REF_Items_Categ_FK
        foreign key (idCategory) references categories (idCategory),
    constraint REF_Items_Users_FK
        foreign key (idUser) references users (idUser)
);

create table bid
(
    idBid     int auto_increment
        primary key,
    bidAmount decimal(15, 2) not null,
    idUser    int            not null,
    idItem    int            not null,
    constraint ID_Bid_IND
        unique (idBid),
    constraint REF_Bid_Items_FK
        foreign key (idItem) references items (idItem),
    constraint REF_Bid_Users_FK
        foreign key (idUser) references users (idUser)
);

create index REF_Bid_Items_IND
    on bid (idItem);

create index REF_Bid_Users_IND
    on bid (idUser);

create index REF_Items_Categ_IND
    on items (idCategory);

create index REF_Items_Users_IND
    on items (idUser);

create table sale
(
    idSale      int auto_increment
        primary key,
    idItem      int                  not null,
    salPickedUp tinyint(1) default 0 not null,
    idUser      int                  not null,
    constraint ID_Sale_IND
        unique (idSale),
    constraint SID_Sale_Items_ID
        unique (idItem),
    constraint SID_Sale_Items_IND
        unique (idItem),
    constraint REF_Sale_Users_FK
        foreign key (idUser) references users (idUser),
    constraint SID_Sale_Items_FK
        foreign key (idItem) references items (idItem)
);

create index REF_Sale_Users_IND
    on sale (idUser);


