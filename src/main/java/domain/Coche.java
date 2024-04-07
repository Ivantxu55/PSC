package domain;

import java.util.Objects;

public class Coche {
    private int id;
    private Marca marca;
    private int anyo;
    private Color color;
    private int kilometraje;
    private int precio;
    private boolean nuevo;

    public Coche(int id, Marca marca, int anyo, Color color, int kilometraje, int precio, boolean nuevo) {
        this.id = id;
        this.marca = marca;
        this.anyo = anyo;
        this.color = color;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.nuevo = nuevo;
    }

    public Coche() {
        this.id = 0;
        this.marca = Marca.Ford;
        this.anyo = 0;
        this.color = Color.Black;
        this.kilometraje = 0;
        this.precio = 0;
        this.nuevo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return id == coche.id && anyo == coche.anyo && kilometraje == coche.kilometraje && precio == coche.precio && nuevo == coche.nuevo && marca == coche.marca && color == coche.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, anyo, color, kilometraje, precio, nuevo);
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", marca=" + marca +
                ", anyo=" + anyo +
                ", color=" + color +
                ", kilometraje=" + kilometraje +
                ", precio=" + precio +
                ", nuevo=" + nuevo +
                '}';
    }
}
