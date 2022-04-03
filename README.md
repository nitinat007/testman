# testman

A tool to manage STLC, its minor and major releases and do analytics around the system collected data to derive
meaningful insights.

#### H2 DB

* H2 database url: http://localhost:8080/h2-console
* JDBC url : `jdbc:h2:mem:universitydb`

#### Swagger

* SwaggerUI url: http://localhost:8080/swagger-ui.html

#### Features & Technology:

* Spring Boot microservices
* Using JPA
* Custom Exception Handling
* JPA Auditing enabled

#### Other Planned Items

* Use Thymeleaf html template engine to expose bootstrap based UI
* Make this product available as docker image
* Allow custom external relational DB configuration
* DB dump creation
* Calculate reliability/stability of testcases being run