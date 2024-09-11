#!/bin/bash

sonar-scanner \
  -Dsonar.projectKey=$SONAR_PROJECT_KEY \
  -Dsonar.sources=./sp-ws-mibse-mibse/src \
  -Dsonar.host.url=$SONAR_HOST \
  -Dsonar.login=$SONAR_LOGIN \
  -Dsonar.java.binaries=./sp-ws-mibse-mibse/target/classes \
  -Dsonar.java.libraries=./sp-ws-mibse-mibse/target/**/*.jar
