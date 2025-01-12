CREATE DATABASE IF NOT EXISTS LibraryBookManagementSystem;
use LibraryBookManagementSystem;
CREATE TABLE User (
    firstName VARCHAR(20),
    lastName VARCHAR(20),
    email VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE UserRole (
    email VARCHAR(255),
    role VARCHAR(255),
    primary key (email, role),
    CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES User(email)
);
CREATE TABLE Book (
    Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ISBN VARCHAR(255) NOT NULL,
    Title VARCHAR(255) NOT NULL,
    Author VARCHAR(255) NOT NULL,
    Summary VARCHAR(255) NOT NULL,
    YearPublished int NOT NULL,
    ImageUrl VARCHAR(255) NOT NULL
);
CREATE TABLE PhysicalBook (
    Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Location VARCHAR(255) NOT NULL,
    Status VARCHAR(255) NOT NULL,
    BookId int NOT NULL,
    CONSTRAINT fk_book FOREIGN KEY (BookId) REFERENCES Book(Id)
);
CREATE TABLE Reservation (
    Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    UserEmail VARCHAR(255) NOT NULL,
    PhysicalBookId int NOT NULL,
    DateReserved DATE NOT NULL,
    Status VARCHAR(255) NOT NULL,
    CONSTRAINT fk_userEmail FOREIGN KEY (UserEmail) REFERENCES User(email),
    CONSTRAINT fk_physicalBookId FOREIGN KEY (PhysicalBookId) REFERENCES PhysicalBook(Id)
);
INSERT INTO User VALUES ("Shirley", "Chow", "admin@gmail.com","password");
INSERT INTO UserRole VALUES ("admin@gmail.com", "admin");
INSERT INTO User VALUES ("John", "Doe", "public@gmail.com","password");
INSERT INTO UserRole VALUES ("public@gmail.com", "public");
INSERT INTO User VALUES ("Jane", "Doe", "staff@gmail.com","password");
INSERT INTO UserRole VALUES ("staff@gmail.com", "staff");
INSERT INTO Book (ISBN, Title, Author, Summary, YearPublished, ImageUrl)VALUES("0195153448", "Classic Mythology", "Mark PO Morford", "This is a book about ancient Greek heroes", 2019, "http://images.amazon.com/images/P/0195153448.01.MZZZZZZZ.jpg");
INSERT INTO Book (ISBN, Title, Author, Summary, YearPublished, ImageUrl)VALUES("0002005018", "Clara Callan", "Richard Bruce Wright", "This is a book about a famous scientist", 2011, "http://images.amazon.com/images/P/0002005018.01.MZZZZZZZ.jpg");
INSERT INTO Book (ISBN, Title, Author, Summary, YearPublished, ImageUrl)VALUES("0393045218", "The Mummies of Urumchi", "E. J. W. Barber", "This is a book about historical burial customs", 1999, "http://images.amazon.com/images/P/0393045218.01.MZZZZZZZ.jpg");
INSERT INTO PhysicalBook (Location, Status, BookId) VALUES("ABC Branch", "Available", 1);
INSERT INTO PhysicalBook (Location, Status, BookId) VALUES("ABC Branch", "Borrowed", 1);
INSERT INTO PhysicalBook (Location, Status, BookId) VALUES("ABC Branch", "Available", 2);
INSERT INTO PhysicalBook (Location, Status, BookId) VALUES("DEF Branch", "Available", 3);