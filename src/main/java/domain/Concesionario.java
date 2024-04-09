package domain;

import java.util.ArrayList;
import java.util.Scanner;

public class Concesionario {

    private String nombre;
    private String localidad;
    private ArrayList<Coche> coches;

    // declaramos un constructor vacío
    public Concesionario(){
        this.nombre = "";
        this.localidad = "";
        this.coches = new ArrayList<Coche>();
    }

    // declaramos el constructor (sobrecargar método)
    public Concesionario(String nombre, String localidad, ArrayList<Coche> coches){
        this.nombre = nombre;
        this.localidad = localidad;
        this.coches = coches;
    }

    // métodos "getter" y "setter":
    // nombre
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    // localidad
    public String getLocalidad(){
        return localidad;
    }
    public void setLocalidad(String localidad){
        this.localidad = localidad;
    }

    // coches
    public ArrayList<Coche> getCoches(){
        return coches;
    }
    // no estableceremos un "setCoches", porque esto se manejará mediante los siguientes métodos:
    // agregar un coche al concesionario
    public void agregarCoche(Coche coche) {
        this.coches.add(coche);
    }
    // eliminar un coche del concesionario
    public boolean eliminarCoche(Coche coche) {
        return this.coches.remove(coche);
    }
    // ------------------------------

    // encontrar un coche por su identificador
    public Coche encontrarCochePorId(int id) {
        for (Coche coche : coches) {
            if (coche.getId() == id) {
                return coche;
            }
        }
        return null; // devolver "nulo" si no se encontró el coche en la lista
    }

    // @Override
    public String toString() {
        return "Concesionario{" +
               "nombre='" + nombre + '\'' +
               ", localidad='" + localidad + '\'' +
               ", coches=" + coches +
               '}';
    }




    // Creo unos susuarios y contraseñas por ahora
    private static final String[][] usuarios = {{"usuario1", "password1"}, {"usuario2", "password2"}, {"usuario3", "password3"}};

    public static void main(String[] args) {
        // Lógica de inicio de sesión
        boolean loggedIn = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema del concesionario");

        while (!loggedIn) {
            System.out.print("Ingrese su nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            if (login(username, password)) {
                loggedIn = true;
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + username + "!");
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.");
            }
        }
        scanner.close();
        // Mostrar el menú principal del concesionario.
    }

    // Método para verificar la autenticación del usuario
    private static boolean login(String username, String password) {
        for (String[] user : usuarios) {
            if (user[0].equals(username) && user[1].equals(password)) {
                return true; // El usuario y la contraseña coinciden
            }
        }
        return false; // No se encontró coincidencia
    }



}
