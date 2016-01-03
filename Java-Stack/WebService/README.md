#SmartHomeWebService
数据库的连接配置放在persistence.xml和db.properties（在spring配置文件中引用）。
数据库的连接池用的阿里巴巴。

目录结构
src/main/java	源代码
src/main/resources 配置文件
src/main/webapp web部署需要的文件，主要在这里指的是web.xml
src/test/java 测试的源代码
src/test/resources 测试所需要的资源

部署有关
部署到tomacat，构建的话执行命令tomcat7:build或者tomcat7:run；
数据库生成的sql放在src/main/resources/sql/smart_home_server.sql端口号3306
数据库目前先配置在本机，数据库名test，
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/test" />
<property name="javax.persistence.jdbc.user" value="root" />
<property name="javax.persistence.jdbc.password" value="root" />

疑问：
不知道为什么必须要在persitence.xml中写关于jdbc连接的信息，而不能只在spring中直接配置好。