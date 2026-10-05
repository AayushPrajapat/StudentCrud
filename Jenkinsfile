pipeline {
    agent any

    tools {
        jdk 'JDK21'
        nodejs 'NodeJS20'
    }

    environment {
        COMPOSE_PROJECT_NAME = 'studentcrud'
    }

    stages {
        stage('Clone') {
            steps {
                checkout scm
            }
        }

        stage('Build - Maven (Backend)') {
            steps {
                dir('Backend/StudentDetails') {
                    bat 'mvnw.cmd clean package -DskipTests'
                }
            }
        }

        stage('Build - NPM Install (Frontend)') {
            steps {
                dir('Frontend/my-app') {
                    bat 'npm ci'
                }
            }
        }

        stage('Build - NPM Build (Frontend)') {
            steps {
                dir('Frontend/my-app') {
                    bat 'npm run build -- --configuration production'
                }
            }
        }

        stage('Docker - Verify') {
            steps {
                bat 'docker version'
                bat 'docker compose config'
            }
        }

        stage('Image Build') {
            steps {
                bat 'docker compose build'
            }
        }

        stage('Container Run') {
            steps {
                bat 'docker compose down'
                bat 'docker compose up -d'
                bat 'docker compose ps'
            }
        }
    }

    post {
        success {
            echo 'Pipeline successful ✅'
            echo 'Frontend: http://localhost:4200 | Backend: http://localhost:8085'
        }
        failure {
            echo 'Pipeline failed ❌'
            bat 'docker compose logs --tail=50'
        }
    }
}