# Pruebas en contenedor local

Para probar en un contenedor local debe utilizar el Dockerfile que se encuentra en la raiz del proyecto, para eso nos paramos en la raiz del proyecto y ejecutamos el siguiente comando de build.

    podman build -t sp-ws-mibse-mibse:latest .

Luego de crear la imagen para ejecutarla debe correr el siguiente comando

    podman run -e "LOG_LEVEL=debug" -p 8080:8080 -p 8081:8081 sp-ws-mibse-mibse:latest