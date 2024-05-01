import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.jdo.Coche;
import domain.jdo.Color;
import domain.jdo.Marca;

class CocheTests {

    private Coche coche;

    @BeforeEach
    public void setUp() {
        coche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
    }

    @Test
    public void testConstructorAndGetter() {
        assertEquals(Marca.Ford, coche.getMarca());
        assertEquals(2020, coche.getAnyo());
        assertEquals(Color.Black, coche.getColor());
        assertEquals(5000, coche.getKilometraje());
        assertEquals(20000, coche.getPrecio());
        assertFalse(coche.isNuevo());
    }

    @Test
    public void testSetter() {
        coche.setMarca(Marca.Toyota);
        assertEquals(Marca.Toyota, coche.getMarca());

        coche.setAnyo(2021);
        assertEquals(2021, coche.getAnyo());

        coche.setColor(Color.White);
        assertEquals(Color.White, coche.getColor());

        coche.setKilometraje(6000);
        assertEquals(6000, coche.getKilometraje());

        coche.setPrecio(21000);
        assertEquals(21000, coche.getPrecio());

        coche.setNuevo(true);
        assertTrue(coche.isNuevo());
    }

    @Test
    public void testEquals() {
        Coche sameCoche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        Coche differentCoche = new Coche(Marca.Toyota, 2021, Color.White, 6000, 21000, true);

        assertEquals(coche, sameCoche);
        assertNotEquals(coche, differentCoche);
    }

    @Test
    public void testHashCode() {
        Coche sameCoche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
        assertEquals(coche.hashCode(), sameCoche.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Coche{id=0, marca=Ford, anyo=2020, color=Black, kilometraje=5000, precio=20000, nuevo=false}";
        assertEquals(expected, coche.toString());
    }
}
