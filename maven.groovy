/*

	forma de invocación de método call:

	def ejecucion = load 'script.groovy'
	ejecucion.call()

*/

def call(){
  
  script{
    //Escribir directamente el código del stage, sin agregarle otra clausula de Jenkins.
            stage('Compile') {
          
              sh './mvnw clean compile -e'
          
            
        }
        stage('Unit') {
            
             
                    sh './mvnw clean test -e'
            
            
        }
        stage('Jar') {
          
                  
                    sh './mvnw clean package -e'
            
            
        }

          stage('SonarQube') {
          	
    			withSonarQubeEnv('Sonar') { // You can override the credential to be used
      			sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    				}
			   
  		}

      stage('Nexus Upload'){
                    steps {
                        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus',
                         packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '',
                          extension: 'jar', filePath: 'build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020',
                           groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.2']]]
                        }
                }


        stage('Run') {
            
            
                    sh 'nohup bash mvnw spring-boot:run &'
                    
              
             
               
            
        }
         stage('Test') {
            
                sleep 20
                sh 'curl http://localhost:8081/rest/mscovid/test?msg=testing'
            
        } 
        
       stage('Stop') {
            
                    sh 'bash mvnw spring-boot:stop &'
                    
                 
            
        }
  }

}

return this;
