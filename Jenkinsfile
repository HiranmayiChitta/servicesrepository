pipeline
 {
  agent any
   environment {
        PATH = "C:\\WINDOWS\\SYSTEM32"
         CATALINA_HOME="C:\\apache-tomcat-8.5.98"
    }
  tools
   {
    maven 'Maven'
    jdk 'JDK17'
   }

  options
   {
    buildDiscarder(logRotator(numToKeepStr: '4'))
    skipStagesAfterUnstable()
    disableConcurrentBuilds()
   }


  triggers
   {
    // MINUTE HOUR DOM MONTH DOW
    pollSCM('H 6-18/4 * * 1-5')
   }


  stages
   {
    stage('Clean')
     {
      steps
       {
        script
         {
          if (isUnix()) 
           {
            sh 'mvn --batch-mode clean'
           }
          else
           {
            bat 'mvn --batch-mode clean'
           }
         }
       }
     }

    stage('Build')
     {
      steps
       {
        script
         {
          if (isUnix()) 
           {
            sh 'mvn --batch-mode compile'
           }
          else
           {
            bat 'mvn --batch-mode compile'
           }
         }
       }
     }

    stage('UnitTests')
     {
      steps
       {
        script
         {
          if (isUnix()) 
           {
            sh 'mvn --batch-mode resources:testResources compiler:testCompile surefire:test'
           }
          else
           {
            bat 'mvn --batch-mode resources:testResources compiler:testCompile surefire:test'
           }
         }
       }
      
     }

    stage('Sanity check')
     {
      steps
       {
        script
         {
          if (isUnix()) 
           {
            sh 'mvn --batch-mode checkstyle:checkstyle pmd:pmd pmd:cpd com.github.spotbugs:spotbugs-maven-plugin:spotbugs'
           }
          else
           {
            bat 'mvn --batch-mode checkstyle:checkstyle pmd:pmd pmd:cpd com.github.spotbugs:spotbugs-maven-plugin:spotbugs'
           }
         }
       }
     }

    stage('Packaging')
     {
      steps
       {
        script
         {
          if (isUnix()) 
           {
            sh 'mvn --batch-mode jar:jar'
           }
          else
           {
            bat 'mvn --batch-mode jar:jar'
           }
         }
       }
     }

    stage('install local')
     {
      steps
       {
        script
         {
          if (isUnix()) 
           {
            sh 'mvn --batch-mode jar:jar source:jar install:install'
           }
          else
           {
            bat 'mvn --batch-mode jar:jar source:jar install:install' // maven-jar-plugin falseCreation default is false, so no doubled jar construction here, but required for maven-install-plugin internal data
           }
         }
       }
     }

    stage('Documentation')
     {
      steps
       {
        script
         {
          if (isUnix()) 
           {
            sh 'mvn --batch-mode site'
           }
          else
           {
            bat 'mvn --batch-mode site'
           }
         }
       }
   
     }

    stage('Deploy test')
     {
      steps
       {      
        script
         {
          if (isUnix()) 
           {
            // todo
           }
          else
           {
            bat returnStatus: true, script: 'C:\\apache-tomcat-8.5.98\\bin\\shutdown.bat'
            sleep(time:30, unit:"SECONDS")
            bat returnStatus: true, script: 'C:\\scripts\\clean.bat'
            bat returnStatus: true, script: 'robocopy "target" "C:\\apache-tomcat-8.5.98\\webapps" springdemo.war'
            bat 'C:\\apache-tomcat-8.5.98\\bin\\startup.bat'
            sleep(time:30, unit:"SECONDS")
           }
         }
       }
     }

    stage('Integration tests')
     {
      steps
       {
        script
         {
          if (isUnix()) 
           {
            sh 'mvn --batch-mode failsafe:integration-test failsafe:verify'
           }
          else
           {
            bat 'mvn --batch-mode failsafe:integration-test failsafe:verify'
           }
         }
       }
     }

   }

 }
