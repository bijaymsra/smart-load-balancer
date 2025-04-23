# 🚀 Smart Load Balancer for Microservices

An intelligent, containerized microservices-based system that utilizes **Kubernetes HPA** to dynamically distribute traffic based on **real-time CPU usage**, while offering **live monitoring dashboards** using **Prometheus** and **Grafana**.

---

## 🛠️ Tech Stack

| Layer        | Technology                         |
|--------------|-------------------------------------|
| Frontend     | React.js + Recharts                |
| Backend      | Spring Boot (Java)                 |
| Containerization | Docker                          |
| Orchestration | Kubernetes (kind + Docker Desktop) |
| Monitoring   | Prometheus + Grafana               |
| Load Balancing | Kubernetes HPA (CPU > 75%)       |

---

## 📁 Project Structure

```
smart-load-balancer/
├── client/        # React frontend
├── backend/       # Spring Boot backend
├── services/      # Additional microservices (e.g., dashboard)
├── k8s/           # Kubernetes YAMLs (Deployment, Services, HPA, etc.)
└── README.md
```

---

## ⚙️ How It Works

1. **Kubernetes** orchestrates all services and manages scaling.
2. **Horizontal Pod Autoscaler (HPA)** dynamically scales pods based on CPU thresholds (>75%).
3. **Docker** containers wrap each service for consistency.
4. **Prometheus** scrapes CPU/memory metrics from pods.
5. **Grafana** visualizes real-time data using customizable dashboards.
6. **React frontend** displays a live dashboard with charts (via Recharts).

---

## 🚀 Running the Project Locally

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/bijaymsra/smart-load-balancer.git
cd smart-load-balancer


2️⃣ Start Kubernetes Cluster (via kind)
Make sure Docker Desktop is running and Kubernetes is enabled.
kind create cluster --name smart-lb-cluster

3️⃣ Deploy Services
kubectl apply -f k8s/

This includes:
Spring Boot backend
React frontend
Dashboard service
HPA configuration

4️⃣ Access the Services
Service	URL
React Client	http://localhost:<NodePort>
Backend APIs	http://<backend-service-ip>:<port>
Grafana	http://localhost:3000
Prometheus	http://localhost:9090

```

---

### 🔍 Output Preview:

## 📊 Monitoring

- **Grafana**: Displays dynamic charts for CPU, memory, and traffic usage  
- **Prometheus**: Collects metrics from all microservices and Kubernetes components  

---

## 🐳 Docker

All services are fully containerized using **Docker**.

### 🔧 Build Docker Images

From each service directory, run:

```bash
docker build -t service-name:latest .

## 📌 Features

- ✅ Kubernetes-powered autoscaling  
- ✅ Real-time traffic distribution based on CPU usage  
- ✅ React dashboard with dynamic charts  
- ✅ Prometheus and Grafana integration  
- ✅ Microservices fully containerized  
- ✅ Cloud deployment-ready  

## ✍️ Author

**Bijay M S R A**  
[GitHub Profile](https://github.com/bijaymsra)

---

## 📜 License

This project is licensed under the **MIT License**.

---

## 📸 Optional Additions

Want to improve this README further? Consider adding:

- 🧱 **Architecture Diagram** (PNG/SVG)
- 🖼️ **Screenshots** of the frontend & Grafana dashboard
- 🎥 **Project Demo Video** (YouTube/Loom)
- ☁️ **Deployment Steps** for GCP / AWS / Azure
- 🤝 **Contribution Guidelines**

## 💡 Need Help?

Let me know if you want help generating:

- 🧱 A diagram of your architecture  
- 📄 YAML templates for Prometheus/Grafana setup  
- ⚙️ GitHub Actions CI/CD for auto-deployments  

✨ Happy to help polish this project even more!
