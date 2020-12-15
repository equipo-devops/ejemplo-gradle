pipeline {
    agent any
    options {
      timeout(time: 120, unit: 'SECONDS') 
    }
    stages {
        stage('Pipeline') {
            steps {
                script {
                    stage('build & test') {
                          sh  "gradle clean build"
                    }
                    stage('sonar') {
                        
                    }
                    stage('run') {
                        
                    }
                    stage('nexus') {
                        
                    }
                }
            }
        }
    }
}
