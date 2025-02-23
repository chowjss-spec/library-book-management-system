pipeline {
    agent any
    
    environment {
        SONARQUBE = 'Default'
        GIT_CREDENTIALS = credentials('github-token')  // Reference the stored credentials by ID
    }
    tools {
        maven 'Default'
    }

    stages {
        stage('Setup') {
            steps {
                script {
                    sh 'cd library-book-management-system && ls && mvn clean install -DskipTests && docker-compose up -d'
                    sh '''
                    until curl -s http://host.docker.internal:9000/LibraryBookManagementSystem/Home; do
                      echo "Waiting for the container to be ready..."
                      sleep 5
                    done
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'cd library-book-management-system && ls && mvn test'
                }
            }
        }
        stage('Teardown'){
            steps {
                script {
                    sh 'cd library-book-management-system && docker-compose down'
                }
            }
        }
        stage('Sonarqube') {
            steps{
                script{
                    withSonarQubeEnv(SONARQUBE) {
                        sh 'cd library-book-management-system && ls && mvn sonar:sonar -Dsonar.projectKey=LibraryBookManagementSystem -Dsonar.host.url=http://host.docker.internal:9090 -Dsonar.login=sqp_a87d735a63ce96075e0ac0dc39c3c7baaeb6e330'
                    }
                }
            }
        }
        stage('Deploy') {
            steps{
                script{
                    withAWS(region:'ap-southeast-1',credentials:'AwsCredentials') {
                        sh 'cd library-book-management-system/script && chmod +x deploy.sh && ./deploy.sh'
                    }
                }
            }
        }
    }
}