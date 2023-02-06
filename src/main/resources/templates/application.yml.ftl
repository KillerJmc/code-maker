server:
  port: 80

spring:
  datasource:
    <#-- these args will be injected by class YmlTemplateInjectCode -->
    url: ${url}
    username: ${username}
    password: ${password}
