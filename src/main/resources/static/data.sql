-- DROP TABLE IF EXISTS person;
--
-- CREATE TABLE person (
--   person_id INT AUTO_INCREMENT PRIMARY KEY,
--   firstName VARCHAR(250) NOT NULL,
--   secondName VARCHAR(250) NOT NULL,
--   thirdName VARCHAR(250) NOT NULL,
--   department VARCHAR(250) NOT NULL
-- );
--
--
--
--
-- DROP TABLE IF EXISTS factory;
-- CREATE TABLE factory (
--   factoryName VARCHAR(250) PRIMARY KEY,
--   townName VARCHAR(250) NOT NULL
-- );
--
--
-- DROP TABLE IF EXISTS businessTrip;
-- CREATE TABLE businessTrip (
-- 	businessTrip_id INT AUTO_INCREMENT PRIMARY KEY,
--     dateOfTrip DATE,
--     factory_id VARCHAR(250),
--     FOREIGN KEY (factory_id) REFERENCES factory(factoryName)
-- );

INSERT INTO person (first_name, second_name, third_Name, department) VALUES
('Sasha', 'Kokshin','Kek','otdel'),
('Ali', 'Bodiazhin','Keking','CoolPeople'),
('Kuki', 'Kurev','Keking','CoolPeople'),
('Gugi', 'Durov','Keking','CoolPeople'),
('Pinky', 'Ebobo','Keking','otdel');

INSERT INTO factory (factory_Name, town_Name) VALUES
('NNK', 'Novokuibishevsk'),
('ANPZ', 'Achinsk');
