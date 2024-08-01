#!/bin/bash

BRANCH_PREFIX="origin/"
BRANCH_NAME="${GIT_BRANCH#${BRANCH_PREFIX}}"
REPOSITORY="https://nexus.apps.cl-desa.bse.com.uy/repository/maven-releases/"
GROUP_ID=$($MVN org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.groupId -q -DforceStdout -f $BASE_POM_PATH | sed 's/\./\//g') 
ARTIFACT_ID=$($MVN org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.artifactId -q -DforceStdout -f $BASE_POM_PATH | sed 's/\./\//g') 
REPOSITORY_VERSIONS_URL=$REPOSITORY/$GROUP_ID/$ARTIFACT_ID/maven-metadata.xml

echo "########## URL del artefacto Maven a empaquetar en Nexus es $REPOSITORY_VERSIONS_URL ##########"
echo "########## La branch que estamos construyendo es $BRANCH_NAME ##########"

REGEXP_BRANCH_VERSION="([0-9]+\.[0-9]+\.)([xX]+)\-BETA"
if [[ $BRANCH_NAME =~ $REGEXP_BRANCH_VERSION ]]; then
    RESPONSE_CODE=$(curl --head --silent --insecure "$REPOSITORY_VERSIONS_URL" | grep -i "HTTP/1.1" | awk '{print $2}')
    echo "########## HTTP status al obtener maven-metadata.xml es $RESPONSE_CODE ##########"
    
    if [ "$RESPONSE_CODE" == "404" ]; then
        if [[ $BRANCH_NAME == release/* ]]; then
            VERSION_PREFIX=$(echo "${BRANCH_NAME#release/}" | sed -E 's/^([0-9]+\.[0-9]+)\..*/\1/')
            INITIAL_VERSION="${VERSION_PREFIX}.0-BETA"
            echo "########## No existe un artefacto, se debe desplegar el primero ##########"
            $MVN versions:set -DnewVersion=$INITIAL_VERSION -f $BASE_POM_PATH
            echo "$INITIAL_VERSION" > ".version"
        else
            echo "########## No existe artefacto y no estamos en una rama release válida ##########"
            exit 1
        fi
    else
        echo "########## Ya existen artefactos, creamos nueva versión ##########" 
        curl --insecure -o maven-metadata.xml "$REPOSITORY_VERSIONS_URL"

        echo "########## Versiones hasta el momento ##########" 
        cat ./maven-metadata.xml

        BRANCH_VERSION="${BASH_REMATCH[1]}"
        echo "########## La versión del branch a desplegar paquete BETA es $BRANCH_VERSION ##########"

        BRANCH_VERSION_REGEXP=$(echo "$BRANCH_VERSION" | sed 's/\./\\\./g')
        RELEASE_VERSION=$(grep -oP "<version>\K${BRANCH_VERSION_REGEXP}[0-9]+-BETA(?=</version>)" ./maven-metadata.xml | sort -V | tail -n 1)
        echo "########## El último release es $RELEASE_VERSION ##########"

        REGEXP_PATCH_VERSION="([0-9]+\.[0-9]+\.)([0-9]+)\-BETA"
        if [ -z "$RELEASE_VERSION" ]; then
            echo "########## No existe artefacto con versión de branch $BRANCH_VERSION, creamos primer paquete BETA ##########"
            $MVN versions:set -DnewVersion=${BRANCH_VERSION}0-BETA -f $BASE_POM_PATH
            echo "${BRANCH_VERSION}0-BETA" > ".version"
        elif [[ $RELEASE_VERSION =~ $REGEXP_PATCH_VERSION ]]; then
            PATCH_VERSION="${BASH_REMATCH[2]}"
            PATCH_VERSION_DEPLOY=$((PATCH_VERSION+1))
            
            echo "########## El último patch version de la versión $RELEASE_VERSION es $PATCH_VERSION ##########"
            echo "########## El siguiente patch es $PATCH_VERSION_DEPLOY ##########"
            
            RELEASE_VERSION_DEPLOY="${BASH_REMATCH[1]}$PATCH_VERSION_DEPLOY"
            echo "########## El release a desplegar es $RELEASE_VERSION_DEPLOY ##########"   
            $MVN versions:set -DnewVersion=$RELEASE_VERSION_DEPLOY-BETA -f $BASE_POM_PATH
            echo "$RELEASE_VERSION_DEPLOY-BETA" > ".version"
        else
            echo "########## No se pudo determinar la versión patch, las versiones en Nexus no respetan el nombrado de artefactos, no se sube artefacto a Nexus ##########"
            exit 1
        fi  
    fi
    echo "########## Subiendo release a Nexus ##########"
    $MVN clean install -Dmaven.test.skip=true -f $BASE_POM_PATH
    $MVN deploy -Dmaven.test.skip=true -f $BASE_POM_PATH
else
    echo "########## La versión de la rama no es una versión válida, el nombre de la rama debe tener el formato A.B.X-BETA, siendo A y B las versiones mayor y minor del release ##########"
    exit 1
fi
