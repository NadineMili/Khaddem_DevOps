pipeline {
    agent any

    stages {
        stage("Git Clone") {
            steps {
                script {
                    credentialsId: 'GIT_HUB_CREDENTIALS',
                    url: 'https://github.com/NadineMili/Khaddem_DevOps.git'
                }
            }
        }
        stage('Nettoyage du projet') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('MVN Package') {
            steps {
                sh "mvn package -DskipTests=true"
            }
        }
        
        stage("Publish to Nexus") {
            steps {
                sh 'mvn deploy'
            }
        }
        stage('SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=e9085ee87c56742400195eb9b4268923f7143138'
            }
        }
        stage("Docker build") {
            steps {
                sh 'docker version'
                sh 'docker build -t nadinemilli/5sae2-g4-khaddem .'
                sh 'docker image list'
                sh 'docker tag nadinemilli/5sae2-g4-khaddem nadinemilli/5sae2-g4-khaddem:latest'
            }
        }
        stage("Docker Login") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DOCKER', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                }
            }
        }
         stage("Push Image to Docker Hub") {
            steps {
                sh 'docker push nadinemilli/5sae2-g4-khaddem:latest'
            }
        }
        
       // stage("Pull Image from Docker Hub") {
         //   steps {
           //     sh 'docker pull nadinemilli/5sae2-g4-khaddem:latest'
          //  }
        //}
    }

    post {
        success {
            emailext (
                subject: 'Construction réussie',
                body: 'La construction du projet a réussi. Tout est en ordre!',
                to: 'mili.nadine07@gmail.com'
            )
        }
        failure {
            emailext (
                subject: 'Construction échouée',
                body: 'La construction du projet a échoué. Veuillez vérifier les logs.',
                to: 'mili.nadine07@gmail.com'
            )
        }
    }
}
