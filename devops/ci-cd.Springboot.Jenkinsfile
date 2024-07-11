pipeline {
    agent {
        kubernetes {
            yamlFile 'devops/ci-cd-springboot-kubernetes-pod.yml'
        }
    }
    post {
      failure {
        updateGitlabCommitStatus name: STAGE_NAME, state: 'failed'
      }
      success {
        updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
      }
      aborted {
        updateGitlabCommitStatus name: STAGE_NAME, state: 'canceled'
      }
    }
    environment {
        BASE_POM_PATH        = 'sp-ws-mibse-mibse/pom.xml'
        MVN                  = 'mvn'
        GITLAB_SOURCE_BRANCH = "${env.gitlabSourceBranch}"
        GITLAB_TARGET_BRANCH = "${env.gitlabTargetBranch}"
        ENV_FILE             = '.env.build'
        OPENSHIFT_NAMESPACE  = 'mibse'
        OPENSHIFT_CONTAINER  = 'sp-ws-mibse'
        GITOPS_NAME          = 'mibse'
        GITOPS_REPOSITORY    = 'sp-ws-mibse'
    }
    options {
        gitLabConnection('gitlab-bse')
    }
    stages {

        stage("Otorgar permisos a los archivos .sh"){
            steps{
                container('jnlp'){
                    sh'chmod +x ./devops/*.sh'
                }
            }
        }
        stage('Clone settings Maven') {
            steps {
                updateGitlabCommitStatus name: STAGE_NAME, state: 'running'
                container('jnlp') {
                    sh 'git clone https://gitlab.bse.com.uy/desarrollo/settings-maven.git ./settings-maven'
                }
                updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
            }
        }
        stage("Configuracion settings Maven") {
            steps {
                updateGitlabCommitStatus name: STAGE_NAME, state: 'running'
                container('maven') {
                    sh './devops/maven-config.sh'
                }
                updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
            }    
        }
        stage("Maven Install") {
            steps {
                updateGitlabCommitStatus name: STAGE_NAME, state: 'running'
                container('maven') {
                    sh '$MVN clean package -Dmaven.test.skip=true -f $BASE_POM_PATH'
                }
                updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
            }    
        }
        stage("Maven Deploy a Nexus") {
            steps {
                updateGitlabCommitStatus name: STAGE_NAME, state: 'running'
                container('maven') {
                    script {
                        sh './devops/maven-deploy.sh'
                        def tagVersion = sh(script: 'cat .version', returnStdout: true).trim()
                        echo "La variable tiene como valor ${tagVersion}"
                        env.SNAPSHOT_VERSION = tagVersion
                    }
                }
                updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
            }
        }
        stage('Openshift Build Image') {
            steps {
                updateGitlabCommitStatus name: STAGE_NAME, state: 'running'
                container('oc') {
                    script {
                        env.VERSION_INPUT = env.SNAPSHOT_VERSION

                        sh """
                            touch devops/$ENV_FILE
                            echo APPLICATION_JAR_URL="https://nexus.apps.cl-desa.bse.com.uy/repository/maven-snapshots/uy/com/bse/mibse/sp/ws/mibse/1.0.0-SNAPSHOT/mibse-1.0.0-${env.VERSION_INPUT}.jar" >> devops/$ENV_FILE

                            sed -i 's/VERSION/${env.VERSION_INPUT}/g' ./devops/cd-springboot-buildconfig.yaml
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
                updateGitlabCommitStatus name: STAGE_NAME, state: 'running'
                container('jnlp') {
                    script {
                        env.REPOSITORY_URL = "https://${env.USUARIO_AD}:${env.USUARIO_TOKEN}@gitlab.bse.com.uy/apps-gitops/${GITOPS_NAME}/${GITOPS_REPOSITORY}.git"
                        sh './devops/git-commit-version-dev.sh'
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
