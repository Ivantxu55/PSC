-- crear tabla
CREATE TABLE Coche (
    id INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(50),
    anyo INT,
    color VARCHAR(50),
    kilometraje INT,
    precio INT,
    nuevo BOOLEAN
);

-- insertar algunos datos
INSERT INTO Coche (marca, anyo, color, kilometraje, precio, nuevo) VALUES
('Toyota', 2020, 'Rojo', 15000, 20000, TRUE),
('Ford', 2018, 'Azul', 30000, 15000, FALSE),
('Honda', 2021, 'Negro', 10000, 22000, TRUE),
('Chevrolet', 2019, 'Blanco', 20000, 18000, FALSE),
('Tesla', 2022, 'Gris', 5000, 45000, TRUE);
