# Agencia de Turismo - API REST

Este proyecto es una API REST desarrollada para una agencia de turismo que permite gestionar la búsqueda y reserva de hoteles y vuelos. La API está construida utilizando Spring Boot, Java, y sigue las mejores prácticas de desarrollo, incluyendo el uso de Spring Security, JWT, JPA, Hibernate, y Swagger para la documentación de los endpoints.

## Requisitos Técnicos

- **Java 17**
- **Spring Boot 3.4.2 (Spring Security, Spring Data JPA)**
- **MySQL 8.0**
- **Maven**
- **Hibernate**
- **Swagger para documentación de API**
- **Postman para pruebas**

# Estructura del Repositorio #

`/agencia-de-turismo
│── /src
│── /sql
│    ├── agencia_de_turismo.sql (Base de datos)
│    ├── datos agencia de viajes.sql (Script de carga de datos)
│── /Postman
│    ├── AgenciaDeTurismo.postman_collection.json (Colección de endpoints)
│── README.md
│── pom.xml`

## Estructura del Proyecto

El proyecto está organizado en las siguientes capas:

- **Controladores**: Manejan las solicitudes HTTP y devuelven las respuestas correspondientes.
- **Servicios**: Contienen la lógica de negocio y se comunican con los repositorios.
- **Repositorios**: Interactúan con la base de datos para realizar operaciones CRUD.
- **Modelos**: Representan las entidades de la base de datos.
- **DTOs**: Objetos de transferencia de datos utilizados para enviar y recibir información entre el cliente y el servidor.

## Configuración del Proyecto

### Base de Datos

Puedes usar la base de datos proporcionada en la carpeta `sql`:

- Importa agencia_de_turismo.sql en tu gestor de base de datos.

- O ejecuta el script datos agencia de viajes.sql para cargar los datos en otra base.

- **Es necesario alguno de estos pasos para la correcta utilización de los test unitarios.**

### Postman

Encontrarás un archivo llamado `postman/AgenciaDeTurismo.postman_collection.json`. Este archivo contiene una colección de Postman con todos los endpoints de la API, junto con ejemplos de solicitudes y respuestas.

### Configuración de la Aplicación

El archivo `application.properties` contiene la configuración necesaria para conectar la aplicación con la base de datos MySQL. Asegúrate de ajustar las credenciales y la URL de la base de datos según tu entorno.
```
properties
spring.datasource.url=jdbc:mysql://localhost:3306/agencia_de_turismo?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```
# Ejecución del Proyecto
```
1. git clone https://github.com/rosmander/BlancoPerez_pruebatec4.git
cd agencia-de-turismo
```
2. Asegúrate de tener MySQL instalado y en ejecución.

4. Configura las credenciales de la base de datos en el archivo `application.properties.`

5. Ejecuta la aplicación utilizando Maven:

```
mvn clean install
mvn spring-boot:run

```
6. Accede a la documentación de la API en Swagger UI: `http://localhost:8080/doc/swagger-ui.html.`


# Endpoints

## Hoteles

- GET /agency/hotels: Obtiene un listado de todos los hoteles registrados.

- GET /agency/rooms: Obtiene un listado de habitaciones disponibles en un rango de fechas y destino específico.

- POST /agency/room-booking/new: Realiza una reserva de habitación.

- POST /agency/hotels/new: Crea un nuevo hotel (requiere autenticación).

- PUT /agency/hotels/edit/{id}: Actualiza un hotel existente (requiere autenticación).

- DELETE /agency/hotels/delete/{id}: Elimina un hotel (requiere autenticación).

## Vuelos

-  GET /agency/flights: Obtiene un listado de todos los vuelos registrados.

-  GET /agency/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=ciudad1&destination=ciudad2: Obtiene un listado de vuelos disponibles en un rango de fechas y según origen y destino.

-  POST /agency/flight-booking/new: Realiza una reserva de vuelo.

-  POST /agency/flights/new: Crea un nuevo vuelo (requiere autenticación).

-  PUT /agency/flights/edit/{id}: Actualiza un vuelo existente (requiere autenticación).

-  DELETE /agency/flights/delete/{id}: Elimina un vuelo (requiere autenticación).

# Pruebas

Puedes utilizar la colección de Postman proporcionada en la carpeta `Postman` para probar los endpoints de la API. La colección incluye ejemplos de solicitudes y respuestas para cada endpoint.

# Seguridad y Autenticación

- Acceso libre a los endpoints de consulta de hoteles y vuelos.

- Autenticación requerida para realizar reservas o gestionar datos.
  
- Seguridad básica para proteger los endpoints sensibles.

# Contribuciones

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.

2. Crea una nueva rama para tu contribución.

3. Realiza tus cambios y asegúrate de que todas las pruebas pasen.

4. Envía un pull request con una descripción detallada de los cambios realizados.
