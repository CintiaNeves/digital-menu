pipeline {
     agent any
     stages {
         stage("Checkout") {
            steps {
                checkout scm
            }
         }

        stage("Build") {
             steps {
                sh "./gradlew clean build -x test"
            }
        }

        stage("Test") {
            steps {
                sh "./gradlew test --stacktrace --info "
            }
        }

        stage("Deploy") {
            steps {
                sh "nohup ./gradlew bootRun &"
            }
        }
    }
}