//// package domain;
//
//// import java.util.ArrayList;
//// import java.util.Properties;
//
//// import javax.jdo.JDOHelper;
//// import javax.jdo.PersistenceManager;
//// import javax.jdo.Transaction;
//// import javax.jdo.PersistenceManagerFactory;
//
//// class pruebaPersistencia {
////     public static void main(String[] args) {
//
////     // Properties properties = new Properties();
////     // properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
////     // properties.setProperty("javax.jdo.option.ConnectionURL", "jdbc:mysql://localhost/concesionariosdb");
////     // properties.setProperty("javax.jdo.option.ConnectionDriverName", "com.mysql.cj.jdbc.Driver");
////     // properties.setProperty("javax.jdo.option.ConnectionUserName", "usuario");
////     // properties.setProperty("javax.jdo.option.ConnectionPassword", "pass");
////     // properties.setProperty("datanucleus.autoCreateSchema", "true");
//
////     // PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties);
//
////         // PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
////         // PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("datanucleus.properties").getPersistenceManager();
////         // Transaction tx = pm.currentTransaction();
////         PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
////         PersistenceManager pm = pmf.getPersistenceManager();
////         Transaction tx = pm.currentTransaction();
//
////         try {
////             tx.begin();
//
////             Concesionario concesionario = new Concesionario("Concesionario XYZ", "Ciudad X", new ArrayList<>());
////             pm.makePersistent(concesionario);
//
////             tx.commit();
////         } finally {
////             if (tx.isActive()) {
////                 tx.rollback();
////             }
////             pm.close();
////         }
////     }
//// }
//
//
//// public class Main {
////     public static void main(String[] args) {
////         System.out.println("hola mundo");
//
////         try {
////             System.out.println("ejecutando prueba");
////             pruebaPersistencia.main(args);
////             System.out.println("prueba finalizada con éxito");
////         } catch (Exception e) {
////             System.out.println("ha ocurrido un error con la prueba");
////             e.printStackTrace();
////     }
//// }
//// }
//
//package domain;
//
//import java.util.ArrayList;
//import java.util.Properties;
//import javax.jdo.JDOHelper;
//import javax.jdo.PersistenceManager;
//import javax.jdo.Transaction;
//import javax.jdo.PersistenceManagerFactory;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//class TestDB {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/concesionariosdb"; // Ajusta esto para tu base de datos
//        String user = "root";
//        String password = "pass";
//
//        try {
//            Connection conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Conectado exitosamente a la base de datos.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//class pruebaPersistencia {
//    public static void main(String[] args) {
//        // Creación de un objeto Properties y configuración de las propiedades de conexión y otras opciones
//        Properties properties = new Properties();
//        properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
//        properties.setProperty("javax.jdo.option.ConnectionURL", "jdbc:mysql://localhost:3306/concesionariosdb");
//        properties.setProperty("javax.jdo.option.ConnectionDriverName", "com.mysql.cj.jdbc.Driver");
//        properties.setProperty("javax.jdo.option.ConnectionUserName", "root");
//        properties.setProperty("javax.jdo.option.ConnectionPassword", "pass");
//        properties.setProperty("datanucleus.autoCreateSchema", "true");
//
//        // Obtención de la PersistenceManagerFactory usando las propiedades definidas
//        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties);
//        PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx = pm.currentTransaction();
//        try {
//            tx.begin();
//
//            // Suponiendo que Concesionario es una clase que ya has definido y está mapeada correctamente para JDO
//            Concesionario concesionario = new Concesionario("Concesionario XYZ", "Ciudad X", new ArrayList<>());
//            pm.makePersistent(concesionario);
//
//            tx.commit();
//        } catch (Exception e) {
//            System.out.println("Error durante la transacción: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            pm.close();
//        }
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hola mundo");
//
//        try {
//            System.out.println("Ejecutando pruebas");
//            TestDB.main(args);
//            pruebaPersistencia.main(args);
//            System.out.println("Pruebas finalizada con éxito");
//        } catch (Exception e) {
//            System.out.println("Ha ocurrido un error con la prueba");
//            e.printStackTrace();
//        }
//    }
//}
