pipeline {
    agent any

    stages {
        stage('Git') {
            steps {
                git branch: 'master', url: 'https://github.com/donia102000/paris_events_back.git'
            }
        }

        stage('Maven compile') {
            steps {
                echo 'Compilation du projet';
                sh 'mvn compile';
            }
        }

        stage('Maven Package') {
    steps {
        echo 'Packaging the project'
        sh ' mvn package -DskipTests'
}
        }


      stage('Build Docker Image') {
    steps {
        script {
            echo "Building Docker image"
            withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                // Construction de l'image Docker
                sh """
                    docker build --no-cache -t ${DOCKER_USERNAME}/paris_event_project:5.0.0 .
                """
            }
        }
    }
}

stage('Docker Push') {
    steps {
        script {
            echo "Pushing Docker image to Docker Hub"
            withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                // Authentification avec Docker Hub et Push de l'image
                sh """
                    echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin
                    docker push ${DOCKER_USERNAME}/paris_event_project:5.0.0
                """
            }
        }
    }
}


   stage('docker compose ') {
            steps {
                echo 'docker compose up ';
                sh 'docker compose up -d '
            }
        }
    }








}

