package domain;

import java.util.ArrayList;

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


}
