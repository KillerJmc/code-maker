# code-maker

#### Description

Generate code for a spring boot project.

(This is a project based on mybatis-plus AutoGenerator.)

#### Function

1. Generate multi-layered architecture.
2. Clean the useless files of the project.
3. Inject a yml template for the project.
4. Inject a pom template for the project.
5. Delete itself silently after above operations.

#### Usage

1. Create a new Spring Boot project.
2. Add dependency for this module.
3. Add **spring.datasource config** into **application.properties** or **application.yml** 
4. Write **@CodeMaker(tableNames = {tableName1, tableName2,...})** on the application class.
5. **Launch** the application.
