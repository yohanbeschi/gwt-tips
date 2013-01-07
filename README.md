# Tips for GWT

Each tip is described in a tag. Each tag inherits the previous tips.

## Basics
Minimal Maven configuration for a GWT project.

### Compilation and execution
$ mvn clean install  
$ mvn gwt:run  

## Remote Logging
Ability to log client messages to the server.

### Compile and execute
$ mvn clean install  
$ mvn gwt:run  
### Logs directory
gwt-tips/

## JNDI Datsource
Uses a JNDI datasource in Development Mode.

### Compilation and execution
$ mvn clean install sql:execute  
$ mvn gwt:run  

### Connection
login: user  
password: password

### Creating a WAR without Jetty configuration nor H2 included
$ mvn clean install -P prod

### SQL scripts
/src/sql/h2

### H2 directory
gwt-tips/.h2

### jetty-web.xml
/src/profiles/dev/webapp/WEB-INF

### Logs directory
gwt-tips/

## Spring and Hibernate
Inject Spring beans in GWT Servlets

### Compilation and execution in dev mode
$ mvn clean install sql:execute -P dev  
$ mvn gwt:run -P dev
  
Important note: Due to a weird problem with the activeByDefault property in a multi-module Maven project with profile dependencies, there is no default profile anymore.

### Connection
login: user  
password: password

### Creating a WAR without Jetty configuration nor H2 included
$ mvn clean install -P prod

### SQL scripts
gwt-tips/front/src/sql/h2

### H2 directory
gwt-tips/front/.h2

### SQL scripts for testing
gwt-tips/persistence/src/sql/h2

### H2 directory for testing (database reinitialized everytime)
gwt-tips/persistence/target/.h2

### jetty-web.xml
/src/profiles/dev/webapp/WEB-INF

### Logs directory
gwt-tips/front/