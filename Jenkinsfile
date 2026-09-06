pipeline {

    agent any


    environment {

        DOCKER_IMAGE =
            'YOUR_DOCKERHUB_USERNAME/devops-cicd-project'

        IMAGE_TAG =
            "${BUILD_NUMBER}"

    }


    stages {


        stage('Checkout') {

            steps {

                echo 'Cloning source code from GitHub...'

                checkout scm

            }

        }


        stage('Build') {

            steps {

                echo 'Building application with Maven...'

                bat 'mvn clean package -DskipTests'

            }

        }


        stage('Test') {

            steps {

                echo 'Running unit tests...'

                bat 'mvn test'

            }

        }


        stage('Docker Build') {

            steps {

                echo 'Building Docker image...'

                bat """
                docker build -t %DOCKER_IMAGE%:%IMAGE_TAG% .
                """

            }

        }


        stage('Trivy Security Scan') {

            steps {

                echo 'Scanning Docker image using Trivy...'

                bat """
                trivy image --severity HIGH,CRITICAL --exit-code 0 %DOCKER_IMAGE%:%IMAGE_TAG%
                """

            }

        }


        stage('Docker Login') {

            steps {

                echo 'Logging into Docker Hub...'


                withCredentials([

                    usernamePassword(

                        credentialsId: 'dockerhub',

                        usernameVariable: 'DOCKER_USERNAME',

                        passwordVariable: 'DOCKER_PASSWORD'

                    )

                ]) {


                    bat """

                    echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin

                    """

                }

            }

        }


        stage('Docker Push') {

            steps {

                echo 'Pushing Docker image to Docker Hub...'


                bat """

                docker push %DOCKER_IMAGE%:%IMAGE_TAG%

                docker tag %DOCKER_IMAGE%:%IMAGE_TAG% %DOCKER_IMAGE%:latest

                docker push %DOCKER_IMAGE%:latest

                """

            }

        }


        stage('Deploy to Kubernetes') {

            steps {

                echo 'Deploying application to Kubernetes...'


                bat """

                kubectl apply -f kubernetes/deployment.yaml

                kubectl apply -f kubernetes/service.yaml

                kubectl set image deployment/devops-cicd-project devops-cicd-project=%DOCKER_IMAGE%:%IMAGE_TAG%

                """

            }

        }


        stage('Verify Deployment') {

            steps {

                echo 'Checking Kubernetes deployment...'


                bat """

                kubectl rollout status deployment/devops-cicd-project --timeout=120s

                kubectl get deployments

                kubectl get pods

                kubectl get services

                """

            }

        }

    }


    post {


        success {

            echo '======================================'

            echo 'CI/CD PIPELINE SUCCESSFUL'

            echo 'Application deployed successfully!'

            echo '======================================'

        }


        failure {

            echo '======================================'

            echo 'CI/CD PIPELINE FAILED'

            echo 'Check Jenkins Console Output.'

            echo '======================================'

        }


        always {

            echo 'Pipeline execution completed.'

        }

    }

}