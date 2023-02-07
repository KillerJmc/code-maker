server:
  port: 80

<#-- these args will be injected by class YmlTemplateInjectCode -->
spring:
  datasource:
    url: ${url}
    username: ${username}
    password: ${password}
