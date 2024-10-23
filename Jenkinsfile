pipeline {
    agent any
    
    stages {
        stage('Setup Environment') {
            steps {
                script {
                    sh '''
                      sudo curl -L "https://github.com/docker/compose/releases/download/v2.12.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
                      sudo chmod +x /usr/local/bin/docker-compose
                      sudo docker-compose up -d
                      sleep 60
                      java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar &
                      sleep 60
                      chmod +x gradlew
                    '''
                }
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    sh 'java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar'
                    sh './gradlew "-Ddb.url=jdbc:postgresql://localhost:5432/app" test --info -Dselenide.headless=true'
                }
            }
        }
    }
}
