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

import domain.jdo.Coche;

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

}
