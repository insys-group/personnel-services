#TRAPPS API application
Tracking  
Resource  
Applicants  
Project  
Personnel  
Skills  
 
##develop branch works on CF right now. Haven't tested on pcf dev. 

##Build and push to CF (for now) 
mvn -Dmaven.test.skip=true clean package  
cf create-service cleardb spark mysqldb  
cf push trapps-api -p target/trapps-api-0.0.1-SNAPSHOT.jar  
cf bind-service trapps-api mysqldb  
cf restart trapps-api  
 
##Steps to clone, create branch, made changes and push 
git clone https://github.com/insys-group/trapps-api.git  
git checkout develop  
git branch feature/TRAP-<story number>  
git add .  
git commit -m "<some description about your changes>"  
git push origin feature/TRAP-<story number>  
 
