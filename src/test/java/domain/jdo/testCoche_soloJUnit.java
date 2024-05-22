// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import domain.jdo.Coche;
// import domain.jdo.Color;
// import domain.jdo.Marca;

// import java.util.Objects;

// class CocheTests {

//     private Coche coche;

//     @BeforeEach
//     public void setUp() {
//         coche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
//     }

//     @Test
//     public void testConstructorAndGetter() {
//         assertEquals(Marca.Ford, coche.getMarca());
//         assertEquals(2020, coche.getAnyo());
//         assertEquals(Color.Black, coche.getColor());
//         assertEquals(5000, coche.getKilometraje());
//         assertEquals(20000, coche.getPrecio());
//         assertFalse(coche.isNuevo());
//     }

//     @Test
//     public void testSetter() {
//         coche.setMarca(Marca.Toyota);
//         assertEquals(Marca.Toyota, coche.getMarca());

//         coche.setAnyo(2021);
//         assertEquals(2021, coche.getAnyo());

//         coche.setColor(Color.White);
//         assertEquals(Color.White, coche.getColor());

//         coche.setKilometraje(6000);
//         assertEquals(6000, coche.getKilometraje());

//         coche.setPrecio(21000);
//         assertEquals(21000, coche.getPrecio());

//         coche.setNuevo(true);
//         assertTrue(coche.isNuevo());

//         coche.setId(1);
//         assertEquals(1, coche.getId());
//     }

//     @Test
//     public void testEquals() {
//         Coche sameCoche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
//         Coche differentCoche = new Coche(Marca.Toyota, 2021, Color.White, 6000, 21000, true);

//         assertEquals(coche, sameCoche);
//         assertNotEquals(coche, differentCoche);

//         Coche coche1 = new Coche(Marca.Ford, 2010, Color.Black, 100000, 5000, false);
//         Coche coche2 = new Coche(Marca.Ford, 2010, Color.Black, 100000, 5000, false);
//         assertTrue(coche1.equals(coche2), "Los coches deberían ser iguales");
//     }

//     @Test
//     public void testHashCode() {
//         Coche sameCoche = new Coche(Marca.Ford, 2020, Color.Black, 5000, 20000, false);
//         assertEquals(coche.hashCode(), sameCoche.hashCode());

//         Coche coche = new Coche(Marca.Ford, 2010, Color.Black, 100000, 5000, false);
//         assertEquals(Objects.hash(0, Marca.Ford, 2010, Color.Black, 100000, 5000, false), coche.hashCode(), "HashCode debe coincidir con la implementación");
//     }

//     @Test
//     public void testToString() {
//         String expected = "Coche{id=0, marca=Ford, anyo=2020, color=Black, kilometraje=5000, precio=20000, nuevo=false}";
//         assertEquals(expected, coche.toString());
//     }

//     @Test
//     void testConstructorVacio() {
//         Coche coche = new Coche();
//         assertNotNull(coche, "El coche no debe ser nulo");
//         assertEquals(Marca.Ford, coche.getMarca(), "La marca por defecto debe ser Ford");
//         assertTrue(coche.isNuevo(), "El coche nuevo debe ser verdadero por defecto");
//     }

//     @Test
//     void testConstructorConParametros() {
//         Coche coche = new Coche(Marca.Toyota, 2015, Color.White, 50000, 15000, true);
//         assertEquals(Marca.Toyota, coche.getMarca(), "La marca debe ser Toyota");
//         assertEquals(2015, coche.getAnyo(), "El año debe ser 2015");
//     }

// }
