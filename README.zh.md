中文 &nbsp; | &nbsp; [English](README.md)

# code-maker



## 介绍

为一个**Spring Boot** 项目自动生成代码

（这是一个基于 **[mybatis-plus generator](https://gitee.com/baomidou/generator)** 的项目）



## 依赖
+ Gradle

  1. 添加仓库
     ```groovy
     maven { url "https://killerjmc.github.io/code-maker/repo" }
     maven { url "https://killerjmc.github.io/jmc-utils/repo" }
     ```
     
  2. 添加依赖

     ```groovy
     implementation "com.jmc:code-maker:latestVersion"
     ```

     

+ Maven

  1. 添加仓库
      ```xml
      <repository>
          <id>jmc-code-maker-repo</id>
          <url>https://killerjmc.github.io/code-maker/repo</url>
      </repository>
      ```

  2. 添加依赖
     ```xml
     <dependency>
         <groupId>com.jmc</groupId>
         <artifactId>code-maker</artifactId>
         <version>latest-version</version>
     </dependency>
     ```



## 用法

1. 创建一个新的 **Spring Boot** 项目
2. 加入本模块的依赖
3. 在 **application.properties** 或者 **application.yml** 中加入 **spring.datasource** 配置
4. 在启动类上编写 **@CodeMaker**
5. **启动** 程序



## 功能

+ 构建 **多层架构**（pojo, dao, service, controller）
+ 清理项目中的无用文件
+ 自动注入 **yml模板**
+ 自动注入项目依赖
+ 完成上述操作后自动删除本身依赖



## 特性

在 **@CodeMaker** 中可以指定以下属性

+ **persistenceFramework** 属性可指定持久化框架 （**JPA** 或者 **MyBatis Plus**）

+ **include** 属性可指定 **导入的表**
+ **exclude** 属性可指定 **排除的表**
+ **tablePrefix** 属性可指定 **表的前缀** ，这些前缀**不会**加入它们对应 **JavaBean** 的类名中
+ **autowired** 属性可指定是否自动创建**组件与其上级的依赖**
+ **injectYml** 属性可注入 **application.yml** 的模板

