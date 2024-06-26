package domain;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;

import javax.ws.rs.PUT;
import javax.ws.rs.GET;

import domain.jdo.Coche;

import java.util.concurrent.TimeUnit;
import java.util.Map;

import domain.jdo.Marca;
import domain.jdo.Color;

import java.util.List;
import java.util.ArrayList;

import javax.jdo.Query;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;

/**
 * Clase que representa un recurso REST con los metodos para agregar, eliminar y modificar coches.
 * @see Coche
 */
@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {


    public static void resource(String[] args) {
        // configurando JDO para persistencia
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
    }

    @POST
    @Path("/agregarCoche")
    // @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarCoche(Coche coche) {

        System.out.println(coche);

        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin(); // iniciar transacción
            pm.makePersistent(coche);
                        tx.commit(); // commit de la transacción
            return Response.ok("Coche almacenado con éxito: " + coche).build();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback(); // rollback en caso de error
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al almacenar el coche: " + e.getMessage()).build();
        } finally {
            if (pm != null && !pm.isClosed()) {
                pm.close(); // cerrar el PersistenceManager
            }
        }
    }

    @DELETE
    @Path("/eliminarCoche/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCoche(@PathParam("id") Long id) {
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin(); // iniciar transacción

            // Buscar el coche por ID y eliminarlo
            Coche coche = pm.getObjectById(Coche.class, id);
            pm.deletePersistent(coche);
            tx.commit(); // commit de la transacción
            return Response.ok("Coche eliminado con éxito: " + coche).build();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback(); // rollback en caso de error
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al eliminar el coche: " + e.getMessage()).build();
        } finally {
            if (pm != null && !pm.isClosed()) {
                pm.close(); // cerrar el PersistenceManager
            }
        }
    }

    @PUT
    @Path("/modificarCoche/{id}")
    // @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarCoche(@PathParam("id") Long id, Map<String, Object> cambios) {
        System.out.println("Intentando modificar el coche con ID: " + id + " con cambios: " + cambios);

        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin(); // iniciar transacción
            Coche cocheExistente = pm.getObjectById(Coche.class, id);
            if (cocheExistente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                            .entity("Coche no encontrado con ID: " + id).build();
            }

            // Actualizar los campos del coche si están presentes en el JSON
            if (cambios.containsKey("precio")) {
                cocheExistente.setPrecio((Integer) cambios.get("precio"));
            }
            if (cambios.containsKey("kilometraje")) {
                cocheExistente.setKilometraje((Integer) cambios.get("kilometraje"));
            }
            if (cambios.containsKey("color")) {
                Color color = Color.valueOf((String) cambios.get("color"));
                cocheExistente.setColor(color);
            }
            if (cambios.containsKey("marca")) {
                Marca marca = Marca.valueOf((String) cambios.get("marca"));
                cocheExistente.setMarca(marca);
            }
            if (cambios.containsKey("anyo")) {
                cocheExistente.setAnyo((Integer) cambios.get("anyo"));
            }
            if (cambios.containsKey("nuevo")) {
                cocheExistente.setNuevo((Boolean) cambios.get("nuevo"));
            }

            pm.makePersistent(cocheExistente);
            tx.commit(); // commit de la transacción
            return Response.ok("Coche modificado con éxito: " + cocheExistente).build();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback(); // rollback en caso de error
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error al modificar el coche: " + e.getMessage()).build();
        } finally {
            if (pm != null && !pm.isClosed()) {
                pm.close(); // cerrar el PersistenceManager
            }
        }
    }

    @GET
    @Path("/getCoches")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoches() {

        ArrayList<Coche> coches = new ArrayList<>();

        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();

        try {
            // consulta SQL para obtener todos los coches
            Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT * FROM COCHE");
            List<Object[]> results = (List<Object[]>) q.execute();

            // iterar sobre cada fila de resultados para crear objetos Coche y añadirlos a la ArrayList coches
            for (Object[] row : results) {
                Marca marca = Marca.valueOf((String) row[1]);
                Color color = Color.valueOf((String) row[3]);
                Coche coche = new Coche(
                    marca,          // marca
                    (Integer) row[2], // año
                    color,          // color
                    (Integer) row[4], // km
                    (Integer) row[5], // precio
                    (Boolean) row[6]  // nuevo
                );
                coche.setId((Integer) row[0]);
                coches.add(coche);
            }
        } finally {
            pm.close();  // nos aseguramos de cerrar la consulta y el PersistenceManager para liberar recursos
        }

        Gson gson = new Gson();
        // System.out.println("Coches: " + gson.toJson(coches));
        return Response.ok(gson.toJson(coches)).build();
    }

}
