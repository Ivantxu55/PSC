// package domain;

// import javax.jdo.JDOHelper;
// import javax.jdo.PersistenceManager;
// import javax.jdo.PersistenceManagerFactory;
// import javax.jdo.Transaction;

// import org.eclipse.jetty.server.Server;
// import org.eclipse.jetty.servlet.ServletContextHandler;
// import org.eclipse.jetty.servlet.ServletHolder;
// import org.eclipse.jetty.webapp.WebAppContext;

// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;

// @WebServlet(name = "MyServlet", urlPatterns = {"/"})
// public class Main {

//     public static void main(String[] args) {
//         // configurando JDO para persistencia
//         PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
//         PersistenceManager pm = pmf.getPersistenceManager();
//         Transaction tx = pm.currentTransaction();

//         try {

//             Server server = new Server(8080);
//             WebAppContext context = new WebAppContext();
//             context.setContextPath("/");
//             // context.setResourceBase("webapp/");
//             // context.setDescriptor("webapp/WEB-INF/web.xml");
            
//             // context.setParentLoaderPriority(true);
            
//             // server.setHandler(context);

//             // Configure Jersey servlet
//             ServletHolder jerseyServlet = context.addServlet(
//                     org.glassfish.jersey.servlet.ServletContainer.class, "/api/*");
//             jerseyServlet.setInitOrder(0);
//             jerseyServlet.setInitParameter(
//                 "jersey.config.server.provider.packages",
//                 "your.package.name.here");  // Replace with your package name containing JAX-RS resources

//             server.setHandler(context);

//             server.start();
//             server.join();

//         } catch (Exception e) {
//             e.printStackTrace();  // Manejo de la excepción
//         }

//     }

// }
