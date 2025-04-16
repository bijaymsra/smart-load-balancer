# 🚀 Smart Load Balancer for Microservices

An intelligent, containerized microservices-based project that uses Kubernetes to dynamically distribute traffic among services based on CPU usage, with real-time monitoring using Prometheus and Grafana.


## 🛠️ Tech Stack

- **Frontend**: React.js + Recharts
- **Backend**: Spring Boot (Java)
- **Containerization**: Docker
- **Orchestration**: Kubernetes (kind + Docker Desktop)
- **Monitoring**: Prometheus + Grafana
- **Load Balancing Strategy**: Kubernetes HPA (based on CPU > 75%)


## 📁 Project Structure

smart-load-balancer/ ├── smart-load-balancer/ │ ├── client/ # React frontend │ └── backend/ # Spring Boot backend ├── k8s/ # Kubernetes YAML configs (deployments, services, HPA, etc.) ├── services/ # Other microservices (dashboard)


## ⚙️ How It Works

1. **Kubernetes** manages multiple microservices.
2. **HPA (Horizontal Pod Autoscaler)** scales services based on CPU usage.
3. Traffic is routed intelligently to services with less load.
4. Metrics are scraped by **Prometheus**, visualized on **Grafana**.
5. The **React frontend** shows live stats from backend services.


## 🚀 Running the Project

### 1. Clone the Repo

git clone https://github.com/bijaymsra/smart-load-balancer.git
cd smart-load-balancer
2. Start Kubernetes Cluster
Make sure Docker Desktop is running with Kubernetes enabled.


kind create cluster --name smart-lb-cluster
3. Deploy Microservices
kubectl apply -f k8s/
This will deploy:

dashboard

Backend & client

HPA setup

4. Access Services
React Client: http://localhost:<NodePort>

Backend APIs: http://<backend-service-ip>:<port>

Grafana: http://localhost:3000
Prometheus: http://localhost:9090

📊 Monitoring
Grafana Dashboards: Real-time CPU, memory, and traffic usage.

Prometheus: Scrapes metrics from services and Kubernetes.

📦 Docker
All services are containerized.

# From each service directory
docker build -t service-name:latest .
📌 Features
Kubernetes-powered dynamic scaling

Load distribution based on real-time CPU metrics

Modern frontend with live charts

Fully containerized microservices

Ready for cloud deployment

✍️ Author
Bijay M S R A
GitHub

📜 License
This project is licensed under the MIT License.


Let me know if you'd like to add:
- Architecture diagram
- Screenshots of the dashboard
- Project demo video link (YouTube or Loom)
- Contribution or deployment instructions (for cloud platforms like GCP/AWS)
