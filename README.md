# Flight Search API

This project serves as a foundational example of a flight management system. Using the system, users can list flights,
add new flights, and search for flights based on various criteria.

## Project Setup
- Clone this repository.
- Download Docker.
- Start PostgreSQL.
```sql
docker-compose -f src/main/resources/postgresql.yml up -d
```
- Run the project.
```sql
./mvnw spring-boot:run
```
- go to swagger api
```sql
http://localhost:8088/swagger-ui/index.html
```

## Features:

1. Flights Management:

- List Flights: View all available flights.
- Add New Flight: Ability to add new flights to the system.
- Search Flights: Search for flights based on departure airport, arrival airport, departure date, and return date.
- Update Flight: Modify the details of an existing flight.
- Delete Flight: Remove a flight from the system.

2. Airports Management:

- List Airports: Display a list of all available airports.
- Add New Airport: Ability to add new airports to the system.
- Update Airport: Modify the details of an existing airport.
- Delete Airport: Remove an airport from the system.

3. Daily Scheduled Task: A scheduled task that generates a random flight and adds it to the system every day at a
   specific time.

## Technologies:

 - Java
 - Spring Boot
 - Spring Security for Authentication and Authorization
 - Swagger for API Documentation
 - Spring Data JPA
 - PostgreSQL

