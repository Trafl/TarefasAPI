# TodoTasks
This is a simple yet effective solution for task registration and organization based on priority. The API is built following the MVC architecture, uses DTOs for data transfer, includes API tests implemented with Rest-assured, and is fully documented in the OpenAPI standard to provide a comprehensive understanding of its endpoints. Additionally, I have made the API image available on DockerHub to simplify execution, among other features.

## Technologies used
This project was made with the following technologies:
- Java 17
- Spring Boot
- Spring Data JPA
- ModelMapper
- Flyway
- Lombok
- MySQL
- JasperReports
- Docker
- Docker compose
- Docker image of the todo-api project API
- Mysql database docker image
- JUnit
- Rest-Assured
- OpenApi
- SpringDoc

## Requirements 
To run this project, you must have the following programs installed on your machine
- Docker
- Docker Compose

## How to run this project
Enter the project folder where the `docker-compose.yaml` file is located and then use the following command to run the project
```
docker-compose up
```
To stop execution, use the command:
`
Ctrl + C
`
or, in another terminal open in the same path:
```
docker-compose stop
```

`docker-compose up` will create the containers specified in the file `docker-compose.yaml` and initialize them.

After the containers are created, the database would use port 3307, use a program like `Postman` or `Insomnia` to make http requests.
Once you have Insomnia or Postman open, go to the url [http://localhost:8080/trabalhos](http://localhost:8080/trabalhos) to interact with the rest api.

## Documentation
This project was documented in the OpenApi standard, after execution go to the url [http://localhost:8080/swagger-ui/index.html#/Tasks](http://localhost:8080/swagger-ui/index.html#/Tasks) to access more details about EndPoints.
