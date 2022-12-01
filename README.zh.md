中文 &nbsp; | &nbsp; [English](README.md)

# code-maker

## 介绍

为一个**Spring Boot** 项目自动生成代码

（这是一个基于 **[mybatis-plus generator](https://gitee.com/baomidou/generator)** 的项目）

## 依赖
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

+ 构建 **多层架构**（pojo, dao, service, controller）.
+ 清理项目中的无用文件
+ 自动注入 **yml模板**
+ 自动注入 **pom模板**
+ 完成上述操作后自动删除本身依赖

## 特性
在 **@CodeMaker** 中可以指定以下属性
+ **include** 属性可指定 **导入的表**
+ **exclude** 属性可指定 **排除的表**
+ **tablePrefix** 属性可指定 **表的前缀** ，这些前缀 **不会** 加入它们对应
 **JavaBean** 的类名中
+ **autowired** 属性可指定是否自动创建**组件与其上级的依赖**

