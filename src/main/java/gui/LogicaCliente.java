package gui;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import domain.jdo.Coche;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;


public class LogicaCliente {

	private final Client client;
	private final WebTarget webTarget;

	public LogicaCliente(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}

    public void agregarCoche(Coche coche) {
        System.out.println(coche);

        // establecemos conexion con el servidor a través de la API
        WebTarget agregarCocheWebTarget = webTarget.path("agregarCoche");

        // enviamos una petición POST con los datos del coche en formato JSON
		Invocation.Builder invocationBuilder = agregarCocheWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(coche, MediaType.APPLICATION_JSON));

        // LOG4J AÚN SIN CONFIGURAR
        // // comprobamos y registramos si la petición ha sido correcta o no
		// if (response.getStatus() != Status.OK.getStatusCode()) {
		// 	logger.error("Error connecting with the server. Code: {}", response.getStatus());
		// } else {
		// 	logger.info("User correctly registered");
		// }
    }

}