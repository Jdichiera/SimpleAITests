pipeline {
    agent any

    environment {
        ANDROID_SDK_ROOT = "android_sdk"
    }

    tools {
        gradle "Gradle 6.1"
    }

    stages {
        stage('Build') {
            steps {
                // Download Android SDK
                dir('android_sdk'){
                    sh "wget https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip"
                    sh "unzip -o ./sdk-tools-linux-4333796.zip"
                    sh "tools/bin/sdkmanager --update"
                    sh "tools/bin/sdkmanager --list"
                    sh "tools/bin/sdkmanager “platforms;android–27” “build-tools;27.0.3”"
                }


                // Set ANDROID_SDK_ROOT
                //env.ANDROID_SDK_ROOT = "./android_sdk"

                // Get some code from a GitHub repository
                git 'https://github.com/Jdichiera/SimpleAITests.git'

                // Run Maven on a Unix agent.
                sh "gradle build"
            }

            post {
                success {
                    sh "echo success!"
                }
            }
        }
    }
}