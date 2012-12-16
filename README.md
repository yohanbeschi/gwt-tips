# Tips for GWT

Each tip is described in a tag. Some tags can inherit other tips.

## Basics
Minimal Maven configuration for a GWT project.
### Compile and execute
mvn clean install  
mvn gwt:run  

## Remote Logging
Ability to log client errors to the server.
### Compile and execute
mvn clean install  
mvn gwt:run  

## JNDI Datsource
Use a JNDI datasource in Development Mode.
### Compile and execute
mvn sql:execute  
mvn clean install  
mvn gwt:run  

### Connection
login: user  
password: password

### Creating a WAR without Jetty configuration nor H2 included
mvn clean install -P prod

### SQL scripts
/src/sql/h2

### jetty-web.xml
/src/profiles/dev/webapp/WEB-INF