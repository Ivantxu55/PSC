package domain.jdo;

import junit.framework.TestCase;
import com.github.noconnor.junitperf.*;

import org.junit.rules.Timeout;

import com.github.noconnor.*;

import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class testPerfCoche extends TestCase {
	
    private static final int NUM_ITERACIONES = 1000; // Número de iteraciones para las pruebas de rendimiento

    public static Test suite() {
        TestSuite suite = new TestSuite("Test de Rendimiento de Coche");

        // Agregar la prueba de rendimiento
        suite.addTest((Test) new Timeout(100)); // Establecer un límite de tiempo de 100 milisegundos

        return suite;
    }

    public testPerfCoche(String name) {
        super(name);
    }

    public static void main(String[] args) {
        // Ejecutar la suite de pruebas
        TestRunner.run(suite());
    }

    public void testPerformance() {
        long startTime = System.currentTimeMillis();

        // Realizar operaciones de rendimiento (creación de instancias de Coche)
        for (int i = 0; i < NUM_ITERACIONES; i++) {
            Coche coche = new Coche(/* Parámetros del constructor */);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Tiempo total para " + NUM_ITERACIONES + " operaciones: " + totalTime + " milisegundos");
    }

}
