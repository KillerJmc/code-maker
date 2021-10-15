# code-maker

## 介绍

为一个**Spring Boot** 项目自动生成代码

（这是一个基于 **[mybatis-plus generator](https://gitee.com/baomidou/generator)** 的项目）

## 用法

1. 创建一个新的 **Spring Boot** 项目
2. 加入本模块的依赖
3. 在 **application.properties** 或者 **application.yml** 中加入 **spring.datasource** 配置
4. 在启动类上编写 **@CodeMaker(tables = {表名1, 表名2,...})**
5. **启动** 程序

## 功能

+ 构建 **多层架构**（pojo, dao, service, controller）.
+ 清理项目中的无用文件
+ 自动注入 **yml模板**
+ 自动注入 **pom模板**
+ 完成上述操作后自动删除本身依赖

## 特性
+ 你可以在 **@CodeMaker** 中加入 **autowired** 参数来指定是否自动创建 **层级间的依赖**
+ 你可以在 **@CodeMaker** 中加入 **tablePrefix** 参数来指定**表的前缀**，这些前缀**不会**加入它们对应
**JavaBean**的类名中
