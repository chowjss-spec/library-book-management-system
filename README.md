## Library-book-management-system

# Setup guide
- Run 'mvn clean' and 'mvn install' to compile the source code.
- Install docker.
- Run 'docker-compose up --build' to set up app and db containers.
- Run the following commands to downland and copy the sql driver jar to the tomcat server.

```sh
curl -O https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.31/mysql-connector-j-8.0.31.jar
docker cp mysql-connector-j-8.0.31.jar java-app-container:/usr/local/tomcat/lib/
```

- Go to http://localhost:8080/LibraryBookManagementSystem/Home to access login page.
