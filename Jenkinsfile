pipeline {
     agent any
     stage("Checkout"){
         checkout scm
     }

    stage("Build"){
         sh "./gradlew clean build -x test"
    }

    stage("Test") {
        sh "./gradlew test --stacktrace --info "
        step([$class: "JUnitResultArchiver", testResults: "**/build/test-results/test/TEST-*.xml"])
    }

    stage("Deploy") {
        sh "BUILD_ID=hack java -Dspring.profiles.active=prod -jar build/libs/digital-menu-0.0.1-SNAPSHOT.jar&"
    }
}