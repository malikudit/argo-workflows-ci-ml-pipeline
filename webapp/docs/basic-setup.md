Reproducting the API's functionality:

Requirements and dependencies:
- Java 8
- Spring Boot
- PostgreSQL
- Maven
- Angular 12
- Angular HttpClient
- Angular Router
- Bootstrap 4

Setup:
1. Clone the repository to a local directory with the command:
$ git clone https://github.com/malikudit/vuse-summer-research.git

2. cd into src/main/resources, and open the application.properties file.

3. Make the following edits to set up PostgreSQL for running the app:
spring.datasource.url= jdbc:postgresql://localhost:5432/{name-of-postgres-user}
spring.datasource.username= {name-of-postgres-user}
spring.datasource.password= {postgres-user-password}

4. Run the Spring Boot application with the command. The database table is automatically generated on doing so.
mvn spring-boot:run

5. Test the API with Postman by sending a POST request to http://localhost:8080/api/workflows with the following raw JSON example:
{
    "name": "test",
    "description": "test"
}

6. Test the database by logging into Postgres from your terminal:
$ sudo su postgres
$ psql
$ \d workflows

7. To run the Angular web client for the app, install the following dependency and then navigate to the angularclient folder and run:
$ npm install --save-dev @angular-devkit/build-angular
$ ng serve --port 8081
