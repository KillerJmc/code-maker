# code-maker

## Description

Generate code for a **Spring Boot** project.

(This is a project based on **mybatis-plus AutoGenerator**.)

## Usage

1. Create a new **Spring Boot** project.
2. Add dependency for this module.
3. Add **spring.datasource config** into **application.properties** or **application.yml**
4. Write **@CodeMaker(tables = {tableName1, tableName2,...})** on the application class.
5. **Launch** the application.

## Function

+ Generate **multi-layered architecture** (pojo, mapper, service, controller).
+ Clean the useless files of the project.
+ Inject a **yml template** for the project.
+ Inject a **pom template** for the project.
+ Delete itself silently after above operations.

## Feature
+ You can add  **autowired** arg on **@CodeMaker** to specify whether to add **the dependencies between layers**.
