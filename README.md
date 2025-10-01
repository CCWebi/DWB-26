# Seminario Desarrollo Web Backend: Proyecto

Este repositorio contiene el proyecto del Seminario de Desarrollo Web Backend.

## Ambiente de desarrollo

- Java 17 / 21
- Maven 3.9.1
- Spring Boot 3.5.5

### Base de Datos

> [!IMPORTANT]
> Uso de MYSQL

Las variables (puerto, dirección, usuario y contraseña), se dejaron por defecto.
La conexión con nuestra DB fue éxitosa.

En `database/DDL.sql` se deja el código usado para crear la base.
Esperamos no tengamos variables con *nombres diferentes*.

## How to run

Puedes usar cualquier IDE que soporte Maven y Spring Boot, como IntelliJ IDEA o Eclipse o VSCode con los plugiins recomendados.

Y solo buscar la opción `Run`.

O desde la terminal con los siguientes comandos:

```bash
mvn clean install
mvn spring-boot:run
```

Se abrirá en el puerto `8080` por defecto.

## Documentación de la API

La documentación de la API está disponible en:

```plaintext
http://localhost:8080/documentación.html
```

Y con javadoc:

```bash
mvn javadoc:javadoc
```

La documentación generada estará en `target/reports/apidocs/index.html` o `target/site/apidocs/index.html` dependiendo de la versión de Maven.
