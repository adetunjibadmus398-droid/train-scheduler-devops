pipeline {

    agent any

    environment {

        AWS_REGION = "us-east-1"
        ECR_REPO = "train-scheduler"
        S3_BUCKET = "train-scheduler-artifacts"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/company/train-scheduler-devops.git'
            }
        }

        stage('Build Java Artifact') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Upload Artifact to S3') {
            steps {
                sh '''
                aws s3 cp target/train-scheduler.jar \
                s3://$S3_BUCKET/train-scheduler.jar
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t train-scheduler .
                '''
            }
        }

        stage('Login to ECR') {
            steps {
                sh '''
                aws ecr get-login-password \
                --region $AWS_REGION | docker login \
                --username AWS \
                --password-stdin \
                123456789.dkr.ecr.$AWS_REGION.amazonaws.com
                '''
            }
        }

        stage('Push Image to ECR') {
            steps {
                sh '''
                docker tag train-scheduler:latest \
                123456789.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO:latest

                docker push \
                123456789.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO:latest
                '''
            }
        }

        stage('Deploy to EKS') {
            steps {
                sh '''
                kubectl apply -f k8s/deployment.yaml
                kubectl apply -f k8s/service.yaml
                '''
            }
        }

    }

}
