pipeline {
    agent any

    stages {
         stage('Build') {
            agent any
            steps {
                echo 'Building..'
                echo "checkout from git"
                git([url: 'https://github.com/HiranmayiChitta/servicesrepository.git', branch: 'master'])
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
             when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
            steps {
                echo 'Deploying..'
                sh 'make publish'
            }
        }
    }
}
