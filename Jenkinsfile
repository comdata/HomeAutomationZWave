pipeline {
    agent {
        docker {
            image 'maven:3.5.4-jdk-8-alpine' 
            args '-v /root/.ssh:/root/.ssh -v $HOME/.m2:/root/.m2' 
        }
    }
    triggers { upstream(upstreamProjects: 'zwave', threshold: hudson.model.Result.SUCCESS) }
    stages {
	stage('Prepare') {
	    steps {
		sh 'apk update'
		sh 'apk add rsync openssh openrc git'
//		sh 'rm -rf obera-base zwave'
//		sh 'git clone https://github.com/oberasoftware/obera-base.git'
//		sh 'cd obera-base && mvn -DskipTests install'
//		sh 'git clone https://github.com/comdata/zwave.git'
//		sh 'cd zwave && mvn -DskipTests install'
	    } 
	}

 
           
		 stage('Build Backend') {
			steps {
				withMaven() {
				 

				sh '$MVN_CMD -B clean deploy'
				   
				}
            }
		
	   
        }
    }
}
