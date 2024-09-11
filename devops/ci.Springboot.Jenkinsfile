pipeline {
    agent {
        kubernetes {
            yamlFile 'devops/ci-springboot-kubernetes-pod.yml'
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
                    sh '$MVN clean package -f  $BASE_POM_PATH'
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
                        env.GIT_TAG_PUSH = tagVersion
                    }
                }
                updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
            }
        }

        stage("Git Tag") {
            steps {
                updateGitlabCommitStatus name: STAGE_NAME, state: 'running'
                container('jnlp') {
                    script {
                        def tagVersion = env.GIT_TAG_PUSH
                        def repositoryURL = scm.userRemoteConfigs[0].url  
                        env.REPOSITORY_URL = repositoryURL.replaceAll("https://", "https://${env.USUARIO_AD}:${env.USUARIO_TOKEN}@")
                        sh './devops/git-tag.sh'
                    }
                }
                updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
            }
        }
        stage("Sonar Scann") {
            steps {
                updateGitlabCommitStatus name: STAGE_NAME, state: 'running'
                container('sonar-scanner-cli') {
                    script {
                        sh '''
                            ./devops/sonar-scan.sh > sonar-output.log 2>&1
                        '''
                    }
                }
                updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
            }
        }
    }
}
