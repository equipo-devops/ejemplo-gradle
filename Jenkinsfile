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
                           def scannerHome = tool 'sonar-scanner'; // scanner
                        withSonarQubeEnv('Sonar') { // server
                            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build " 
                        }
                    
                    }
                    stage('run') {

                         sh "nohup bash gradle bootRun &"
                        
                    }
                      stage('rest') {
                        sleep(time: 10, unit: 'SECONDS')
                        sh 'curl -X GET "http://localhost:8081/rest/mscovid/test?msg=testing"'
                    }
                    stage('nexus') {
                        
                    }
                }
            }
        }
    }
}
