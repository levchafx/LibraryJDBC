insert into roles (role) values ('ADMIN');
insert into roles (role) values ('USER');
insert into users(name,surname,email,age) values ('sergei','levchenko','levchafx@rambler.ru',35);
insert into authenticate(login,password,profile_enable) values ('admin','1234','true');
insert into users_roles(user_id,role_id) values(1,1);