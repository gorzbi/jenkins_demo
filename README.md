pipeline {
    agent any
    
    parameters {
        choice choices: ['main', 'dev'], name: 'Branch'
        choice choices: ['RunAll', 'RunBug', 'RunFirst', 'RunFirstTwo'], name: 'Runner'
        }

    stages {
        stage('Weryfikacja folderu dla raportów') {
            steps {
                script {
                    // Sprawdź, czy katalog istnieje
                    if (fileExists('target/cucumber-html-reports')) {
                    echo 'Folder dla raportu istnieje!'
                    } else {
                    echo 'Folder dla raportu nie istnieje. Tworzę go!'
                    bat 'mkdir -p target/cucumber-html-reports'
                    }
                }
            }
        }
        stage('Start full automation') {
            steps {
                echo "Rozpoczynam automation pipeline"
            }
        }
        stage('Get repo') {
            steps {
                echo "Pobieram repo z git z brancha -> ${params.Branch}"
                // Get some code from a GitHub repository
                git branch: "${params.Branch}", credentialsId: 'github', url: 'https://github.com/gorzbi/jenkins_demo.git'
                echo "Repo pobrane z git"
            }
        }
        stage('Start tests') {
            steps {
                 echo "Uruchamiam testy: ${params.Runner}"
                // To run Maven on a Windows agent, use
               // bat "mvn -Dmaven.test.failure.ignore=true clean package"
               bat "mvn clean test -Dtest=${params.Runner}"
                 echo "Testy zakończone"
            }
        }
        stage('Get report') {
            steps {
                 echo "Tworzę raport"
                    publishHTML([
                        allowMissing: '', 
                        alwaysLinkToLastBuild: '', 
                        keepAll: '', 
                        reportDir: 'target/cucumber-html-reports', // taką ścieżkę bo Cucumber nie ma stylów i tak też w runner się podaje
                        reportFiles: 'index.html', // index bo testng generuje w target taką nazwę
                        reportName: 'Cucumber HTML Report',
                        useWrapperFileDirectly: false
                        ])
            
            }
        }
    }
}
