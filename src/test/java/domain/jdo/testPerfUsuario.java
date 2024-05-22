package domain.jdo;

import junit.framework.TestCase;
import com.github.noconnor.junitperf.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.rules.Timeout;

import com.github.noconnor.*;

import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class testPerfUsuario extends TestCase {

    private static final int NUM_ITERACIONES = 1000;

    public static Test suite() {
        TestSuite suite = new TestSuite("Test de Rendimiento de Usuario");

        suite.addTest((Test) new Timeout(100));

        return suite;
    }

    public testPerfUsuario(String name) {
        super(name);
    }

    public static void main(String[] args) {
        TestRunner.run(suite());
    }

    public void testPerformance() {
        long startTime = System.currentTimeMillis();
        
        ArrayList<Coche> coches = new ArrayList<>();
        coches.add(new Coche(Marca.Tesla, 2020, Color.Black, 0, 80000, true));

        for (int i = 0; i < NUM_ITERACIONES; i++) {
        	Usuario usuario = new Usuario("Juan", new Date(), "juan@example.com", "password123", coches);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Tiempo total para " + NUM_ITERACIONES + " operaciones: " + totalTime + " milisegundos");
    }
}
