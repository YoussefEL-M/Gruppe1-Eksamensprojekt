/* Clara */

CREATE DATABASE IF NOT EXISTS car_rental_gruppe1;

USE car_rental_gruppe1;


CREATE TABLE IF NOT EXISTS customer
(id 	INT	PRIMARY KEY	NOT NULL	AUTO_INCREMENT,
 name	VARCHAR(100)	NOT NULL,
    telnr	VARCHAR(50)	NOT NULL,
    email	VARCHAR(50),
    address	VARCHAR(150)	NOT NULL,
    birthDate	DATE	NOT NULL
    );


CREATE TABLE IF NOT EXISTS carIdentification
(serialNumber VARCHAR(50) PRIMARY KEY	NOT NULL,
    brand	VARCHAR(50)	NOT NULL,
    model	VARCHAR(50)	NOT NULL
    );


CREATE TABLE IF NOT EXISTS car
(id	INT	PRIMARY KEY	NOT NULL,
 serialNumber	VARCHAR(50),	FOREIGN KEY(serialNumber)	REFERENCES caridentification(serialNumber),
    color	VARCHAR(50)	NOT NULL,
    trimLevel	VARCHAR(100)	NOT NULL,
    steelPrice	DOUBLE,
    registrationTax	DOUBLE	NOT NULL,
    emission	DOUBLE,
    damaged	BOOLEAN	NOT NULL,
    ds	BOOLEAN	NOT NULL,
    licensePlate	VARCHAR(50)	NOT NULL
    );


CREATE TABLE IF NOT EXISTS user
(id	INT	PRIMARY KEY	NOT NULL	AUTO_INCREMENT,
 name	VARCHAR(150)	NOT NULL,
    username	VARCHAR(50)	NOT NULL,
    password	VARCHAR(50)	NOT NULL,
    email	VARCHAR(50)	NOT NULL
    );

CREATE TABLE IF NOT EXISTS rental
(id	INT	PRIMARY KEY	NOT NULL	AUTO_INCREMENT,
 customer_id	INT, FOREIGN KEY(customer_id)	REFERENCES customer(id),
    car_id	INT, FOREIGN KEY(car_id)	REFERENCES car(id),
    user_id	INT, FOREIGN KEY(user_id)	REFERENCES	user(id),
    pickUpLocation	VARCHAR(150)	NOT NULL,
    returnLocation	VARCHAR(150)	NOT NULL,
    type	VARCHAR(100)	NOT NULL,
    startDate	DATE	NOT NULL,
    endDate	DATE	NOT NULL
    );

CREATE TABLE IF NOT EXISTS report
(id	INT PRIMARY KEY	NOT NULL	AUTO_INCREMENT,
 rentalId	INT,	FOREIGN KEY(rentalId)	REFERENCES rental(id),
    title	VARCHAR(50)	NOT NULL,
    date	DATE	NOT NULL
    );