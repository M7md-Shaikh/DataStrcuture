SELECT * FROM Manager WHERE Manager_ID =33 ;
SELECT * FROM Manager;
SELECT * FROM Driver;

DROP DATABASE IF EXISTS PH3;

CREATE DATABASE PH3;
USE PH3;

DROP TABLE IF EXISTS Manager;

CREATE TABLE Manager (
    Manager_ID INT PRIMARY KEY,
	Name VARCHAR(32) NOT NULL,
    Phone VARCHAR(32) NOT NULL,
    Address VARCHAR(32) NOT NULL
);
DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (
    SSN INT PRIMARY KEY,
    Name VARCHAR(32) NOT NULL,
    Phone VARCHAR(32) NOT NULL,
    Address VARCHAR(32) NOT NULL,
    Manager_ID INT,
    FOREIGN KEY (Manager_ID) REFERENCES Manager(Manager_ID)
);
DROP TABLE IF EXISTS Driver;

CREATE TABLE Driver (
     Driver_ID INT PRIMARY KEY,
      Manager_ID INT,
	Name VARCHAR(32) NOT NULL,
    Phone VARCHAR(32) NOT NULL,
    Address VARCHAR(32) NOT NULL,
    FOREIGN KEY (Manager_ID) REFERENCES Manager(Manager_ID)
);
  
DROP TABLE IF EXISTS Car;

CREATE TABLE Car (
    Reg_Number INT PRIMARY KEY,
    Car_type VARCHAR(32) NOT NULL,
    Car_color VARCHAR(32) NOT NULL
);
DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
    Product_Name VARCHAR(32) PRIMARY KEY,
    Price DECIMAL(10, 2) NOT NULL CHECK (Price >= 0)
);
DROP TABLE IF EXISTS Orders;
CREATE TABLE Orders (
    Order_ID INT PRIMARY KEY,
    Customer_ID INT,
    Driver_ID INT,
    Product_Name VARCHAR(32),
    Quantity INT NOT NULL CHECK (Quantity > 0),
    Order_date DATE NOT NULL,
    FOREIGN KEY (Customer_ID) REFERENCES Customer(SSN),
    FOREIGN KEY (Driver_ID) REFERENCES Driver(Driver_ID),
    FOREIGN KEY (Product_Name) REFERENCES Product(Product_Name)
);

ALTER TABLE Orders DROP FOREIGN KEY orders_ibfk_2;

-- Insert values into the Manager table
INSERT INTO Manager (Manager_ID, Name, Phone, Address) VALUES
(1, 'John Doe', '123-456-7890', '123 Elm Street'),
(2, 'Jane Smith', '987-654-3210', '456 Oak Avenue');


-- Insert values into the Driver table
INSERT INTO Driver (Driver_ID, Manager_ID, Name, Phone, Address) VALUES
(1, 1, 'Alice Johnson', '555-123-4567', '789 Pine Road'),
(2, 2, 'Bob Brown', '555-765-4321', '101 Maple Drive');

-- Verify the data insertion
#SELECT * FROM Manager;
#SELECT * FROM Driver;
#SELECT * FROM Manager WHERE Manager_ID =5 ;
