create table if not exists Car(
    id varchar(4) not null,
    plateNumber varchar(25) not null primary key,
    ownerName varchar(25) not null,
    driverName varchar(25) not null,
    seatsCount varchar(25) not null,
    carBrand varchar(10) not null,
    carType varchar(10) not null
);

create table if not exists Taco(
id identity,
name varchar(50) not null,
createdAt timestamp not null
);

create table if not exists Taco_Ingredients(
taco bigint not null,
ingredient varchar(4) not null
);

alter table Taco_Ingredients
add foreign key (taco) references Taco(id);
alter table Taco_Ingredients
add foreign key (ingredient) references Ingredient(id);

create table if not exists Taco_Order(
id identity,
deliveryName varchar(50) not null,
deliveryStreet varchar(50) not null,
deliveryCity varchar(50) not null,
deliveryState varchar(2) not null,
deliveryZip varchar(10) not null,
ccNumber varchar(16) not null,
ccExpiration varchar(5) not null,
ccCVV varchar(3) not null,
placeAt timestamp not null
);

create table if not exists Taco_Order_Tacos(
tacoOrder bigint not null,
taco bigint not null,
);


create table if not exists users(
    username varchar2(50) not null primary key,
	password varchar2(50) not null,
	enabled char(1) default '1');

create table if not exists authorities (
    username varchar2(50) not null,
	authority varchar2(50) not null,
	constraint fk_authorities_users
	   foreign key(username) references users(username));

create unique index ix_auth_username
    on authorities (username, authority);

alter table Taco_Order_Tacos
add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
add foreign key (taco) references Taco(id)