pipeline {
    agent any
    tools {
        maven 'maven3'
        jdk 'jdk8'
    }
    stages {
         stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                ''' 
            }
        }
        stage('Install') {
            steps {

                sh "mvn clean test"
            }
        }
    }
}
