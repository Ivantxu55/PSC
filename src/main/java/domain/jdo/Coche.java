package domain.jdo;

import java.util.Objects;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.IdGeneratorStrategy;

@PersistenceCapable
public class Coche {

   	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private int id;

    @Persistent
    private Marca marca;
    @Persistent
    private int anyo;
    @Persistent
    private Color color;
    @Persistent
    private int kilometraje;
    @Persistent
    private int precio;
    @Persistent
    private boolean nuevo;

    public Coche(Marca marca, int anyo, Color color, int kilometraje, int precio, boolean nuevo) {
        // this.id = id;
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
