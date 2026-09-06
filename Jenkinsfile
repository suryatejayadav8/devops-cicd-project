pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Downloading project from GitHub'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building application'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests'
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'CI Pipeline completed successfully!'
        }

        failure {
            echo 'CI Pipeline failed!'
        }
    }
}
