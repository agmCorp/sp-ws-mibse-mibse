#!/bin/bash

echo "########## Tagueamos para version $GIT_TAG_PUSH ##########"
REGEXP_TAG_VERSION="([0-9]+\.[0-9]+\.)([0-9]+)\-(BETA|FINAL)"
if [[ $GIT_TAG_PUSH =~ $REGEXP_TAG_VERSION ]]; then
    echo "########## La version $GIT_TAG_PUSH, es una version valida para tagear, pusheamos tag ##########"
    git tag $GIT_TAG_PUSH
    git push $REPOSITORY_URL --tags
else 
    echo "########## La version $GIT_TAG_PUSH, no es una version valida revise la nomenclatura de la version o realice el tag manualmente ##########" 
fi