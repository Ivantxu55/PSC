/* =============================
 * **Explicación de la prueba**
 * =============================
 * Vamos asegurar que LogicaCliente realiza las llamadas adecuadas a la API simulada y además
 * confirmar que LogicaCliente maneja las respuestas de la API de acuerdo con lo esperado.
 * -----------------------------
 * Usaremos JUnit junto con Mockito, ya que nos permite simular las respuestas de la API y 
 * verificar el comportamiento del código en diferentes escenarios
 * .............................
 * Algunos detalles sobre estas pruebas:
 * - verificamos que se realicen las llamadas correctas a métodos específicos (como WebTarget.path y Builder.delete).
 * - comprobamos que el código gestiona correctamente los códigos de respuesta HTTP y procesa los cuerpos de las respuestas.
 * - utilizamos JUnit para estructurar las pruebas y Mockito para simular las respuestas de la API y verificar el comportamiento del código.
 */

// -------------------------------------
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import domain.jdo.Coche;
import gui.LogicaCliente;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;
// -------------------------------------

class LogicaClienteTest {

    private Client client;
    private WebTarget webTarget;
    private LogicaCliente logicaCliente;
    private Coche coche;

    @BeforeEach
    void setUp() {
        client = mock(Client.class);
        webTarget = mock(WebTarget.class);
        when(client.target(anyString())).thenReturn(webTarget);
        // Pasar el cliente simulado al constructor
        logicaCliente = new LogicaCliente("localhost", "8080", client);

        coche = new Coche();  // Asume que tienes un constructor adecuado
        coche.setId(1);
        // Configura más propiedades según sea necesario
    }

    // **************************************************************
    // vamos a probar que el método agregarCoche del objeto de la clase logicaCliente 
    // interactúa con la API como se espera
    // **************************************************************
    @Test
    void testAgregarCoche() {
        WebTarget agregarCocheTarget = mock(WebTarget.class);
        Invocation.Builder builder = mock(Invocation.Builder.class);

        // configurar el mock para simular la API para agregar un coche
        when(webTarget.path("agregarCoche")).thenReturn(agregarCocheTarget);  // simulamos webtarget para la ruta especifica de agregarCoche
        when(agregarCocheTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);  // obtenemos un objeto builder para hacer luego la petición
        when(builder.post(Entity.entity(coche, MediaType.APPLICATION_JSON))).thenReturn(Response.status(Response.Status.OK).build());  // simulamos la petición POST
        // ----------------------------------------------------------------

        logicaCliente.agregarCoche(coche);

        verify(builder).post(Entity.entity(coche, MediaType.APPLICATION_JSON));
    }

    // **************************************************************
    // vamos a probar que el método modificarCoche interactúa con la API como se espera
    // **************************************************************
    @Test
    void testModificarCoche() {
        WebTarget modificarCocheTarget = mock(WebTarget.class);
        WebTarget cocheConId = mock(WebTarget.class);
        Invocation.Builder builder = mock(Invocation.Builder.class);
        Response simulatedResponse = mock(Response.class); // Crear un objeto Response simulado

        Map<String, Object> cambios = new HashMap<>();
        cambios.put("modelo", "Modelo Nuevo");
        cambios.put("color", "Azul");

        // configurando el mock para simular la API para modificar un coche
        when(webTarget.path("modificarCoche/{id}")).thenReturn(modificarCocheTarget);  // simulamos webtarget para la ruta especifica de modificarCoche 
        when(modificarCocheTarget.resolveTemplate("id", coche.getId())).thenReturn(cocheConId); // resolvemos la plantilla con el id del coche que se nos ha pasado
        when(cocheConId.request(MediaType.APPLICATION_JSON)).thenReturn(builder);  // obtenemos un objeto builder para hacer luego la petición
        when(builder.put(Entity.entity(cambios, MediaType.APPLICATION_JSON))).thenReturn(simulatedResponse);  // simulamos la petición PUT
        when(simulatedResponse.getStatus()).thenReturn(Response.Status.OK.getStatusCode()); // Simular una respuesta OK
        // ----------------------------------------------------------------

        // Llamar al método a probar
        logicaCliente.modificarCoche(coche, cambios);

        // Verificar que la petición PUT se envió correctamente
        verify(builder).put(Entity.entity(cambios, MediaType.APPLICATION_JSON));
    }

    // **************************************************************
    // vamos a probar que el método eliminarCoche interactúa con la API como se espera
    // **************************************************************
    @Test
    void testEliminarCoche() {
        // Configurar un WebTarget simulado específico para eliminarCoche
        WebTarget eliminarCocheTarget = mock(WebTarget.class);
        WebTarget cocheConId = mock(WebTarget.class);
        Invocation.Builder builder = mock(Invocation.Builder.class);
        Response response = mock(Response.class); // Crear un objeto Response simulado

        // configurando el mock para simular la API para eliminar un coche
        when(webTarget.path("eliminarCoche/{id}")).thenReturn(eliminarCocheTarget);  // simulamos webtarget para la ruta especifica de eliminarCoche
        when(eliminarCocheTarget.resolveTemplate("id", coche.getId())).thenReturn(cocheConId);  // resolvemos la plantilla con el id del coche que se nos ha pasado
        when(cocheConId.request(MediaType.APPLICATION_JSON)).thenReturn(builder);   // obtenemos un objeto builder para hacer luego la petición
        when(builder.delete()).thenReturn(response);                                // simulamos la petición DELETE 
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());  // simulamos que la respuesta es OK
        // when(response.readEntity(String.class)).thenReturn("Coche eliminado exitosamente");
        // ---------------------------------------------------------------

        // método a probar
        logicaCliente.eliminarCoche(coche);

        // Verificar que la petición DELETE se envió correctamente y se leyó la entidad de la respuesta
        verify(builder).delete();
        verify(response).readEntity(String.class);

        // verificamos comportamiento ante código 200
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(response).readEntity(String.class); // Verificar que se lee la entidad de la respuesta

    }

}
