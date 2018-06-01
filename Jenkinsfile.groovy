node {
    def mavenHome = tool "Maven"
    stage ('clone') {
        checkout scm
    }
    stage ('unit test') {
        sh 'mvn clean test'
        sh 'ls -l target'
        junit 'target/surefire-reports/**/*.xml'
        def betty = "it417"
        echo 'ciao $betty'
        echo "ciao $betty"
    }
    stage ('integration test') {
        echo 'Ciao! Adesso dovrei lanciare i test di integrazione'
    }
    stage ('build artefact') {
        sh 'mvn package'
        archiveArtifacts artifacts: 'target/GooseGame-1.0-SNAPSHOT-jar-with-dependencies.jar', fingerprint: true
    }
}
