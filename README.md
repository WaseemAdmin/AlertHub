[README.md](https://github.com/user-attachments/files/24372095/README.md)
# 🚨 Alert Hub

## Overview

Alert Hub is a microservices-based system that helps project managers track changes from task management platforms such as **GitHub**, **Jira**, and **ClickUp**.

The system scans platform data, evaluates predefined metrics and conditions, and sends **Email or SMS notifications** when specific rules are met.

---

## 🧩 System Architecture

The application consists of **10 microservices**:

1. Loader  
2. Security  
3. Action  
4. Metric  
5. Processor  
6. Sender Email  
7. Sender SMS  
8. User  
9. Evaluation  
10. Logger  

Each microservice:
- Handles exceptions properly  
- Includes Swagger documentation  
- Contains service-layer testing (Logger excluded)

---

## 📦 Data Providers

Supported platforms:
- GitHub
- Jira
- ClickUp

### Data Folder Structure

```
data/
 ├── github/
 ├── jira/
 └── clickup/
```

### File Naming Convention

```
{provider}_yyyy_mm_ddThh_mm_ss
```

Example:
```
jira_2024_08_22T13_30_00
```

---

## 🔄 Loader Service

The Loader service:
- Detects the latest data files
- Parses and transforms platform data
- Maps data to database schema
- Stores results in `platformInformation`
- Runs automatically every hour
- Supports manual scan triggering

**Notes**
- Null numeric values are treated as `0`
- Database timestamp represents scan time

---

## 🗄️ Database

**Database:** MySQL  

**Tables**
- users  
- platformInformation  
- metric  
- action  

---

## 🔐 Security Service

Manages users, authentication, and permissions.

### Permissions
- Create / Update / Delete Action
- Create / Update / Delete Metric
- Trigger manual scan
- Trigger manual process
- Trigger evaluation
- Read access

Admin users have full permissions including user management.

---

## 📊 Metric Service

Metrics define conditions to monitor platform activity.

**Fields**
- id (UUID)
- user_id
- name
- label (enum)
- threshold
- time_frame_hours

Example:
If there are **10 bug-labeled tasks** within **12 hours**, the metric is triggered.

---

## 🛠️ Action Service

Actions define what happens when metrics are satisfied.

**Action Types**
- Email
- SMS

Conditions use AND / OR logic:
```
[[1,2],[3]]
```
Meaning:
- (Metric 1 AND Metric 2) OR Metric 3

---

## ⏰ Job Scheduler

- Runs every 30 minutes
- Checks enabled and non-deleted actions
- Pushes valid actions to the queue

---

## ⚙️ Processor Service

- Retrieves actions from queue
- Evaluates metric conditions
- Produces Kafka messages
- Routes messages to Email or SMS topics

---

## 📩 Notification Services

**Email Service**
- Pulls messages from Email topic
- Sends email notifications

**SMS Service**
- Pulls messages from SMS topic
- Sends SMS notifications

---

## 📈 Evaluation Service

Provides developer performance insights.

### Endpoints

1. Top developer by label  
   `GET /evaluation/developer/most-label`

2. Label aggregation for developer  
   `GET /evaluation/developer/{id}/label-aggregate`

3. Total tasks for developer  
   `GET /evaluation/developer/{id}/task-amount`

Results are sent to the email notification queue.

---

## 🪵 Logger Service

- Centralized logging using MongoDB
- Uses SLF4J
- Stores logs with timestamp, level, service name, and message

---

## 🚀 Deployment (Minikube)

1. Build Docker image  
2. Push image to Docker Hub  
3. Start Minikube  
4. Deploy using Kubernetes YAML  
5. Expose the service  
6. Verify pods and services  
7. Access service URL  
8. Test with Postman or frontend  

---

## ✅ Summary

Alert Hub provides:
- Centralized monitoring
- Rule-based notifications
- Scalable microservices
- Developer performance evaluation
