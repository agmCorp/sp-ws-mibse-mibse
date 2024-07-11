#!/bin/bash

BRANCH_PREFIX="origin/"
BRANCH_NAME="${GIT_BRANCH#${BRANCH_PREFIX}}"
REPOSITORY="https://nexus.apps.cl-desa.bse.com.uy/repository/maven-snapshots"
GROUP_ID=$($MVN org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.groupId -q -DforceStdout -f $BASE_POM_PATH | sed 's/\./\//g') 
ARTIFACT_ID=$($MVN org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.artifactId -q -DforceStdout -f $BASE_POM_PATH | sed 's/\./\//g') 
REPOSITORY_VERSIONS_URL=$REPOSITORY/$GROUP_ID/$ARTIFACT_ID/1.0.0-SNAPSHOT/maven-metadata.xml

echo "########## URL del artefacto Maven a empaquetar en Nexus es $REPOSITORY_VERSIONS_URL ##########"
echo "########## La branch que estamos construyendo es $BRANCH_NAME ##########"

echo "########## Seteando version a snapshot ##########"
$MVN versions:set -DnewVersion=1.0.0-SNAPSHOT -f $BASE_POM_PATH

echo "########## Subiendo snapshot a Nexus ##########"
$MVN clean install -Dmaven.test.skip=true -f $BASE_POM_PATH
$MVN deploy -Dmaven.test.skip=true -f $BASE_POM_PATH

echo "########## Obteniendo numero de build de snapshot para version ##########"
curl -s $REPOSITORY_VERSIONS_URL -o maven-metadata.xml --insecure

echo "########## Contenido del maven-metadata.xml de Nexus ##########"
cat maven-metadata.xml

TIMESTAMP=$(grep -oP '(?<=<timestamp>).*(?=</timestamp>)' maven-metadata.xml)
BUILD_NUMBER=$(grep -oP '(?<=<buildNumber>).*(?=</buildNumber>)' maven-metadata.xml)

echo "$TIMESTAMP-$BUILD_NUMBER" > ".version"