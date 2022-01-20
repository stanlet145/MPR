CREATE TABLE car
(
    car_id    BIGINT NOT NULL,
    car_brand VARCHAR(255),
    car_model VARCHAR(255),
    CONSTRAINT pk_cars PRIMARY KEY (car_id)
);
CREATE SEQUENCE car_sequence
    start with 1
    increment by 1;
INSERT INTO car (car_id, car_brand, car_model)
VALUES (next value for car_sequence, 'VOLKSWAGEN', '7');
INSERT INTO car (car_id, car_brand, car_model)
VALUES (next value for car_sequence, 'BMW', 'iX');
INSERT INTO car (car_id, car_brand, car_model)
VALUES (next value for car_sequence, 'TOYOTA', 'Corolla');