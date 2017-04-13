# TRAPPS API application
Tracking  
Resource  
Applicants  
Project  
Personnel  
Skills
Interviews
Training
Opportunities

## Build
./gradlew clean build

### PWS database setup  
cf create-service cleardb spark trapps-db  

### PCF Dev database setup  
cf create-service p-mysql 512mb trapps-db  

### Push to PCF Dev (using manifest file)
cf push trapps-api  

### Running locally
gradle bootRun local

## Steps to clone, create branch, made changes and push 
git clone https://github.com/insys-group/trapps-api.git  
git checkout develop  
git branch feature/TRAP-<story number>  
git add .  
git commit -m "description about your changes"  
git push origin feature/TRAP-<story number>
