#!/bin/bash

# Parametros
LABEL_SELECTOR="app=$OPENSHIFT_CONTAINER"
MAX_WAIT=900 # 15 minutos
SLEEP_INTERVAL=5 # 5 segundos

echo "El comando a ejecutar es: oc get pods -l $LABEL_SELECTOR -l environment=test -o jsonpath=\"{range .items[*]}{.metadata.name}{'\t'}{.status.conditions[?(@.type=='Ready')].status}{'\n'}{end}\" --namespace=$OPENSHIFT_NAMESPACE | grep -oP True"
echo "El comando a ejecutar es: oc get pods -l $LABEL_SELECTOR -l environment=test -o json --namespace=$OPENSHIFT_NAMESPACE | grep -oP '(?=\"image\").*(?=\,)' | head -n 1 | grep -oP '[0-9]+\.[0-9]+\.[0-9]+-(BETA|FINAL)'"

get_pod_ready_status() {
    oc get pods -l $LABEL_SELECTOR -l environment=test -o jsonpath="{range .items[*]}{.status.phase}{'\t'}{.metadata.name}{'\t'}{.status.conditions[?(@.type=='Ready')].status}{'\n'}{end}" --namespace=$OPENSHIFT_NAMESPACE | grep -P 'Running\s+\S+\s+True'
}

get_pod_version() {
    oc get pods -l $LABEL_SELECTOR -l environment=test -o json --namespace=$OPENSHIFT_NAMESPACE | grep -oP '(?=\"image\").*(?=\,)' | head -n 1 | grep -oP '[0-9]+\.[0-9]+\.[0-9]+-(BETA|FINAL)'
}

POD_READY=$(get_pod_ready_status)
POD_VERSION=$(get_pod_version)

echo "Estado del pod 'Ready': $POD_READY"
echo "Versión del pod: $POD_VERSION"

START_TIME=$(date +%s)

while [ $(( $(date +%s) - $START_TIME )) -lt $MAX_WAIT ]; do
    if [[ "$VERSION_INPUT" == "$POD_VERSION" ]]; then
        echo "La versión desplegada es $VERSION_INPUT y la presente es $POD_VERSION"
        if [[ -n "$POD_READY" ]]; then
            echo "El pod está listo con la versión $VERSION_INPUT. Despliegue completo."
            exit 0
        else
            echo "La versión desplegada es $VERSION_INPUT, pero el estado aún no es 'Ready'. Continuamos esperando."
            sleep $SLEEP_INTERVAL
        fi
    else
        echo "La versión desplegada es $VERSION_INPUT y la presente es $POD_VERSION. Se continúa esperando la nueva versión."
        sleep $SLEEP_INTERVAL
    fi

    POD_READY=$(get_pod_ready_status)
    POD_VERSION=$(get_pod_version)
done

echo "Tiempo máximo alcanzado sin encontrar pods en estado 'Ready'. Revise la consola, puede haber un error de despliegue."
exit 1
