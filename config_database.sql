-- crear tabla
CREATE TABLE COCHE (
    id INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(50),
    anyo INT,
    color VARCHAR(50),
    kilometraje INT,
    precio INT,
    nuevo BOOLEAN
);

-- insertar algunos datos
INSERT INTO COCHE (marca, anyo, color, kilometraje, precio, nuevo) VALUES
('Toyota', 2020, 'Black', 10, 35000, TRUE),
('Ford', 2018, 'Black', 12, 50000, TRUE),
('Chevrolet', 2021, 'Black', 0, 50000, TRUE),
('Chevrolet', 2008, 'Black', 180000, 1200, FALSE),
('Ford', 1998, 'Red', 200000, 800, FALSE);
