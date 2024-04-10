CREATE TABLE ticket (
    ID int not null primary key auto_increment,
    movieName varchar(255) not null,
    amount int not null,
    firstName varchar(255),
    lastName varchar(255),
    phoneNumber varchar(255),
    eMail varchar(255)
);