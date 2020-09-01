# Real Estate Company
REST app based on Spring framework

## Technology
* Spring Boot, Spring Data
* MySQL, Flyway
* Mockito, JUnit
* Lombok
* Gradle
* Swagger

## Set up
* Clone repository
* Create database schema 'company' (MySQL)
* Configure credential on **application.yaml** and **build.gradle**
* Run command on terminal 'gradle flywayMigrate -i'
* Run Spring Boot application
* Enjoy! [Link to swagger-ui](http://localhost:8080/swagger-ui.html)

## Features
##### Agent Controller
* Create agent
* Load agent by id
* Load agent list
* Update agent
* Delete agent

##### Deal Controller
* Create deal
* Load deal by id
* Load deal list
* Load deal list by agent id
* Load top sellers by limit

##### Real Estate Controller
* Create real estate
* Load real estate by id
* Load real estate list
* Load real estate list by status
* Update real estate
* Delete real estate