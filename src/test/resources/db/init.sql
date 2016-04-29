-- test init data

-- insert users test data
insert into user_ (id,login,password) values (1,'admin','admin');
insert into user_ (id,login,password) values (2,'user01','user01');

-- insert members test data
INSERT INTO member(country,name_street,num_street,pc_town,town,amount,birthday,email,lastname,name,payment_date)  VALUES('France','rue des coquelicots','2','35000','Rennes',3.24,timestamp '1992-10-10','jacquet@gmail.com','jacquet','eddy',now()) ;
INSERT INTO member(country,name_street,num_street,pc_town,town,amount,birthday,email,lastname,name,payment_date)  VALUES('UK','Street of England','2050','40080','SunTown',5.99,timestamp '1990-11-12','tchouague@gmail.com','wall','jack',now()) ;

-- insert author test data
INSERT INTO author(id, first_name, last_name) VALUES (1, 'Auteur 1', 'Auteur 1');
INSERT INTO author(id, first_name, last_name) VALUES (2, 'Auteur 2', 'Auteur 2');

-- insert media test data
INSERT INTO media(id, title, type, author_id, media_loan_id) VALUES (1, 'Media 1', 'DVD', 1, null);
INSERT INTO media(id, title, type, author_id, media_loan_id) VALUES (2, 'Media 2', 'CD', 1, null);
