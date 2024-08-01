#!/bin/bash

BRANCH_PREFIX="origin/"
BRANCH_NAME="${GIT_BRANCH#${BRANCH_PREFIX}}"
REPOSITORY="https://nexus.apps.cl-desa.bse.com.uy/repository/maven-releases/"
GROUP_ID=$($MVN org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.groupId -q -DforceStdout -f $BASE_POM_PATH | sed 's/\./\//g') 
ARTIFACT_ID=$($MVN org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.artifactId -q -DforceStdout -f $BASE_POM_PATH | sed 's/\./\//g') 
REPOSITORY_VERSIONS_URL=$REPOSITORY/$GROUP_ID/$ARTIFACT_ID/maven-metadata.xml

echo "########## URL del artefacto Maven a empaquetar en Nexus es $REPOSITORY_VERSIONS_URL ##########"
echo "########## La branch que estamos construyendo es $BRANCH_NAME ##########"

if [[ $BRANCH_NAME == "main" ]]; then
    RESPONSE_CODE=$(curl --head --silent --insecure "$REPOSITORY_VERSIONS_URL" | grep -i "HTTP/1.1" | awk '{print $2}')
    echo "########## HTTP status al obtener maven-metadata.xml es $RESPONSE_CODE ##########"
    
    if [ "$RESPONSE_CODE" == "404" ]; then
        echo "########## No existe un artefacto, se debe desplegar el primero ##########"
        $MVN versions:set -DnewVersion=1.0.0-FINAL -f $BASE_POM_PATH
        echo "1.0.0-FINAL" > ".version"
    else
        echo "########## Ya existen artefactos, creamos nueva version ##########" 
        curl --insecure -o maven-metadata.xml "$REPOSITORY_VERSIONS_URL"

        echo "########## Versiones hasta el momento ##########" 
        cat ./maven-metadata.xml
        
        REGEXP_BRANCH_VERSION="([0-9]+\.[0-9]+\.)[Xx]+\-BETA"

        if [[ $GITLAB_SOURCE_BRANCH =~ $REGEXP_BRANCH_VERSION ]]; then
            BRANCH_VERSION="${BASH_REMATCH[1]}"
            echo "########## La version del branch a desplegar paquete FINAL es $BRANCH_VERSION ##########"
            
            BRANCH_VERSION_REGEXP=$(echo "$BRANCH_VERSION" | sed 's/\./\\\./g')
            RELEASE_VERSION_DEPLOY=$(grep -oP "<version>\K${BRANCH_VERSION_REGEXP}.*(?=-BETA</version>)" ./maven-metadata.xml | tail -n 1)

            echo "########## El release a desplegar es $RELEASE_VERSION_DEPLOY ##########"   
            $MVN versions:set -DnewVersion=$RELEASE_VERSION_DEPLOY-FINAL -f $BASE_POM_PATH 
            echo "$RELEASE_VERSION_DEPLOY-FINAL" > ".version"
        else
            echo "########## No se pudo determinar la version BETA, no se sube artefacto a Nexus ##########"
            exit 1
        fi
    fi
    echo "########## Subiendo release a Nexus ##########"
    $MVN clean install -f $BASE_POM_PATH
    $MVN deploy -f $BASE_POM_PATH
else
    echo "########## La rama a construir no es main, no se despliega el paquete FINAL ##########"
    exit 1
fi        
