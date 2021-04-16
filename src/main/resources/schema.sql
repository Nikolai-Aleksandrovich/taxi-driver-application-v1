create table if not exists Poi(
    id varchar(4) not null primary key,
    idCode varchar(25) not null,
    name varchar(25) not null,
    type varchar(25) not null,
    useType varchar(25) not null,
    checkInCount varchar(10) not null,
    userCount varchar(10) not null,
    latitude varchar(25) not null,
    longitude varchar(25) not null
);


create table if not exists Poi_Dest(
    dest bigint not null,
    ingredient varchar(4) not null
);


alter table Poi_Dest
add foreign key (dest) references Dest(id);

alter table Poi_Dest
add foreign key (ingredient) references Poi(id);



create table if not exists Dest(
--    id varchar(4) not null primary key,
    id identity,
    tripName varchar(50) not null,
    createdAt timestamp not null,
);



create table if not exists User_Order_Dest(
userOrder bigint not null,
dest bigint not null,
);




create table if not exists User_Order(
--    id varchar(4) not null primary key,
    id identity,
    placedAt timeStamp not null ,
    userName varchar(25) not null,
    userPhoneNumber varchar(25) not null,
    destLongitude varchar(25) not null,
    destLatitude varchar(25) not null,
    destProvince varchar(25) not null,
    destCity varchar(25) not null,
    destDistrict varchar(25) not null,
    destRoad varchar(25) not null,
    destLocationName varchar(25) not null,

    startLongitude varchar(25) not null,
    startLatitude varchar(25) not null,

    startProvince varchar(25) not null,
    startCity varchar(25) not null,
    startDistrict varchar(25) not null,
    startRoad varchar(25) not null,
    startLocationName varchar(25) not null,

    startTime timeStamp not null,

    passengerNumbers varchar(25) not null,

);

alter table User_Order_Dest
add foreign key (userOrder) references User_Order(id);

alter table User_Order_Dest
add foreign key (dest) references Dest(id);


create table if not exists Car(
    id varchar(4) not null,
    plateNumber varchar(25) not null primary key,
    ownerName varchar(25) not null,
    driverName varchar(25) not null,
    seatsCount varchar(25) not null,
    carBrand varchar(10) not null,
    carType varchar(10) not null
);

create table if not exists UserDetails(
    id varchar(4) not null,
    userNumber varchar(25) not null primary key,
    fullName varchar(25) not null,
    street varchar(25) not null,
    city varchar(25) not null,
    province varchar(25) not null,
    phoneNumber varchar(25) not null,
    latitude varchar(50) not null,
    longitude varchar(50) not null
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


create table if not exists Taco(
id identity,
name varchar(50) not null,
createdAt timestamp not null
);

create table if not exists Taco_Ingredients(
taco bigint not null,
ingredient varchar(4) not null
);



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




create unique index ix_auth_username
    on authorities (username, authority);

alter table Taco_Order_Tacos
add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
add foreign key (taco) references Taco(id)