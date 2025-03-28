CREATE DATABASE RegistrationForm;



CREATE TABLE users (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100),
    mobile VARCHAR(15),
    gender VARCHAR(10),
    dob VARCHAR(20),
    address TEXT
);

