# Proyecto para la asignatura Proceso de Software y Calidad, 
## Grupo 2, curso 2023-24
### Universidad de Deusto

## Introducción
Este proyecto tiene como objetivo aprender a utilizar la metodología SCRUM para la gestión de proyectos en equipos. Más que las tecnologías utilizadas, que deben ser usadas adecuadamente, por supuesto, se buscará aplicar correctamente la metodología mencionada.

## Cómo construir y ejecutar el programa
1. Tener instalado Java y Mavevn.
2. Haciendo uso de Maven, compilar el programa con `mvn compile`.
3. Ejecutar la parte del programa deseada desde Maven:
    - Servidor:          `mvn exec:java -Pservidor`
    - Cliente (usuario): `mvn exec:java -Pcliente_usuario`
    - Cliente (admin):   `mvn exec:java -Pcliente_admin`
* En un futuro cercano será necesario tener instalada una base de datos SQL y configurar los archivos con la información necesaria para establecer la conexión.

## Estructura y dependencias
Existen dos paquetes dentro de nuestra aplicación, uno contiene la lógica de la app (lado servidor) y otro la interfaz gráfica (lado cliente). Todas las dependencias se encuentran en el archivo `pom`.

## Contacto de los miembros del equipo
 - @Ivantxu55 -
 - @Federriko97 - 
 - @mariaMardones - 
 - @jrsbajo - j.suarezbajo@opendeusto.es