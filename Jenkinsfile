pipeline {
    agent any
    stages {        
         stage('clone the project') {
                              steps {
                                git branch: 'bilel', credentialsId: 'conn', url: 'https://github.com/ouniahmed/Devops-backend.git'
                              }
                }
          stage('create package') {
                                steps {
                                       sh "mvn package -Dmaven.test.skip"
                                      }
                                }
           stage('Start Tests') {
                                 steps {
                                       sh "mvn test"
                                          }
                               }
        
                    stage('Quality Test Sonar') {
                                 steps {
                                       sh "mvn sonar:sonar  -Dsonar.projectKey=back-end -Dsonar.host.url=http://127.0.0.1:9000 -Dsonar.login=$AUTHTOKEN"
                                          }
                               }
                stage('Deploy Artifact') {
                                 steps {
                                       sh "mvn deploy -DskipTests -Dmaven.install.skip=true"
                                          }
                               }

                stage('Build Image') {
                                 steps {
                                       sh "whoami"
                                       sh """docker build -t back-end-server ."""
                                          }
                              }
                stage('Push Image to DockerHUB'){
                                 steps {
                                       sh "docker push  zraier/back-end-server:beta"
                                          }
                              }


                stage('Copying prometheus configuration'){
                        steps {
                            sh "cp env/prometheus.yaml prometheus/"
                        }
    }
                    stage('Starting the application'){
                                steps {
                                    sh "docker-compose up -d"
                                }
                    }
    }

}
