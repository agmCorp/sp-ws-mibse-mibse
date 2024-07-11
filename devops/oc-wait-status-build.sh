#!/bin/bash

BUILD_STATUS=$(oc get build $OPENSHIFT_CONTAINER-buildconfig-1 -o=jsonpath='{.status.phase}' --namespace=$OPENSHIFT_NAMESPACE)
while [ "$BUILD_STATUS" == "Running" ]; do
    echo "Esperando a que finalice el build"
    sleep 5
    BUILD_STATUS=$(oc get build $OPENSHIFT_CONTAINER-buildconfig-1 -o=jsonpath='{.status.phase}' --namespace=$OPENSHIFT_NAMESPACE)
done
echo "Build finalizada con estado $BUILD_STATUS"
if [ "$BUILD_STATUS" == "Complete" ]; then
    exit 0
else
    exit 1
fi
