pipeline {
    agent any

    stages {
         stage('Build') {
            agent any
            steps {
                echo 'Building..'
                echo "checkout from git"
                git([url: 'https://github.com/HiranmayiChitta/servicesrepository.git', branch: 'master'])
                bat 'mvn clean install'
                stash includes: '**/target/springdemo.jar', name: 'springdemo' 
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
