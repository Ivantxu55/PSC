package domain.jdo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/coche")
public class CocheResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoche(@PathParam("id") int id) {
        // Aquí deberías buscar el Coche con el id especificado en tu base de datos
        // y devolverlo. Por ahora, solo devolveremos un Coche de ejemplo.
        Coche coche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        return Response.ok(coche).build();
    }

    // Aquí puedes añadir más métodos para manejar otras solicitudes HTTP,
    // como POST para crear un nuevo Coche, PUT para actualizar un Coche existente,
    // DELETE para eliminar un Coche, etc.
}