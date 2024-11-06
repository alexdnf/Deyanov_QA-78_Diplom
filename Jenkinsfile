pipeline {
    agent any
    
    stages {
        stage('Setup Environment') {
            steps {
                script {
                    sh '''
                      sudo docker-compose up -d
                      sleep 20
                      java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar &
                      sleep 60
                      chmod +x gradlew
                    '''
                }
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    sh './gradlew "-Ddb.url=jdbc:mysql://localhost:3306/app" test --info -Dselenide.headless=true'
                }
            }
        }
    }
}
