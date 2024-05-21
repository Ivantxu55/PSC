# Proyecto para la asignatura Proceso de Software y Calidad
## Grupo 2, curso 2023-24
### Universidad de Deusto

## Introducción
Este proyecto tiene como objetivo aprender a utilizar la metodología SCRUM para la gestión de proyectos en equipos. Más que las tecnologías utilizadas, que deben ser usadas adecuadamente, por supuesto, se buscará aplicar correctamente la metodología mencionada.

## Cómo construir y ejecutar el programa
1. Tener instalado Java y Maven.
2. Configurar la base de datos:
    - En el archivo `datanucleus.properties` debe modificar los campos `ConnectionURL`, `ConnectionUserName` y `ConnectionPassword` con los valores adecuado para realizar la conexión con su base de datos.
    - En el archivo `config_database.sql` se encuentran las consultas SQL para crear la tabla necesaria y para insertar algunos datos de prueba.
3. Haciendo uso de Maven, compilar el programa con `mvn compile`.
4. Ejecutar la parte del programa deseada desde Maven:
    - Servidor:          `mvn jetty:run`
    - Cliente:           `mvn exec:java -Pcliente`

## Cómo ejecutar los tests
Se recomienda ejecutar los tests antes de realizar cualquier cambio en el código y limpiar el código con `mvn clean` antes de ejecutar los tests.
Los tests sirven para comprobar que el programa funciona correctamente. Para ejecutarlos, simplemente ejecute el comando `mvn test`.
Para tener una forma más visual de ver los resultados de los tests, puede ejecutar el comando `mvn jacoco:report`.

## Estructura y dependencias
Existen dos paquetes dentro de nuestra aplicación, uno contiene la lógica de la app (lado servidor) y otro la interfaz gráfica (lado cliente). Todas las dependencias se encuentran en el archivo `pom`.

## Contacto de los miembros del equipo
 - @Ivantxu55 - ivan.casado@opendeusto.es
 - @Federriko97 - federico.alonso@opendeusto.es
 - @mariaMardones - maria.mardones@opendeusto.es
 - @jrsbajo - j.suarezbajo@opendeusto.es
