# 📁 File Upload Lookup Platform

A full-stack platform for uploading file metadata and querying it via a user-friendly interface. The backend is built with **Spring Boot** and **AWS (DynamoDB + S3)**, with a **React** frontend for file uploads and search functionality.

---

## 🚀 Features

- ✅ Upload file metadata (filename, timestamp, S3 URL)
- ✅ Query metadata by filename
- ✅ Store records in DynamoDB (local mock or AWS)
- ✅ Supports S3 file links
- ✅ (Planned) Lambda integration for queries and event notifications
- ✅ (Planned) Infrastructure as code via Terraform

---

## 🧱 Tech Stack

### Backend:
- Java 17
- Spring Boot 3.3.x
- AWS SDK v2 (DynamoDB, S3)
- Lombok

### Frontend (Planned):
- React + TypeScript
- Vite
- axios
- shadcn/ui or Tailwind CSS (optional)

---

## 📦 Project Structure (Backend)

```bash
src/
├── main/
│   ├── java/com/tonyyang/fileuploadlookup/
│   │   ├── controller/      # REST APIs
│   │   ├── service/         # Business logic
│   │   └── model/           # Data model
│   └── resources/
│       └── application.properties
