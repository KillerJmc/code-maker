[中文](README.zh.md) &nbsp; | &nbsp; English

# code-maker



## Description

Generate code for a **Spring Boot** project.

(This is a project based on **[mybatis-plus generator](https://github.com/baomidou/generator)**.)



## Dependencies
+ Gradle

  1. Add repository
      ```groovy
      maven { url "https://killerjmc.github.io/code-maker/repo" }
      ```

  2. Add dependency
     ```groovy
     implementation "com.jmc:code-maker:latestVersion"
     ```

     

+ Maven

  1. Add repository
      ```xml
      <repository>
          <id>jmc-code-maker-repo</id>
          <url>https://killerjmc.github.io/code-maker/repo</url>
      </repository>
      ```

  2. Add dependency
     ```xml
     <dependency>
         <groupId>com.jmc</groupId>
         <artifactId>code-maker</artifactId>
         <version>latest-version</version>
     </dependency>
     ```

     

## Usage

1. Create a new **Spring Boot** project.
2. Add dependency for this module.
3. Add **spring.datasource config** into **application.properties** or **application.yml**
4. Write **@CodeMaker** on the application class.
5. **Launch** the application.



## Function

+ Generate **multi-layered architecture** (pojo, dao, service, controller).
+ Clean the useless files of the project.
+ Inject a **yml template** for the project.
+ Inject **dependencies** for the project.
+ Delete itself silently after above operations.



## Feature

You can specify following properties in **@CodeMaker**

+ Add **buildType** arg to specify build type (**Gradle**, **Maven** or **None**).
+ Add **persistenceFramework** arg to specify persistence framwork (**JPA** or **MyBatis Plus**).

+ Add **include** arg to include tables.
+ Add **include** arg to exclude tables.
+ Add  **tablePrefix** arg to specify the **table prefixes**, these prefixes **will not** join the class name of **Java Bean** corresponding to them.
+ Add **autowired** arg to specify whether to add the **dependencies** between components and their **parents**.
+ Add **injectYml** arg to inject **application.yml** template.

