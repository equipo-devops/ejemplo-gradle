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
