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
                sh "BUILD_ID='hack' java -Dspring.profiles.active=prod -jar build/libs/digital-menu-0.0.1-SNAPSHOT.jar&"
            }
        }
    }
}