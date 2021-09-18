# code-maker

#### 介绍

为一个**Spring Boot** 项目自动生成代码

（这是一个基于**mybatis-plus AutoGenerator**的项目）

#### 功能

1. 构建 **多层架构**（pojo, mapper, service, controller）.
2. 清理项目中的无用文件
3. 自动注入 **yml模板**
4. 自动注入 **pom模板**
5. 完成上述操作后自动删除本身依赖

#### 用法

1. 创建一个新的 **Spring Boot** 项目
2. 加入本模块的依赖
3. 在 **application.properties** 或者 **application.yml** 中加入 **spring.datasource** 配置
4. 在启动类上编写 **@CodeMaker(tableNames = {表名1, 表名2,...})**
5. **启动** 程序


#### 特性
你可以在 **@CodeMaker** 中加入 **autowired** 参数来指定是否自动创建 **层级间的依赖**
