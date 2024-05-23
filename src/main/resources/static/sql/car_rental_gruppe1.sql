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
    manual	BOOLEAN	NOT NULL,
    lastUpdated DATE

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
    type	int	NOT NULL,
    startDate	DATE	NOT NULL,
    endDate	DATE	NOT NULL,
    status VARCHAR(10)
    );

CREATE TABLE IF NOT EXISTS report
(id	INT PRIMARY KEY	NOT NULL	AUTO_INCREMENT,
 rentalId	INT,	FOREIGN KEY(rentalId)	REFERENCES rental(id),
    user_id INT,    FOREIGN KEY(user_id)    REFERENCES user(id),
    title	VARCHAR(50)	NOT NULL,
    date	DATE	NOT NULL,
    comment	VARCHAR(500),
    treatment VARCHAR(200)
    );



CREATE TABLE IF NOT EXISTS damages (
                                       id	INT PRIMARY KEY	NOT NULL	AUTO_INCREMENT,
                                       report_id INT, FOREIGN KEY(report_id)	REFERENCES report(id),
    damage VARCHAR(50),
    price DOUBLE
    );




INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Lars', '12345678', 'Vej 14', '2011-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Birgitte', '87654321', 'Gade 69', '1999-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Jens', '11111111', 'Vej 14', '1920-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Hans', '22222222', 'Vej 14', '1992-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Henriette', '33333333', 'Vej 14', '1973-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Herbert', '44444444', 'Vej 14', '1955-11-11');
INSERT INTO customer (name, telnr, address, birthdate) VALUES ('Gitte', '55555555', 'Vej 14', '1988-11-11');



INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN001', 'Toyota', 'Camry');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN002', 'BMW', 'X5');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN003', 'Honda', 'Accord');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN004', 'Ford', 'Mustang');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN005', 'Tesla', 'Model S');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN006', 'Audi', 'Q7');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN007', 'Lexus', 'RX');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN008', 'Porsche', 'Taycan');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN009', 'Nissan', 'Altima');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN010', 'Mercedes-Benz', 'C-Class');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN011', 'Jaguar', 'I-Pace');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN012', 'Volvo', 'XC60');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN013', 'Chevrolet', 'Camaro');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN014', 'Mercedes-Benz', 'E-Class');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN015', 'Hyundai', 'Sonata');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN016', 'Subaru', 'Outback');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN017', 'Audi', 'e-tron');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN018', 'Toyota', 'Highlander');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN019', 'Mazda', 'CX-5');
INSERT INTO carIdentification (serialNumber, brand, model) VALUES ('SN020', 'Tesla', 'Model X');


INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('1', 'SN001', 'Rød', 'Sport', '800', '1500', '120', 'AVAILABLE', '0', 'ABC123', 'PETROL', '15000', '15', '30000', '0', '2024-03-15');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('2', 'SN002', 'Blå', 'Luxury', '900', '1800', '110', 'AVAILABLE', '0', 'DEF456', 'DIESEL', '20000', '20', '40000', '1', '2024-03-10');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('3', 'SN003', 'Sort', 'Standard', '850', '1300', '130', 'AVAILABLE', '0', 'GHI789', 'HYBRID', '10000', '25', '35000', '0', '2024-03-12');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('4', 'SN004', 'Hvid', 'Sport', '870', '1600', '115', 'AVAILABLE', '1', 'JKL012', 'PETROL', '25000', '18', '32000', '1', '2024-03-20');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('5', 'SN005', 'Grøn', 'Luxury', '920', '2000', '100', 'AVAILABLE', '0', 'MNO345', 'ELECTRIC', '5000', '30', '45000', '0', '2024-03-18');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('6', 'SN006', 'Sølv', 'Standard', '890', '1400', '125', 'AVAILABLE', '0', 'PQR678', 'DIESEL', '18000', '22', '37000', '1', '2024-03-11');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('7', 'SN007', 'Rød', 'Sport', '810', '1550', '105', 'AVAILABLE', '0', 'STU901', 'HYBRID', '13000', '28', '34000', '0', '2024-03-25');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('8', 'SN008', 'Blå', 'Luxury', '950', '2100', '95', 'AVAILABLE', '0', 'VWX234', 'ELECTRIC', '8000', '32', '50000', '0', '2024-03-21');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('9', 'SN009', 'Sort', 'Standard', '880', '1450', '135', 'AVAILABLE', '0', 'YZA567', 'PETROL', '22000', '16', '31000', '1', '2024-03-14');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('10', 'SN010', 'Hvid', 'Sport', '830', '1700', '140', 'AVAILABLE', '0', 'BCD890', 'DIESEL', '19000', '19', '36000', '1', '2024-03-09');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('11', 'SN011', 'Grøn', 'Luxury', '960', '2200', '90', 'AVAILABLE', '0', 'EFG123', 'ELECTRIC', '6000', '35', '55000', '0', '2024-03-23');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('12', 'SN012', 'Sølv', 'Standard', '890', '1500', '125', 'AVAILABLE', '0', 'HIJ456', 'HYBRID', '14000', '26', '38000', '0', '2024-03-17');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('13', 'SN013', 'Rød', 'Sport', '850', '1600', '115', 'AVAILABLE', '1', 'KLM789', 'PETROL', '17000', '20', '33000', '1', '2024-03-13');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('14', 'SN014', 'Blå', 'Luxury', '940', '1900', '100', 'AVAILABLE', '1', 'NOP012', 'DIESEL', '12000', '23', '42000', '1', '2024-03-19');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('15', 'SN015', 'Sort', 'Standard', '820', '1400', '130', 'AVAILABLE', '0', 'QRS345', 'PETROL', '24000', '17', '29000', '0', '2024-03-16');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('16', 'SN016', 'Hvid', 'Sport', '870', '1650', '110', 'AVAILABLE', '0', 'TUV678', 'HYBRID', '11000', '24', '37000', '1', '2024-03-22');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('17', 'SN017', 'Grøn', 'Luxury', '930', '2100', '95', 'AVAILABLE', '1', 'WXY901', 'ELECTRIC', '7000', '33', '48000', '0', '2024-03-24');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('18', 'SN018', 'Sølv', 'Standard', '860', '1350', '120', 'AVAILABLE', '0', 'ABC234', 'DIESEL', '15000', '21', '31000', '1', '2024-03-26');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('19', 'SN019', 'Rød', 'Sport', '850', '1550', '105', 'AVAILABLE', '1', 'DEF567', 'PETROL', '26000', '19', '32000', '0', '2024-03-27');
INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual, lastUpdated) VALUES ('20', 'SN020', 'Blå', 'Luxury', '940', '2200', '100', 'AVAILABLE', '0', 'GHI890', 'ELECTRIC', '9000', '34', '53000', '0', '2024-03-28');





INSERT INTO USER (type, name, username, password, email) VALUES ('Dataregistrering', 'lars', 'lars', 'lars', 'lars@lars.dk');
INSERT INTO USER (type, name, username, password, email) VALUES ('SkadeOgUdbedring', 's', 's', 's', 's@s.dk');



select * from car;
select * from rental;
select * from report;
SELECT * FROM damages;



