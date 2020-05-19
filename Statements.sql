------------INSERTS------------

INSERT INTO Space_Company (name)
VALUES ('NASA');

INSERT INTO Stations (name, space_company_id)
VALUES ('Johnson Space Center', 1);

INSERT INTO Stations (name, space_company_id)
VALUES ('Stennis Space Center', 1);

INSERT INTO Stations (name, space_company_id)
VALUES ('Kennedy Space Center', 1);

INSERT INTO CEO (first_name, last_name, age, email, space_company_id)
VALUES ('James', 'Bridenstine', 44, 'jamesb@nasa.com', 1);

INSERT INTO Addresses (city, street, number, stations_id)
VALUES ('Houston', 'E NASA Pkwy', 2101, 1);

INSERT INTO Addresses (city, street, number, stations_id)
VALUES ('Kiln', 'Dummy Line Rd', 39556, 2);

INSERT INTO Addresses (city, street, number, stations_id)
VALUES ('Orlando', 'Kennedy Pkwy N', 32899, 3);

INSERT INTO Rockets (name, space_company_id)
VALUES ('Saturn V', 1);

INSERT INTO Rockets (name, space_company_id)
VALUES ('Little Joe', 1);

INSERT INTO Rockets (name, space_company_id)
VALUES ('Pegasus', 1);

INSERT INTO Astronauts (first_name, last_name, age, duty, stations_id)
VALUES ('Neil', 'Armstrong', 38, 'Captain', 1);

INSERT INTO Astronauts (first_name, last_name, age, duty, stations_id)
VALUES ('Michael', 'Collins', 35, 'Pilot', 1);

INSERT INTO Astronauts (first_name, last_name, age, duty, stations_id)
VALUES ('Edwin', 'Aldrin', 41, 'Pilot', 1);

INSERT INTO Engineers (first_name, last_name, age, speciality, space_company_id)
VALUES ('John', 'Johnson', 42, 'Aerospace', 1);

INSERT INTO Engineers (first_name, last_name, age, speciality, space_company_id)
VALUES ('Pete', 'Peterson', 49, 'Mechanical', 1);

INSERT INTO Engineers (first_name, last_name, age, speciality, space_company_id)
VALUES ('David', 'Smith', 39, 'Computer Hardware', 1);

INSERT INTO Missions (name, objective, span_in_years)
VALUES ('Apollo Program', 'Take the first human to the moon.', 11);

INSERT INTO Missions (name, objective, span_in_years)
VALUES ('Mercury Program', 'First U.S. crewed program.', 4);

INSERT INTO Rocket_Mission_Date (launch_date, rockets_id, missions_id)
VALUES (TO_DATE('06/20/1969', 'MM/DD/YYYY'), 1, 1);

INSERT INTO Rocket_Mission_Date (launch_date, rockets_id, missions_id)
VALUES (TO_DATE('07/21/1959', 'MM/DD/YYYY'), 2, 2);

------------UPDATES------------

UPDATE Engineers
SET first_name = 'Robert'
WHERE id = 1;

UPDATE Engineers
SET last_name = 'Stevens'
WHERE id = 2;

UPDATE Astronauts
SET age = 36
WHERE id = 2;

UPDATE Rockets
SET name = 'Titan'
WHERE id = 3;

------------SELECTS WITH HAVING------------

SELECT * FROM Missions m
LEFT JOIN Rocket_Mission_Date rmd on m.id = rmd.missions_id
LEFT JOIN Rockets r on r.id = rmd.rockets_id
GROUP BY m.id
HAVING m.name = 'Apollo Program';

SELECT * FROM Missions m
LEFT JOIN Satellite_Mission_Date smd on m.id = smd.missions_id
LEFT JOIN Satellites s on s.id = smd.satellites_id
GROUP BY m.id
HAVING m.name = 'Mercury Program';

SELECT * FROM Engineers e
LEFT JOIN Space_Company sc on sc.id = e.space_company_id
GROUP BY e.id
HAVING e.age > 40;

SELECT * FROM Astronauts a
LEFT JOIN Stations s on s.id = a.stations_id
GROUP BY a.id
HAVING a.age > 38;

SELECT * FROM Satellites s
LEFT JOIN Stations st on st.id = s.stations_id
GROUP BY s.id
HAVING s.weight > 1000;

------------JOIN ALL TABLES------------

SELECT * FROM Space_Company sc
LEFT JOIN Engineers e on sc.id = e.space_company_id
LEFT JOIN CEO c on sc.id = c.space_company_id
LEFT JOIN Rockets r on sc.id = r.space_company_id
LEFT JOIN Rocket_Mission_Date rmd on r.id = rmd.rockets_id
LEFT JOIN Missions m on m.id = rmd.missions_id
LEFT JOIN Satellite_Mission_Date smd on m.id = smd.missions_id
LEFT JOIN Stations s on sc.id = s.space_company_id
LEFT JOIN Satellites sat on s.id = sat.stations_id
LEFT JOIN Addresses ad on s.id = ad.stations_id
LEFT JOIN Astronauts ast on s.id = ast.stations_id;

