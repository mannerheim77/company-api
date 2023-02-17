# Company API

> A simple SpringBoot API demonstrating CRUD operations for a Company, Offices, and Employees.  The API is containerized in Docker, and interacts with a containerized PostGres DB.  

## Technologies
<details>
<summary>Major technologies used in this app</summary><br/>

- **Spring Web MVC** - for building annotation-driven RESTful APIs:
  - ```CompanyController.java``` - for retrieving and creating companies and offices
  - ```EmployeeController.java``` - for retrieving and creating employees
- **Spring Data JPA** - for implementation of the Persistence (JPA) and Repository Layers
    - Entities:```Company.java```, ```Employee.java```, ```Office.java```
    - Repositories: ```CompanyRepository.java```, ```EmployeeRepository.java```, ```OfficeRepository.java```
- **Spring AOP** - for implementation of cross-cutting concerns (method performance and method tracing):
  - ```Timed.java```
  - ```TimedAspect.java```
  - ```Trace.java```
  - ```TraceAspect.java```
- **Logger Injection** - allows for class-aware injection of `org.slf4j.Logger` instances
    - ```LoggerProducer.java```, ```TimedAspect.java```
- **Custom Exception Handling** - for returning proper RESTful responses when encountering errors
    - ```CustomExceptionHandler.java```
- **Docker** - for containerization of the application
  - ```Dockerfile```
- **MapStruct** - for auto-generation of DTO/Entity mapper:
  - ```EntityMapper.java```
- **Lombok** - for elimination of boilerplate in POJOs, and for implementing builder pattern
</details>

##  Instructions
<details>
<summary>Installation and Startup Instructions</summary><br/>

1. Clone this repo.
2. Pull the latest Postgres Docker image:
```shell
    $> docker pull postgres
```
3. Run the postgres docker image.  Choose a new username and password for authentication below
```shell
    $> docker run -itd -e POSTGRES_USER=--username-- -e POSTGRES_PASSWORD=--password-- -p 5430:5432 -v /data:/var/lib/postgresql/data --name postgresql postgres
```
4. Build the Company API Docker image:
```shell
    $> docker build --tag company-api:latest .
```
3. Run the Company API Docker image:
```shell
    $> docker run -p8887:8080 company-api:latest
```
**To run standalone (no Docker) run the following:
```shell
    $> java -jar build/libs/demo-1.0.0.jar --spring.config.location=src/main/resources/application-dev.yaml
```
</details>

## Sample API Requests
<details>
<summary>Sample API Requests</summary><br/>

Create new company

```http request
POST http://localhost:8887/api/v1/companies
Content-Type: application/json
Accept: application/json

{
  "name": "Craigs Company"
}
```

Get all companies

```http request
GET http://localhost:8887/api/v1/companies
Content-Type: application/json
Accept: application/json
```

Create new office
```http request
POST http://localhost:8887/api/v1/companies/<id>/offices
Content-Type: application/json
Accept: application/json

{
  "name" : "Dallas Office"
}
```

Get offices for given company
```http request
GET http://localhost:8887/api/v1/companies/<id>/offices
Content-Type: application/json
Accept: application/json
```

Create new office employee
```http request
POST http://localhost:8887/api/v1/offices/<id>/employees
Content-Type: application/json
Accept: application/json

{
  "firstName" : "Craig",
  "lastName" : "Bellamy",
  "emailId" : "bellamy@whatever.com"
}
```
