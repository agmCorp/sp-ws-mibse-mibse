pipeline {
    agent {
        kubernetes {
            yamlFile 'devops/cd-springboot-kubernetes-pod.yml'
        }
    }
    environment {
        ENV_FILE            = '.env.build'
        OPENSHIFT_NAMESPACE = 'mibse'
        OPENSHIFT_CONTAINER = 'sp-ws-mibse'
        GITOPS_NAME         = 'mibse'
        GITOPS_REPOSITORY   = 'sp-ws-mibse'
    }
    stages {

        stage("Otorgar permisos a los archivos .sh"){
            steps{
                container('jnlp'){
                    sh'chmod +x ./devops/*.sh'
                }
            }
        }
        stage('Openshift Build Image') {
            steps {
                container('oc') {
                    script {
                        def versionInput = input(
                            id: 'version_text',
                            message: 'Esperando nombre del paquete a desplegar',
                            parameters: [string(defaultValue: '', description: 'Coloque el nombre de la version como aparece en el campo Component Version en Nexus', name: 'version')]
                        )

                        env.VERSION_INPUT = versionInput

                        sh """
                            touch devops/$ENV_FILE
                            echo APPLICATION_JAR_URL="https://nexus.apps.cl-desa.bse.com.uy/repository/maven-releases/uy/com/bse/mibse/sp/ws/mibse/${versionInput}/mibse-${versionInput}.jar" >> devops/$ENV_FILE
                            
                            sed -i 's/VERSION/${versionInput}/g' ./devops/cd-springboot-buildconfig.yaml
                            sed -i 's/OPENSHIFT_CONTAINER/$OPENSHIFT_CONTAINER/g' ./devops/cd-springboot-buildconfig.yaml

                            oc delete buildconfig $OPENSHIFT_CONTAINER-buildconfig --namespace=$OPENSHIFT_NAMESPACE || true
                            oc create -f ./devops/cd-springboot-buildconfig.yaml --save-config --namespace=$OPENSHIFT_NAMESPACE || true
                            oc start-build $OPENSHIFT_CONTAINER-buildconfig --from-dir ./devops --follow --namespace=$OPENSHIFT_NAMESPACE
                            
                            devops/oc-wait-status-build.sh
                        """
                    }
                }
            }
        }
        stage("Git Commit Change Image") {
             steps {
                container('jnlp') {
                    script {
                         env.REPOSITORY_URL = "https://${env.USUARIO_AD}:${env.USUARIO_TOKEN}@gitlab.bse.com.uy/apps-gitops/${GITOPS_NAME}/${GITOPS_REPOSITORY}.git"
                         sh './devops/git-commit-version.sh'
                    }
                }
            }
        }
        
        stage("OpenShift Wait New Pods Ready") {
             steps {
                container('oc') {
                    sh './devops/oc-wait-status-deploy.sh'
                }
            }
        }

    }
}
