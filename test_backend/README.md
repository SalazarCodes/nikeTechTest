# Backend de la prueba técnica de Nike

Para el uso e instalación de las dependencias seguir los siguientes pasos.

## Abrir el proyecto
Con intelliJ, abrir la carpeta que contiene el archivo pom.xml, y seleccionar la opción abrir como proyecto.

## Instalacion

Abrir una terminal en la carpeta raiz del archivo (o la terminal integrada en el IDE), y ejecutar el comando

```bash
mvn clean install
```

## Puertos y URL de la base de datos:

La base de datos se puede encontrar en el puerto 9090 de localhost, y la consola en: http://localhost:9090/h2-console.

En caso exista un error con el nombre de la base de datos, utilizar el siguiente nombre: jdbc:h2:mem:testdb
