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
                    	    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: 'localhost:8081',
                        groupId: 'com.devopsusach2020',
                        version: '0.0.1',
                        repository: 'test-nexus',
                        credentialsId: 'credencial_nexus',
                        artifacts: [
                            [artifactId: 'DevOpsUsach2020',
                            classifier: '',
                            file: 'build/libs/DevOpsUsach2020-1.0.2.jar',
                            type: 'jar']
                        ]
                        )
                        
                    }
                }
            }
        }
    }
}
