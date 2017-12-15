pipeline {
    agent {
        docker {
            image 'maven:3.5.2-jdk-8-alpine' 
            args '-v /var/jenkins_home/.m2:/root/.m2' 
        }
    }
    stages {
	stage('Prepare') {
	    steps {
		sh 'apk update'
		sh 'apk add rsync openssh openrc'
	    } 
	}

        stage('Build') { 
            parallel {
		 stage('Build Backend') {
			steps {
				sh 'mvn -B clean install'
            		}
		}
	    }
        }

        stage('Deploy') {
            steps {
		echo 'do nothing'
	    }
        }

    }
}
