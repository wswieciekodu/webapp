# Getting Started

### About

This is same webaap created for Spring technologies overview

### RUN

To communicate with service use http client e.g. curl:

curl -X POST \
http://localhost:8080/user \
-H 'Content-Type: application/json' \
-d '{
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"age": 38,
"gender": "male"
}'

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

