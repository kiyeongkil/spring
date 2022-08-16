pipeline {
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    DOCKER_IMAGE = 'kykil/spring'
  }

  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: gradle
            image: gradle:jdk11
            command:
            - cat
            tty: true
          - name: docker
            image: docker:latest
            command:
            - cat
            tty: true
            volumeMounts:
             - mountPath: /var/run/docker.sock
               name: docker-sock
          volumes:
          - name: docker-sock
            hostPath:
              path: /var/run/docker.sock
        '''
    }
  }
  stages {
    stage('Clone') {
      steps {
        container('gradle') {
          git branch: 'main', changelog: false, poll: false, url: 'https://github.com/kiyeongkil/spring'
        }
      }
    }
    stage('Build-Jar-file') {
      steps {
        container('gradle') {
          sh './gradlew clean build --exclude-task test'
        }
      }
    }
    stage('Build-Docker-Image') {
      steps {
        container('docker') {
          sh '''
            docker build -t ${DOCKER_IMAGE}:latest .
          '''
        }
      }
    }

    stage('Push-Docker-Image') {
      steps {
        container('docker') {
          sh '''
            echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
            docker push $DOCKER_IMAGE:latest
           '''
        }
      }
    }
  }
}g