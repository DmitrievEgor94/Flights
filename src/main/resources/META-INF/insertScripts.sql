INSERT INTO passengers(first_name,last_name) VALUES('Egor', 'Dmitriev');
INSERT INTO passengers(first_name,last_name) VALUES('Kirill', 'Denisov');

INSERT INTO planes(plane_number) VALUES('Aerobus 1');
INSERT INTO planes(plane_number) VALUES('Aerobus 2');

INSERT INTO flights(flight_number) VALUES('x123xcg');
INSERT INTO flights(flight_number) VALUES('a123bxc');

INSERT INTO flights_planes(flight_id, plane_id) values (1,1);
INSERT INTO flights_planes(flight_id, plane_id) values (1,2);
INSERT INTO flights_planes(flight_id, plane_id) values (2,1);

INSERT INTO TICKETS(cost, date, seat, passenger_id, plane_id, flight_id) VALUES(100, TO_DATE('12.10.2017 22:12', 'DD.MM.YYYY HH:MI'), 10, 1, 1, 1);
INSERT INTO TICKETS(cost, date, seat, passenger_id, plane_id, flight_id) VALUES(100, TO_DATE('12.10.2017 22:12', 'DD.MM.YYYY HH:MI'), 11, 1, 2, 2);


