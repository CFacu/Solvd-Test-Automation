------------INSERTS------------

INSERT INTO space_company.space_company (name)
VALUES ('NASA');

INSERT INTO space_company.stations (name, space_company_id)
VALUES ('Johnson Space Center', 1);

INSERT INTO space_company.stations (name, space_company_id)
VALUES ('Stennis Space Center', 1);

INSERT INTO space_company.stations (name, space_company_id)
VALUES ('Kennedy Space Center', 1);

INSERT INTO space_company.ceo (first_name, last_name, age, email, space_company_id)
VALUES ('James', 'Bridenstine', 44, 'jamesb@nasa.com', 1);

INSERT INTO space_company.addresses (city, street, number, stations_id)
VALUES ('Houston', 'E NASA Pkwy', 2101, 1);

INSERT INTO space_company.addresses (city, street, number, stations_id)
VALUES ('Kiln', 'Dummy Line Rd', 39556, 2);

INSERT INTO space_company.addresses (city, street, number, stations_id)
VALUES ('Orlando', 'Kennedy Pkwy N', 32899, 3);

INSERT INTO space_company.rockets (name, space_company_id)
VALUES ('Saturn V', 1);

INSERT INTO space_company.rockets (name, space_company_id)
VALUES ('Little Joe', 1);

INSERT INTO space_company.rockets (name, space_company_id)
VALUES ('Pegasus', 1);

INSERT INTO space_company.astronauts (first_name, last_name, age, duty, stations_id)
VALUES ('Neil', 'Armstrong', 38, 'Captain', 1);

INSERT INTO space_company.astronauts (first_name, last_name, age, duty, stations_id)
VALUES ('Michael', 'Collins', 35, 'Pilot', 1);

INSERT INTO space_company.astronauts (first_name, last_name, age, duty, stations_id)
VALUES ('Edwin', 'Aldrin', 41, 'Pilot', 1);

INSERT INTO space_company.engineers (first_name, last_name, age, speciality, space_company_id)
VALUES ('John', 'Johnson', 42, 'Aerospace', 1);

INSERT INTO space_company.engineers (first_name, last_name, age, speciality, space_company_id)
VALUES ('Pete', 'Peterson', 49, 'Mechanical', 1);

INSERT INTO space_company.engineers (first_name, last_name, age, speciality, space_company_id)
VALUES ('David', 'Smith', 39, 'Computer Hardware', 1);

INSERT INTO space_company.missions (name, objective, span_in_years)
VALUES ('Apollo Program', 'Take the first human to the moon.', 11);

INSERT INTO space_company.missions (name, objective, span_in_years)
VALUES ('Mercury Program', 'First U.S. crewed program.', 4);

INSERT INTO space_company.rocket_mission_date (launch_date, rockets_id, missions_id)
VALUES (TO_DATE('06/20/1969', 'MM/DD/YYYY'), 1, 1);

INSERT INTO space_company.rocket_mission_date (launch_date, rockets_id, missions_id)
VALUES (TO_DATE('07/21/1959', 'MM/DD/YYYY'), 2, 2);

------------UPDATES------------

UPDATE space_company.engineers
SET first_name = 'Robert'
WHERE id = 1;

UPDATE space_company.engineers
SET last_name = 'Stevens'
WHERE id = 2;

UPDATE space_company.astronauts
SET age = 36
WHERE id = 2;

UPDATE space_company.rockets
SET name = 'Titan'
WHERE id = 3;

------------SELECTS WITH HAVING------------

SELECT * FROM space_company.missions m
LEFT JOIN space_company.rocket_mission_date rmd on m.id = rmd.missions_id
LEFT JOIN space_company.rockets r on r.id = rmd.rockets_id
GROUP BY m.id
HAVING m.name = 'Apollo Program';

SELECT * FROM space_company.missions m
LEFT JOIN space_company.satellite_mission_date smd on m.id = smd.missions_id
LEFT JOIN space_company.satellites s on s.id = smd.satellites_id
GROUP BY m.id
HAVING m.name = 'Mercury Program';

SELECT * FROM space_company.engineers e
LEFT JOIN space_company.space_company sc on sc.id = e.space_company_id
GROUP BY e.id
HAVING e.space_company_id = 1;

SELECT * FROM space_company.astronauts a
LEFT JOIN space_company.stations s on s.id = a.stations_id
GROUP BY a.id
HAVING a.age > 38;

SELECT * FROM space_company.satellites s
LEFT JOIN space_company.stations st on st.id = s.stations_id
GROUP BY s.id
HAVING s.weight > 10000;

SELECT * FROM space_company.rockets r
LEFT JOIN space_company.space_company sc on sc.id = r.space_company_id
GROUP BY r.id
HAVING r.weight < 30000;

SELECT AVG(r.fuel_capacity) FROM space_company.rockets r
WHERE r.space_company_id = 1;

SELECT SUM(s.weight) FROM space_company.satellites s
WHERE s.stations_id = 3;

SELECT COUNT(s.cargo_capacity) FROM space_company.satellites s
WHERE s.stations_id = 2 AND s.cargo_capacity > 2000;

------------JOIN ALL TABLES------------

SELECT * FROM space_company.space_company sc
LEFT JOIN space_company.engineers e on sc.id = e.space_company_id
LEFT JOIN space_company.ceo c on sc.id = c.space_company_id
LEFT JOIN space_company.rockets r on sc.id = r.space_company_id
LEFT JOIN space_company.rocket_mission_date rmd on r.id = rmd.rockets_id
LEFT JOIN space_company.missions m on m.id = rmd.missions_id
LEFT JOIN space_company.satellite_mission_date smd on m.id = smd.missions_id
LEFT JOIN space_company.stations s on sc.id = s.space_company_id
LEFT JOIN space_company.satellites sat on s.id = sat.stations_id
LEFT JOIN space_company.addresses ad on s.id = ad.stations_id
LEFT JOIN space_company.astronauts ast on s.id = ast.stations_id;

