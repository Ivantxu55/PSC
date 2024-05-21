package domain.jdo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Clase que representa los tests de la clase Usuario
 */

class UsuarioTest {

    private Usuario usuario;


    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Mock
    private Usuario usuarioMock = mock(Usuario.class);

    @Mock
    private Coche cocheMock = mock(Coche.class);

    private SimpleDateFormat sdf;

    private Date date;


    @BeforeEach
    public void setUp() throws ParseException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = "01/01/2020";
        date = sdf.parse(fecha);
        usuario = new Usuario("usuario",date, "usuario@gmail.com", "1234", new ArrayList<Coche>());

        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test para comprobar el constructor y los getters de la clase Usuario
     */
    @Test
    public void testConstructorAndGetter() {
        when(usuarioMock.getNombre()).thenReturn("usuario");
        when(usuarioMock.getFechaNacimiento()).thenReturn(date);
        when(usuarioMock.getCorreo()).thenReturn("usuario@gmail.com");
        when(usuarioMock.getContrasenia()).thenReturn("1234");
        when(usuarioMock.getCoches()).thenReturn(new ArrayList<Coche>());


        assertEquals("usuario", usuarioMock.getNombre());
        assertEquals(date, usuarioMock.getFechaNacimiento());
        assertEquals("usuario@gmail.com", usuarioMock.getCorreo());
        assertEquals("1234", usuarioMock.getContrasenia());
        assertEquals(new ArrayList<Coche>(), usuarioMock.getCoches());
    }

    /**
     * Test para comprobar el setter de la clase Usuario
     */
    @Test
    public void testSetter() {
        usuario.setNombre("usuario1");
        assertEquals("usuario1", usuario.getNombre());

        usuario.setFechaNacimiento(date);
        assertEquals(date, usuario.getFechaNacimiento());

        usuario.setCorreo("usuario@hotmail.com");
        assertEquals("usuario@hotmail.com", usuario.getCorreo());

        usuario.setContrasenia("12345");
        assertEquals("12345", usuario.getContrasenia());
    }

    /**
     * Test para comprobar el método  y getCoches de la clase Usuario
     */
    @Test
    public void testAddCoche() {
        Coche coche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        usuario.addCoches(coche);

        assertEquals(1, usuario.getCoches().size());
        assertEquals(coche, usuario.getCoches().get(0));
        assertEquals(Marca.Ford, usuario.getCoches().get(0).getMarca());
        assertEquals(2020, usuario.getCoches().get(0).getAnyo());
        assertEquals(Color.Black, usuario.getCoches().get(0).getColor());
        assertEquals(5000, usuario.getCoches().get(0).getKilometraje());
        assertEquals(20000, usuario.getCoches().get(0).getPrecio());
        assertFalse(usuario.getCoches().get(0).isNuevo());
    }

    @Test
    public void testEquals() {
        Usuario sameUsuario = new Usuario("usuario", date, "usuario@gmail.com", "1234", new ArrayList<Coche>());
        Usuario differentUsuario = new Usuario("usuario1", date, "usuario1@gmail.com", "12345", new ArrayList<Coche>());

        assertEquals(usuario, sameUsuario);
        assertNotEquals(usuario, differentUsuario);
    }

    @Test
    public void testHashCode() {
        Usuario sameUsuario = new Usuario("usuario", date, "usuario@gmail.com", "1234", new ArrayList<Coche>());
        assertEquals(usuario.hashCode(), sameUsuario.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("Usuario{nombre='usuario', fechaNacimiento=" + date.toString() + ", correo='usuario@gmail.com', contrasenia='1234', coches=[]}", usuario.toString());
    }


}
