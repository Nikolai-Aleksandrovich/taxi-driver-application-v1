delete from Poi;
delete from Poi_Dest;
delete from Dest;
delete from User_Order_Dest;
delete from User_Order;
delete from Car;

insert into users (username, password) values ('user1', 'password1');
insert into users (username, password) values ('user2', 'password2');
insert into Poi (id,idCode,name,type,userType,checkInCount,userCount,latitude,longititude) values ('1','4d275201068e8cfabf9eb94c','L.E.S Kutz','Salon / Barbershop','Shops','99','37','40.717766','-73.98526')
insert into Poi (id,idCode,name,type,userType,checkInCount,userCount,latitude,longititude) values ('5769452c498ed5c69e5bef83','Boxed.com,Office,Professional','94','25','40.720914','-74.001493')
insert into authorities (username, authority)
    values ('user1', 'ROLE_USER');
insert into authorities (username, authority)
    values ('user2', 'ROLE_USER');

commit;