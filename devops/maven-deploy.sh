#!/bin/bash

echo "########## El pipeline que estamos ejecutando es $JOB_BASE_NAME ##########"
if [[ $JOB_BASE_NAME =~ beta$ ]]; then
    echo "########## Se despliega BETA en repositorio de release ##########" 
    ./devops/maven-deploy-beta.sh
elif [[ $JOB_BASE_NAME =~ final$ ]]; then
    echo "########## Se despliega FINAL en repositorio de release ##########" 
    ./devops/maven-deploy-final.sh
elif [[ $JOB_BASE_NAME =~ snapshot$ ]]; then
    echo "########## Se despliega SNAPSHOT en repositorio de snapshots ##########" 
    ./devops/maven-deploy-snapshot.sh
else 
    echo "########## Pipeline de despliegue invalido ##########"     
fi