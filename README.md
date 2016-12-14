#TRAPPS API application
Tracking  
Resource  
Applicants  
Project  
Personnel  
Skills  
 
##develop branch works on CF right now. Haven't tested on pcf dev. 
Please install Lombok Plugin in IDE
https://projectlombok.org/features/index.html

## Build and push to CF 
mvn clean package  

### PWS database setup  
cf create-service cleardb spark mysqldb  

### PCF Dev database setup  
cf create-service p-mysql 512mb mysqldb  

### Push to PCF Dev (using manifest file)
cf push trapps-api  

### Running locally
mvn -Dspring.profiles.active=local spring-boot:run   

### Accessing H2 query console (available when you run using local profile)
http://localhost:8081/console/

##Steps to clone, create branch, made changes and push 
git clone https://github.com/insys-group/trapps-api.git  
git checkout develop  
git branch feature/TRAP-<story number>  
git add .  
git commit -m "description about your changes"  
git push origin feature/TRAP-NN  
 
