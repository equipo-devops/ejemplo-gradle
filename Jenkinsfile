pipeline {
    agent any
    options {
      timeout(time: 120, unit: 'SECONDS') 
    }
    stages {
        stage('Pipeline') {
            steps {
                script{
                    if (params.Eleccion == 'maven') {
                        echo "ejecución maven"
                        def ejecucion_maven = load 'maven.groovy'
                        ejecucion_maven.call()
                    } else {
                        echo "ejecución gradle"
                        def ejecucion_gradle = load 'gradle.groovy'
                        ejecucion_gradle.call()
                    }
                }
             }
        }
}
post {
        success {
           // slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '[${USER}] [${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
              slackSend (color: '#00FF00', message: "Build Success: '[${git.USER}] [${env.JOB_NAME}] [${params.Eleccion}]' Ejecución exitosa. ")
             
        }
        failure {
           slackSend (color: '#FF0000', message: "FAILED: Job '${USER} ${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
    }
}
