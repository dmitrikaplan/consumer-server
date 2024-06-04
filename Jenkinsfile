pipeline{
    agent any

    environment{
        DOCKER_IMAGE_NAME = "dmitrykaplan/consumer-server"
        dockerImage = ""
        TIMESTAMP = sh(script: 'date +%s', returnStdout: true).trim()
        KUBECONFIG = credentials('kube-config-path')
    }

    stages{
        stage("docker build"){
            steps{
               script {
                    dockerImage = docker.build dockerImage
                }
            }
        }

        stage("pushing docker image"){
            environment{
                registryCredential = 'dockerhub-credentials'
            }
            steps{
                script {
                    docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
                        dockerImage.push(TIMESTAMP)
                    }
                }
            }
        }

        stage("update configs"){
            steps{
                sh 'kubectl apply -f consumer-server.yaml'
            }
        }

        stage("deploying consumer-server deployment"){
            steps {
                script {
                    sh('KUBECONFIG=${KUBECONFIG}')
                    sh('kubectl set image deployments/consumer-server-deployment consumer-server=$DOCKER_IMAGE_NAME:$TIMESTAMP')
                }
            }
        }
    }
}