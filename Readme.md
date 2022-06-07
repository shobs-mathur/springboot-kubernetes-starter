# Kubernetes + Springboot + MySQL
This is a kubernetes starter project for springboot applications. This project uses the kubernetes component of Docker Desktop as the kubernetes cluster.

## Build the docker image for springboot application
1. install to create the jar file 
   
         mvn clean install
2. build docker image from dockerfile 
   
         docker build -t springboot-app .

## Create kubernetes objects using kubectl
1. create namespace for application 
   
         kubectl apply -f k8s/namespace.yaml

2. create kubernetes deployment for mysql 5.7 
   
         kubectl apply -f k8s/deployment-mysql.yaml

3. expose the mysql pod as a service 
   
         kubectl apply -f k8s/service-mysql.yaml

4. execute shell command on mysql pod 
   
         kubectl exec --namespace=springboot-kubernetes --stdin --tty deployment-mysql-9b9b844db-z9nl4 -- /bin/bash

5. login to mysql instance 
   
         mysql -uroot -pTh!s|s@S$cur$P@55w0rd

6. create database and user for springboot application 
   
         CREATE DATABASE spring_envers_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
         CREATE USER 'spring_envers_user'@'%' IDENTIFIED BY 'db_user';
         GRANT ALL PRIVILEGES ON spring_envers_db.* TO 'spring_envers_user'@'%';
         FLUSH PRIVILEGES;

7. create kubernetes deployment for springboot application 
   
         kubectl apply -f k8s/deployment-springboot.yaml

8. expose the springboot application pod as a service 
    
         kubectl apply -f k8s/service-springboot.yaml


## Curl request to access the springboot service


1. add financial limits to mysql database 
   
         curl -X GET http://localhost:8201/limit/add
2. get financial limits from mysql database 
   
         curl -X GET http://localhost:8201/limit

