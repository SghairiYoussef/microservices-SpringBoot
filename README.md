# 🏥 Microservices-Based Patient Management System

This project implements a microservices-based architecture for a Patient Management System using Spring Boot, Kafka, gRPC, and Docker. It is designed to demonstrate scalable, modular, and maintainable service-oriented design principles.

## 📦 Project Structure

The system comprises the following services:

- **`api-gateway`**: Routes external requests to internal services, handling cross-cutting concerns like authentication and rate limiting.
- **`auth-service`**: Manages user authentication and authorization, utilizing JWT tokens.
- **`patient-service`**: Handles CRUD operations and management of patient records.
- **`billing-service`**: Processes billing information and integrates with the analytics service. (#In dev)
- **`analytics-service`**: Aggregates and analyzes data from various services for reporting purposes. (#In dev)
- **`grpc-requests/billing-service`**: Contains gRPC client definitions for inter-service communication with the billing service.

## 🛠️ Technologies Used

- **Spring Boot** for building standalone, production-grade applications.
- **gRPC** for efficient inter-service communication.
- **Kafka** for event-driven async communication between services.
- **Docker** for containerizing services and managing deployments.
- **JWT** for secure authentication and authorization.

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker & Docker Compose
- Kafka (running locally or via Docker)

### Running the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/SghairiYoussef/microservices-SpringBoot.git
   cd microservices-SpringBoot
   ```

2. **Build the services: Each service independantly**

   ```bash
   mvn clean install
   ```

3. **Start Kafka and the services using Docker Compose:**

   ```bash
   docker-compose up --build
   ```

   *Note: Ensure Kafka is properly configured and running. If it's not set up in Docker Compose, you can configure it separately.*

### Kafka Integration

- **Kafka Topics**: The system uses Kafka topics to exchange messages between services, ensuring asynchronous and decoupled communication.
- **Producer/Consumer**: Services like the `billing-service` and `patient-service` act as both Kafka producers and consumers.

## 🔗 API Endpoints

Once the services are up and running, you can access them via the API Gateway:

- **Authentication:**
  - `POST /auth/login`
  - `POST /auth/register`

- **Patient Management:**
  - `GET api/patients`
  - `POST api/patients`
  - `PUT api/patients/{id}`
  - `DELETE api/patients/{id}`

- **Billing:**
  - `GET api/billing/invoices`
  - `POST api/billing/payments`

- **Analytics:**
  - `GET api/analytics/reports`

*Note: These endpoints are routed through the API Gateway and may require proper authentication headers.*

## 📚 Documentation

For detailed API documentation and service specifications, refer to the Swagger UI.

## 🤝 Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.
