drop sequence DIGITALBOOK_USER_SEQ;



DROP TABLE IF EXISTS digitalbook_user;

CREATE TABLE digitalbook_user ( 
   id INT NOT NULL PRIMARY KEY, 
   fullname VARCHAR(50) NOT NULL, 
   email VARCHAR(20) NOT NULL UNIQUE, 
   password VARCHAR(20) NOT NULL 
);



INSERT INTO digitalbook_user (id, fullname, email, password) VALUES (1, 'Subhajit Sinha', 'subhajit@mail.com', 'Pass@123');
INSERT INTO digitalbook_user (id, fullname, email, password) VALUES (2, 'Soumuajit Sinha', 'Soumuajit@mail.com', 'Pass@123');
INSERT INTO digitalbook_user (id, fullname, email, password) VALUES (3, 'Mahua Sinha', 'Mahua@mail.com', 'Pass@123');
INSERT INTO digitalbook_user (id, fullname, email, password) VALUES (4, 'Joydeep Sinha', 'Joydeep@mail.com', 'Pass@123');
INSERT INTO digitalbook_user (id, fullname, email, password) VALUES (5, 'Sainath Kolna', 'Sainath@mail.com', 'Pass@123');



select * from DIGITALBOOK_USER;

