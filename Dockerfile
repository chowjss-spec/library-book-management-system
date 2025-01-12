# Step 1: Use Tomcat as the base image
FROM tomcat:9.0-jdk17-openjdk

# Step 2: Remove the default web application (optional)
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Step 3: Copy your WAR file into the Tomcat webapps directory
COPY ./target/LibraryBookManagementSystem-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/LibraryBookManagementSystem.war


# Step 4: Expose the default Tomcat port (8080)
EXPOSE 8080

# Step 5: Set the command to run Tomcat (this is the default entrypoint)
CMD ["catalina.sh", "run"]