# DevOps CI/CD Project

## Project Overview

This project demonstrates an automated end-to-end DevOps CI/CD pipeline.

The application is developed using Spring Boot and automatically built,
tested, containerized, scanned, pushed to Docker Hub, and deployed to
Kubernetes using Jenkins.

---

## Technologies Used

- Java 17
- Spring Boot
- Maven
- Git
- GitHub
- Jenkins
- Docker
- Docker Hub
- Trivy
- Kubernetes
- Minikube

---

## CI/CD Architecture

```text
Developer
    |
    | git push
    v
GitHub
    |
    v
Jenkins
    |
    +---- Maven Build
    |
    +---- Unit Tests
    |
    +---- Docker Build
    |
    +---- Trivy Security Scan
    |
    +---- Docker Hub
    |
    v
Kubernetes
    |
    +---- Deployment
    |
    +---- Service
    |
    +---- 3 Pods
    |
    v
Spring Boot Application
```

---

## Project Structure

```text
devops-cicd-project/
│
├── src/
│   ├── main/java/com/devops/app/
│   │   ├── DevopsApplication.java
│   │   └── HomeController.java
│   │
│   └── test/java/com/devops/app/
│       └── DevopsApplicationTests.java
│
├── kubernetes/
│   ├── deployment.yaml
│   └── service.yaml
│
├── Dockerfile
├── Jenkinsfile
├── pom.xml
├── README.md
└── .gitignore
```

---

## Run Application Locally

Build:

```bash
mvn clean package
```

Run:

```bash
java -jar target/devops-cicd-project-0.0.1-SNAPSHOT.jar
```

Open:

```text
http://localhost:8080
```

Health endpoint:

```text
http://localhost:8080/health
```

Project information:

```text
http://localhost:8080/info
```

---

## Run Tests

```bash
mvn test
```

---

## Docker Build

```bash
docker build -t devops-cicd-project:latest .
```

Run:

```bash
docker run -d \
-p 8080:8080 \
--name devops-app \
devops-cicd-project:latest
```

Check:

```bash
docker ps
```

Open:

```text
http://localhost:8080
```

---

## Kubernetes

Start Minikube:

```bash
minikube start
```

Check cluster:

```bash
kubectl get nodes
```

Deploy:

```bash
kubectl apply -f kubernetes/deployment.yaml
```

Create service:

```bash
kubectl apply -f kubernetes/service.yaml
```

Check pods:

```bash
kubectl get pods
```

Check deployments:

```bash
kubectl get deployments
```

Check service:

```bash
kubectl get services
```

Open application:

```bash
minikube service devops-cicd-project
```

---

## Jenkins CI/CD Pipeline

Whenever a developer pushes code to GitHub:

```text
Git Push
   ↓
GitHub
   ↓
Jenkins
   ↓
Maven Build
   ↓
Unit Testing
   ↓
Docker Build
   ↓
Trivy Scan
   ↓
Docker Hub
   ↓
Kubernetes Deployment
   ↓
Application Updated
```

---

## Security Scanning

Trivy scans the Docker image for:

- Critical vulnerabilities
- High vulnerabilities
- Operating system vulnerabilities
- Package vulnerabilities

Example:

```bash
trivy image devops-cicd-project:latest
```

---

## Kubernetes Scaling

The application runs with three replicas.

Check:

```bash
kubectl get pods
```

Example:

```text
devops-cicd-project-xxxxx-aaa   1/1   Running
devops-cicd-project-xxxxx-bbb   1/1   Running
devops-cicd-project-xxxxx-ccc   1/1   Running
```

Scale to five:

```bash
kubectl scale deployment devops-cicd-project --replicas=5
```

---

## Developer

Bommena Surya Teja

DevOps CI/CD Project