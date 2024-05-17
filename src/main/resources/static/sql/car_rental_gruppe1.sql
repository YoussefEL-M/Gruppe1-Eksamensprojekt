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
 status VARCHAR(50) NOT NULL,
 ds	BOOLEAN	NOT NULL,
 licensePlate	VARCHAR(50)	NOT NULL,
 fuelType	VARCHAR(50)	NOT NULL,
 kmTraveled	DOUBLE	NOT NULL,
 fuelEfficiency	VARCHAR(60)	NOT NULL,
 price	DOUBLE	NOT NULL,
 manual	BOOLEAN	NOT NULL

);


CREATE TABLE IF NOT EXISTS user
(id	INT	PRIMARY KEY	NOT NULL	AUTO_INCREMENT,
 type	VARCHAR(100)	NOT NULL,
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
 endDate	DATE	NOT NULL,
 status BOOLEAN
);

CREATE TABLE IF NOT EXISTS report
(id	INT PRIMARY KEY	NOT NULL	AUTO_INCREMENT,
 rentalId	INT,	FOREIGN KEY(rentalId)	REFERENCES rental(id),
 title	VARCHAR(50)	NOT NULL,
 date	DATE	NOT NULL,
 comment	VARCHAR(500)	NOT NULL,
 lastUpdated DATE NOT NULL
);




INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Lars', '12345678', 'Vej 14', '2011-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Birgitte', '87654321', 'Gade 69', '1999-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Jens', '11111111', 'Vej 14', '1920-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Hans', '22222222', 'Vej 14', '1992-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Henriette', '33333333', 'Vej 14', '1973-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Herbert', '44444444', 'Vej 14', '1955-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Gitte', '55555555', 'Vej 14', '1988-11-11');



INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('1234', 'DS', 'McQueen');

INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual) VALUES ('1','1234','Red', 'McQueen', '14', '1000','30', 0, 1, 'Kachow', 'Benzin', 100, '14,8 km/l', 4350, true);

INSERT INTO rental (pickUpLocation, returnLocation, type, customer_id, startDate, endDate, car_id) VALUES ('a', 'a', '3', '1', '2024-01-01', '2024-04-01', '1');

