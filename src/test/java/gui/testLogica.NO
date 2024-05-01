package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import domain.jdo.Coche;
import domain.jdo.Color;
import domain.jdo.Marca;

import gui.LogicaCliente;

import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

class LogicaClienteTests {

    private LogicaCliente logicaCliente;
    private Coche coche;

    int id = 1;

    @BeforeEach
    void setUp() {
        logicaCliente = new LogicaCliente("localhost", "8080");
        coche = new Coche();
        coche.setId(id);
        coche.setPrecio(100);
        coche.setColor(Color.Blue);
        logicaCliente.agregarCoche(coche);  // debería ser un stub
    }

    @Test
    void testModificarCoche() {
        Map<String, Object> cambios = new HashMap<>();
        cambios.put("precio", 200);
        cambios.put("color", Color.Red);

        logicaCliente.modificarCoche(coche, cambios);   // debería ser un stub

        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
		Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT * FROM coche where id = "+id);     // debería ser un stub
		List<Object[]> results = q.executeList();
		for (Object[] row : results) {
			Marca marca = Marca.valueOf((String) row[1]);
			Color color = Color.valueOf((String) row[3]);
			Coche cocheModificado = new Coche(
				marca,          	// marca
				(Integer)row[2],    // año
				color,          	// color
				(Integer)row[4],	// km
				(Integer)row[5],	// precio
				(Boolean)row[6]		// estado
			);
			coche.setId((Integer)row[0]);
            assertEquals(200, cocheModificado.getPrecio());
            assertEquals(Color.Red, cocheModificado.getColor());
        }
    }
}
