pipeline {
    agent any

    stages {
         stage('Build') {
            agent any
            steps {
                echo 'Building..'
                echo "checkout from git"
                git([url: 'git://example.com/amazing-project.git', branch: 'master'])
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
