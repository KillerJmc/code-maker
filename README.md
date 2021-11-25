# code-maker

## Description

Generate code for a **Spring Boot** project.

(This is a project based on **[mybatis-plus generator](https://github.com/baomidou/generator)**.)

## Dependencies
+ Maven

1. Add repository
```xml
<repositories>
    <repository>
        <id>jmc-repo</id>
        <url>https://killerjmc.github.io/code-maker/repo/maven</url>
    </repository>
</repositories>
```

2. Add dependency
```xml
<dependency>
    <groupId>com.jmc</groupId>
    <artifactId>code-maker</artifactId>
    <version>latest version</version>
</dependency>
```

## Usage

1. Create a new **Spring Boot** project.
2. Add dependency for this module.
3. Add **spring.datasource config** into **application.properties** or **application.yml**
4. Write **@CodeMaker(tables = {tableName1, tableName2,...})** on the application class.
5. **Launch** the application.

## Function

+ Generate **multi-layered architecture** (pojo, dao, service, controller).
+ Clean the useless files of the project.
+ Inject a **yml template** for the project.
+ Inject a **pom template** for the project.
+ Delete itself silently after above operations.

## Feature
+ You can add  **autowired** arg on **@CodeMaker** to specify whether to add **the dependencies between layers**.
+ You can add  **tablePrefix** arg on **@CodeMaker** to specify the **table prefixes**, these prefixes **will not**
join the class name of **Java Bean** corresponding to them.

