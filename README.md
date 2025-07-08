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
🛠️ Getting Started (Backend)
Prerequisites:
Java 17+

AWS credentials in ~/.aws/credentials or via EC2 IAM role

Run Locally:
bash
Copy
Edit
./mvnw spring-boot:run
API available at: http://localhost:8080

📬 API Reference
Upload Metadata
bash
Copy
Edit
POST /upload-metadata
Content-Type: application/json
json
Copy
Edit
{
  "filename": "example.pdf",
  "uploadTime": "2025-07-08T12:00:00Z",
  "s3Url": "https://your-bucket.s3.amazonaws.com/example.pdf"
}
Query Metadata
bash
Copy
Edit
GET /files?filename=example.pdf
Response:

json
Copy
Edit
{
  "filename": "example.pdf",
  "uploadTime": "2025-07-08T12:00:00Z",
  "s3Url": "https://your-bucket.s3.amazonaws.com/example.pdf"
}
🌐 Deployment (Optional)
Frontend can be deployed to S3 + CloudFront

Backend can run on EC2 behind an ALB

Optional Lambda for serverless querying

Terraform for infrastructure provisioning

📌 Roadmap
 Backend: metadata storage and API

 Frontend: upload & query interface

 S3 file upload integration

 Lambda + SNS integration

 Terraform deployment pipeline

👨‍💻 Author
Tony Yang | bb3696

📄 License
MIT

yaml
Copy
Edit

---

Would you like me to:
- Add this file to your GitHub repo automatically?
- Include project badges (build, license, etc)?
- Add example screenshots or a GIF later?

Let me know and I’ll tailor it to your needs!






Ask ChatGPT
