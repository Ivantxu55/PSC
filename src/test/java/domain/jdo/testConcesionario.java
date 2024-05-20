package domain.jdo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Clase que representa los tests de la clase Usuario
 */

class ConcesionarioTest {

    private Concesionario concesionario;


    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Mock
    private Concesionario concesionarioMock = mock(Concesionario.class);

    private Coche coche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);

    private ArrayList<Coche> coches = new ArrayList<Coche>(Arrays.asList(coche));

    @BeforeEach
    public void setUp() {
        concesionario = new Concesionario("concesionario", "localidad", coches);

        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test para comprobar el constructor y los getters de la clase Concesionario
     */
    @Test
    public void testConstructorAndGetter() {
        when(concesionarioMock.getNombre()).thenReturn("concesionario");
        when(concesionarioMock.getLocalidad()).thenReturn("localidad");
        when(concesionarioMock.getCoches()).thenReturn(coches);

        assertEquals("concesionario", concesionarioMock.getNombre());
        assertEquals("localidad", concesionarioMock.getLocalidad());
        assertEquals(coches, concesionarioMock.getCoches());
    }

    /**
     * Test para comprobar los setter de la Clase Concesionario
     */
    @Test
    public void testSetter() {
        concesionario.setNombre("concesionario2");
        concesionario.setLocalidad("localidad2");
        assertEquals("concesionario2", concesionario.getNombre());
        assertEquals("localidad2", concesionario.getLocalidad());
    }

    /**
     * Test para comprobar el método agregarCoche de la clase Concesionario
     */
    @Test
    public void testAgregarCoche() {
        Coche coche2 = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        concesionario.agregarCoche(coche2);
        assertEquals(2, concesionario.getCoches().size());
    }

    /**
     * Test para comprobar el método eliminarCoche de la clase Concesionario
     */
    @Test
    public void testEliminarCoche() {
        Coche coche2 = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        concesionario.agregarCoche(coche2);
        concesionario.eliminarCoche(coche2);
        assertEquals(1, concesionario.getCoches().size());
    }

    /**
     * Test para comprobar el método encontarCochePorId de la clase Concesionario
     */
    @Test
    public void testEncontrarCochePorId() {
        Coche coche2 = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        concesionario.agregarCoche(coche2);
        assertEquals(coche2, concesionario.encontrarCochePorId(coche2.getId()));
        assertNull(concesionario.encontrarCochePorId(2));
    }

    /**
     * Test para comprobar el método toString de la clase Concesionario
     */
    @Test
    public void testToString() {
        Concesionario c1 = new Concesionario("concesionario", "localidad", coches);
        assertEquals(c1.toString(), concesionario.toString());
    }


}
