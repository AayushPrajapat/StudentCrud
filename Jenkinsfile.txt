pipeline {
    agent any

   tools {
    jdk 'JDK21'
    nodejs 'NodeJS24'
}

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Backend - Build & Test') {
            steps {
                dir('Backend/StudentDetails') {
                    bat 'mvnw.cmd clean package'
                }
            }
        }

        stage('Frontend - Install') {
            steps {
                dir('Frontend/my-app') {
                    bat 'npm ci'
                }
            }
        }

        stage('Frontend - Build') {
            steps {
                dir('Frontend/my-app') {
                    bat 'npm run build -- --configuration production'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'Backend/StudentDetails/target/*.jar', fingerprint: true
                archiveArtifacts artifacts: 'Frontend/my-app/dist/**', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                // Backend jar ko deploy folder me copy
                bat 'if not exist C:\\deploy\\backend mkdir C:\\deploy\\backend'
                bat 'copy /Y Backend\\StudentDetails\\target\\*.jar C:\\deploy\\backend\\'

                // Angular build ko copy (nginx/IIS/any web server folder)
                bat 'if not exist C:\\deploy\\frontend mkdir C:\\deploy\\frontend'
                bat 'xcopy /E /Y /I Frontend\\my-app\\dist\\* C:\\deploy\\frontend\\'
            }
        }
    }

    post {
        success { echo 'Pipeline successful ✅' }
        failure { echo 'Pipeline failed ❌' }
    }
}