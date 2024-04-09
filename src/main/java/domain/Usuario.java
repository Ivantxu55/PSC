package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Usuario {
    private String nombre;
    private Date fechaNacimiento;
    private String correo;
    private String contrasenia;
    private ArrayList<Coche> coches;

    public Usuario(String nombre, Date fechaNacimiento, String correo, String contrasenia, ArrayList<Coche> coches) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.coches = coches;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public ArrayList<Coche> getCoches() {
        return coches;
    }

    public void addCoches(Coche coche) {
        this.coches.add(coche);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) && Objects.equals(fechaNacimiento, usuario.fechaNacimiento) && Objects.equals(correo, usuario.correo) && Objects.equals(contrasenia, usuario.contrasenia) && Objects.equals(coches, usuario.coches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fechaNacimiento, correo, contrasenia, coches);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", coches=" + coches +
                '}';
    }
}
