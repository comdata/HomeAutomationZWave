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
		sh 'apk add rsync openssh openrc git'
		sh 'rm -rf obera-base zwave'
		sh 'git clone https://github.com/oberasoftware/obera-base.git'
		sh 'cd obera-base && mvn -DskipTests install'
		sh 'git clone https://github.com/comdata/zwave.git'
		sh 'cd zwave && mvn -DskipTests install'
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
		sh 'mvn deploy:deploy-file -Dfile=target/HomeAutomationZWave-0.0.1-SNAPSHOT.jar -DpomFile=pom.xml -DrepositoryId=archiva.snapshots -Durl=http://192.168.1.36:8080/repository/snapshots'
	    }
        }

    }
}
