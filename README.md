# Email Writer Service

## Description
The **Email Writer Service** is a RESTful API built with **Spring Boot** that generates professional email replies using **Google Gemini AI**. It takes an email's content and tone as input and returns a well-structured response. The service is deployed on **Render** for cloud accessibility.

## Features
- **AI-Powered Email Generation**: Generates email replies based on user-provided content and tone.
- **REST API with Swagger UI**: Provides an interactive API documentation for easy testing.
- **Google Gemini API Integration**: Uses **Gemini AI** for generating human-like responses.
- **Spring Boot with WebClient**: Utilizes **WebClient** for seamless API calls.
- **Deployed on Render**: Accessible via a public URL for real-time use.
- **Cross-Origin Support**: Allows requests from different domains via **CORS**.

## Technologies Used
- Java 17
- Spring Boot 3.4
- WebClient
- Google Gemini API
- Render (for deployment)
- Swagger (for API documentation)

## API Endpoints
### 1. Generate Email Reply
**POST** `/api/email/generate`

#### Request Body (JSON):
```json
{
  "emailContent": "Hello, I would like to know more about your services.",
  "tone": "formal"
}
```

#### Response (JSON):
```json
{
  "reply": "Dear Sir/Madam, Thank you for reaching out. We would be happy to provide more details regarding our services..."
}
```

## How to Run Locally
### Prerequisites:
- Java 21 or above
- Maven

### Steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/email-writer-service.git
   cd email-writer-service
   ```
2. Build and run the project:
   ```bash
   mvn spring-boot:run
   ```
3. Access Swagger UI:
   - Open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Deployment
The application is deployed on **Render** and can be accessed at:
- 🔗 [](https://email-writer-sb-production-6521.up.railway.app/swagger-ui/index.html#)




