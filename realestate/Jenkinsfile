pipeline {
    agent any
    tools {
         maven 'maven3'
    }
    stages{
        stage('Build'){
            steps{
                 cleanWs()
                 checkout scm
                echo "Building ${env.project1}..."
            }
        }
    }
}
