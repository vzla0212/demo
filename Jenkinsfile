pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './mvnw -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('SonarQube analysis') {
            withSonarQubeEnv() { // You can override the credential to be used
              sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }
          }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}