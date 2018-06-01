node {
    def mavenHome = tool "Maven"
    stage ('clone') {
        checkout scm
    }
    stage ('unit test') {
        sh 'mvn clean test'
    }
    stage ('integration test') {
        echo 'Ciao! Adesso dovrei lanciare i test di integrazione'
    }
}
