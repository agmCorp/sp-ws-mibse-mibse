#!/bin/bash

git clone -b non-prod $REPOSITORY_URL git-ops
sed -i "s/image: .*\$/image: image-registry.openshift-image-registry.svc:5000\/$OPENSHIFT_NAMESPACE\/$OPENSHIFT_CONTAINER-imagestream:$VERSION_INPUT/g" git-ops/envs/test/version.yml
cat git-ops/envs/test/version.yml

if [[ $VERSION_INPUT == *FINAL ]]; then
    sed -i "s/image: .*\$/image: image-registry.openshift-image-registry.svc:5000\/$OPENSHIFT_NAMESPACE\/$OPENSHIFT_CONTAINER-imagestream:$VERSION_INPUT/g" git-ops/envs/prod/version.yml
    cat git-ops/envs/prod/version.yml
fi

cd git-ops
git status
git config --global user.email "jenkins-devops@bse.com.uy"
git config --global user.name "jenkins-devops"
git add envs/test/version.yml

if [[ $VERSION_INPUT == *FINAL ]]; then
    git add envs/prod/version.yml
fi

git commit -m "Jenkins Deploy Git, version $VERSION_INPUT"
git push origin non-prod