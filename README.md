# Sistema Para Organizar Tarefas
Projeto criado que permiti a organização e listagem das tarefas incluindo um relatorio em PDF

## Tecnologias usadas
Este projeto foi feito com as seguintes tecnologias:
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
- Imagem docker da API do projeto todo-api
- Imagem docker do banco de dados mysql
- JUnit
- Rest-Assured
- OpenApi
- SpringDoc

## Requisitos
Para rodar este projeto, é necessário ter instalados na sua máquina os seguintes programas
- Docker
- Docker Compose

## Como executar este projeto
Entre na pasta do projeto, onde está o arquivo `docker-compose.yaml` e então use o seguinte comando para executar o projeto
```
docker-compose up
```
Para parar a excução, utilize o comando:
`
Ctrl + C
`
ou, em outro terminal aberto no mesmo caminho:
```
docker-compose stop
```

`docker-compose up` criará os containers especificados no arquivo `docker-compose.yaml` e os inicializará.


Após os containers serem criados o banco de dados iria usar a porta 3307, use um programa como `Postman` ou `Insomnia` para fazer as requisições http.
Uma vez com o Insomnia ou Postman aberto, vá até a url [http://localhost:8080/tarefas](http://localhost:8080/tarefas) para interagir com a api rest.

## Documentação
Esse projeto foi documentado no padrão OpenApi, apos a execução va a url [http://hotelo.api:8080/swagger-ui/index.html#/Tarefas](http://hotelo.api:8080/swagger-ui/index.html#/Tarefas) para acessar mais detalhes sobre os EndPoints.
