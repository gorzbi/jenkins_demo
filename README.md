ładny raport cucumber w jenkins:
-> w intellij dla każdego Runner tam gdzie "pretty" i ścieżka do html dodać ścieżkę dla raportu json -> json:target/cucumber.json
-> ta ścieżka potrzebna jest dla Jenkins
-> w intellij cucumber generuje raport do target/ bez css i js dlatego trzeba odpowiednio sformatować w Jenkins
-> w Jenkins zainstalować wtyczkę Cucumber report

// konfiguracja raportu cucumber dla jenkins:
https://www.jenkins.io/doc/pipeline/steps/cucumber-reports/

pipeline {
    agent any
    
    parameters {
    // nazwy branch takie jak w github
        choice choices: ['main', 'dev'], name: 'Branch'
        // nazwy runnerów jak w framework'u
        choice choices: ['RunAll', 'RunBug', 'RunFirst', 'RunFirstTwo'], name: 'Runner'
        }

    stages {
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
        stage('Get Cucumber report') {
            steps {
                 echo "Tworzę raport Cucumber"
                 cucumber buildStatus: 'UNCHANGED', 
                 customCssFiles: '', 
                 customJsFiles: '', 
                 failedFeaturesNumber: -1, 
                 failedScenariosNumber: -1, 
                 failedStepsNumber: -1, 
                 fileIncludePattern: '**/*.json', 
                 pendingStepsNumber: -1, 
                 skippedStepsNumber: -1, 
                 sortingMethod: 'ALPHABETICAL', 
                 undefinedStepsNumber: -1
            }
        }
    }
}
