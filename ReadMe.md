# Read Me First

# springboot-nostra movie apps

Minimal [Spring Boot](https://spring.io/projects/spring-boot) Nostra Movie App.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

For the database using :
- [PostgreSQL](https://www.postgresql.org/)

For the documentation API using :
- [Swagger](https://swagger.io/)

URL for Swagger UI :
- [Local Swagger](http://localhost:8080/swagger-ui.html)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.nostra.nostramovieapps.NostraMovieAppsApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

compile project : 

```shell
mvn clean install -P dev-local
```

## Database Configuration

```shell
host      : localhost
database  : nostra_movie
username  : postgres
password  : postgres
schema    : nostra
port      : 5432
```