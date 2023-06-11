pipeline{
    agent any
   stages{
       
      stage("build"){
          steps{
               echo("Build the project")
          }
         } 
       
       stage("Run UTs"){
          steps{
               echo("Run the Unit Tests")
          }
        } 
       stage("Deploy to dev"){
          steps{
               echo("deploying to dev env")
          }
               } 
       stage("Deploy to qa"){
          steps{
               echo("deploying to qa env")
          }
               } 
               
                stage("Run the automated test cases"){
          steps{
          
                   echo("Run regression automated test cases")
          }
                } 
       
       stage("Deploy to stage"){
          steps{
          
                   echo("deploying to stage env")
          }
       }
       
        stage("Deploy to prod"){
          steps{
          
                   echo("deploying to prod env")
          }
        }
       
       
   }
    
  }