package domain.jdo;

//import es.deusto.spq.client.ExampleClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Clase que representa los tests de la clase Coche
 */
class CocheTests extends JerseyTest{


    private Coche coche;

    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Mock
    private Coche cocheMock = mock(Coche.class);

    @Mock
    private Usuario usuarioMock = mock(Usuario.class);

//    private ExampleClient exampleClient;

    @BeforeEach
    public void setUp() {
        coche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);

        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
        client = ClientBuilder.newClient();
        client = mock(Client.class);
        webTarget = mock(WebTarget.class, RETURNS_DEEP_STUBS);

        // Configure the Client mock to return the WebTarget mock
        when(client.target(anyString())).thenReturn(webTarget);



//        MockitoAnnotations.openMocks(this);
//
//        // prepare static mock of ClientBuilder
//        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
//            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
//            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);
//
//            exampleClient = new ExampleClient("localhost", "8080");
//        }
    }

    /**
     * Test para comprobar el constructor y los getters de la clase Coche
     */
    @Test
    public void testConstructorAndGetter() {
        when(cocheMock.getMarca()).thenReturn(Marca.Ford);
        when(cocheMock.getAnyo()).thenReturn(2020);
        when(cocheMock.getColor()).thenReturn(Color.Black);
        when(cocheMock.getKilometraje()).thenReturn(5000);
        when(cocheMock.getPrecio()).thenReturn(20000);
        when(cocheMock.isNuevo()).thenReturn(false);

        // Verify that the mock object behaves as expected
        assertEquals(Marca.Ford, cocheMock.getMarca());
        assertEquals(2020, cocheMock.getAnyo());
        assertEquals(Color.Black, cocheMock.getColor());
        assertEquals(5000, cocheMock.getKilometraje());
        assertEquals(20000, cocheMock.getPrecio());
        assertFalse(cocheMock.isNuevo());
    }

    /**
     * Test para comprobar los setters de la clase Coche
     */
    @Test
    public void testSetter() {
        coche.setMarca(Marca.Toyota);
        assertEquals(Marca.Toyota, coche.getMarca());

        coche.setAnyo(2021);
        assertEquals(2021, coche.getAnyo());

        coche.setColor(Color.White);
        assertEquals(Color.White, coche.getColor());

        coche.setKilometraje(6000);
        assertEquals(6000, coche.getKilometraje());

        coche.setPrecio(21000);
        assertEquals(21000, coche.getPrecio());

        coche.setNuevo(true);
        assertTrue(coche.isNuevo());

        coche.setId(1);
        assertEquals(1, coche.getId());
    }

    /**
     * Test para comprobar el método equals de la clase Coche
     */
    @Test
    public void testEquals() {
        Coche sameCoche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        Coche differentCoche = new Coche(Marca.Toyota, 2021, Color.White, 6000, 21000, true);

        assertEquals(coche, sameCoche);
        assertNotEquals(coche, differentCoche);
    }

    /**
     * Test para comprobar el método hashCode de la clase Coche
     */
    @Test
    public void testHashCode() {
        Coche sameCoche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        assertEquals(coche.hashCode(), sameCoche.hashCode());
    }

    /**
     * Test para comprobar el método toString de la clase Coche
     */
    @Test
    public void testToString() {
        String expected = "Coche{id=0, marca=Ford, anyo=2020, color=Black, kilometraje=5000, precio=20000, nuevo=false}";
        assertEquals(expected, coche.toString());
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(CocheResource.class);
    }

    @Test
    public void testGetCoche() {
        // Configura el mock para que devuelva un Coche específico cuando se llame a getCoche(1)
        when(webTarget.path("coche/1").request().get(Coche.class)).thenReturn(coche);

        // Realiza una solicitud GET a /coche/1 y verifica que la respuesta es el Coche que esperabas
        Coche response = client.target("http://localhost:8080/rest/resource").path("coche/1").request().get(Coche.class);
        assertEquals(coche, response);
    }

}
