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
}
