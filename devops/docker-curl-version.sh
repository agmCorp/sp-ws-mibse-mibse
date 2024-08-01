#!/bin/bash

. ./devops/.env.build
HTTP_CODE=$(curl "${APPLICATION_JAR_URL}" -o application.jar -s -w "%{http_code}" --insecure)
if [ "$HTTP_CODE" = "200" ]; then
    echo "Parquete descargado de Nexus (HTTP 200 OK)"
    exit 0
else
    echo "No se pudo descargar paquete de Nexus, HTTP Status Code: $HTTP_CODE"
    exit 1
fi