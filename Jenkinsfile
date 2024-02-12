pipeline {
    agent any

    stages {
         stage('Build') {
            agent any
            steps {
                echo 'Building..'
                checkout scm
                sh 'make'
                stash includes: '**/target/*.jar', name: 'springdemo' 
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
