node{
    ansiColor('xterm') {
    // some block
    }
    timestamps {
    // some block
    }
    stage('1 - Get code'){
        git credentialsId: 'kien_bit', url: 'https://github.com/KienTM23/maven-java-jenkin'
    }
    stage('2 - Get Compiler'){
        bat 'mvn clean'
    }
    stage('3 - Run Test'){
        bat 'mvn test'
    }
    stage('4 - Public report'){
       publishHTML([allowMissing: false,
       alwaysLinkToLastBuild: false, 
       keepAll: false, 
       reportDir: 'E:\\Automation Testing\\04-Maven Hybrid Framework\\maven-java-jenkin\target\\surefire-reports\\html',
       reportFiles: 'index.html', 
       reportName: 'ReportNG HTML', 
       reportTitles: '',
       useWrapperFileDirectly: true])
    }
}
