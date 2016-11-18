#TRAPPS API application
Tracking  
Resource  
Applicants  
Project  
Personnel  
Skills  
 
##develop branch works on CF right now.  

##Build and push to CF (for now) 
mvn -Dmaven.test.skip=true clean package  

--next line for PWS  
cf create-service cleardb spark mysqldb  
--use this line for PCF Dev  
cf create-service p-mysql 512mb mysqldb  

cf push trapps-api -p target/trapps-api-0.0.1-SNAPSHOT.jar  


##Steps to clone, create branch, made changes and push 
git clone https://github.com/insys-group/trapps-api.git  
git checkout develop  
git branch feature/TRAP-<story number>  
git add .  
git commit -m "<some description about your changes>"  
git push origin feature/TRAP-<story number>  
 
