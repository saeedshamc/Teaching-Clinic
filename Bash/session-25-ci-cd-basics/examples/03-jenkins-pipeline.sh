#!/usr/bin/env bash

echo "=== Jenkins Pipeline ==="

# ایجاد Jenkinsfile
cat > /tmp/jenkins-test/Jenkinsfile << 'EOF'
pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'make build'
            }
        }
        
        stage('Test') {
            steps {
                sh 'make test'
            }
        }
        
        stage('Deploy') {
            steps {
                sh 'make deploy'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline completed'
        }
    }
}
EOF

echo "Jenkinsfile ایجاد شد"
cat /tmp/jenkins-test/Jenkinsfile

# پاکسازی
rm -rf /tmp/jenkins-test
