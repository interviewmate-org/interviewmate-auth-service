# InterviewMate Authentication Service

<div align="center">

![InterviewMate Logo](https://img.shields.io/badge/InterviewMate-Auth%20Service-blue?style=flat-square)
![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-green?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

**A robust, scalable, and secure authentication and authorization microservice built with Spring Boot**

</div>

---

## 🎯 Project Overview

InterviewMate Authentication Service is a dedicated microservice component of the InterviewMate platform, designed to handle all authentication and authorization requirements. This service provides secure user authentication, token management, OAuth2 integration, OTP verification, and admin controls within a distributed microservices architecture.

### Key Characteristics

- **Microservice Architecture**: Built as an independent, scalable microservice using Spring Cloud and Eureka
- **Security First**: Implements industry-standard security practices with JWT, OAuth2, and Spring Security
- **High Performance**: Leverages Redis caching and Kafka messaging for optimal performance
- **Enterprise-Grade**: Supports comprehensive audit logging, distributed tracing, and monitoring
- **Cloud Native**: Containerized with Docker and optimized for cloud deployment

---

## ✨ Core Features

### 1. **User Authentication**
- Secure login with email and password
- JWT (JSON Web Token) based stateless authentication
- Token refresh mechanisms with refresh tokens
- Automatic token expiration handling

### 2. **OAuth2 Integration**
- Google OAuth2 authentication
- GitHub OAuth2 authentication
- Social login provider support
- Automatic user profile synchronization

### 3. **OTP (One-Time Password)**
- OTP generation and verification
- SMS/Email based delivery
- Configurable expiration times
- Security rate limiting

### 4. **User Management**
- User registration and profile management
- Role-based access control (RBAC)
- User status management
- Account lock/unlock capabilities

### 5. **Admin Controls**
- User management dashboard
- Role assignment and modification
- Account deactivation and activation
- Admin audit trails

### 6. **Security Features**
- Password hashing using BCrypt
- CORS (Cross-Origin Resource Sharing) support
- CSRF protection
- Rate limiting and throttling
- Input validation and sanitization

### 7. **Monitoring & Observability**
- Distributed tracing with TraceID
- Performance monitoring with AOP
- Exception tracking and logging
- Spring Boot Actuator metrics

### 8. **Event-Driven Architecture**
- Kafka integration for async processing
- Audit event publishing
- Security event logging
- Performance metrics streaming

---

## 🏗️ Architecture Overview

### Technology Stack

| Layer | Technology | Purpose |
|-------|-----------|---------|
| **Runtime** | Java 21 | Latest Java LTS version for modern features |
| **Framework** | Spring Boot 4.0.6 | Microservice foundation |
| **Security** | Spring Security + OAuth2 | Authentication and authorization |
| **Data Persistence** | Spring Data JPA + MySQL | Reliable data storage |
| **Caching** | Spring Data Redis Reactive | High-performance caching |
| **Messaging** | Apache Kafka | Async event processing |
| **Service Discovery** | Eureka Client | Microservice registration and discovery |
| **Token Management** | JJWT | JWT token creation and validation |
| **Mapping** | ModelMapper | DTO to Entity conversion |
| **AOP** | Spring AOP | Cross-cutting concerns and auditing |
| **API Documentation** | Spring Boot Actuator | Health checks and metrics |

### Project Structure

```
src/
├── main/
│   ├── java/com/interviewmate/authservice/
│   │   ├── controller/           # REST API endpoints
│   │   ├── service/              # Business logic
│   │   ├── service/implementation/ # Service implementations
│   │   ├── entity/               # JPA entities
│   │   ├── repository/           # Data access layer
│   │   ├── dto/                  # Data Transfer Objects
│   │   ├── payload/              # Request/Response payloads
│   │   ├── security/             # Security configurations
│   │   ├── configuration/        # Spring configurations
│   │   ├── exceptions/           # Custom exceptions
│   │   ├── filter/               # HTTP filters
│   │   ├── aop/                  # Aspect-oriented programming
│   │   ├── utils/                # Utility classes
│   │   ├── enums/                # Enumerations
│   │   └── constance/            # Constants
│   └── resources/
│       └── application.yml       # Configuration files
└── test/                         # Test suite
```

---

## 🔑 Key Components

### Controllers
- **AuthController**: Handles user login, registration, token refresh, password reset
- **OtpController**: Manages OTP generation and verification
- **AdminController**: Provides admin functionalities and user management

### Services
- **AuthService**: Core authentication logic
- **OtpService**: OTP generation and validation
- **AdminService**: Administrative operations
- **CustomUserDetailService**: Spring Security user details provider
- **CustomOAuth2UserService**: OAuth2 user information handling

### Security Components
- **SecurityConfig**: Spring Security configuration and bean definitions
- **JwtFilter**: JWT token validation and extraction
- **TraceIdFilter**: Distributed tracing context management
- **OAuth2SuccessHandler**: OAuth2 authentication success handling
- **Oauth2FailureHandler**: OAuth2 authentication failure handling

### Infrastructure
- **KafkaTopics**: Topic definitions for event streaming
- **KafkaProducerConfig**: Kafka producer configuration
- **KafkaAuditPublisher**: Audit event publishing to Kafka
- **AopConfig**: AOP aspect configuration
- **AppConfig**: General application configuration

### Database Entities
- **User**: User account information and credentials
- **RefreshToken**: Token management for refresh operations

---

## 📊 API Endpoints

### Authentication Endpoints
```
POST   /api/auth/login              - User login
POST   /api/auth/register           - User registration
POST   /api/auth/refresh-token      - Refresh access token
POST   /api/auth/logout             - User logout
POST   /api/auth/forget-password    - Initiate password reset
```

### OTP Endpoints
```
POST   /api/otp/generate            - Generate OTP
POST   /api/otp/verify              - Verify OTP
```

### Admin Endpoints
```
GET    /api/admin/users             - List all users
GET    /api/admin/users/{id}        - Get user details
PUT    /api/admin/users/{id}/role   - Update user role
PUT    /api/admin/users/{id}/status - Update user status
DELETE /api/admin/users/{id}        - Deactivate user
```

### OAuth2 Endpoints
```
GET    /oauth2/authorize/google     - Google OAuth2 login
GET    /oauth2/authorize/github     - GitHub OAuth2 login
```

---

## 🔐 Security Implementation

### JWT Token Structure
- **Header**: Algorithm (HS256) and Token Type
- **Payload**: User ID, Username, Roles, Issuance/Expiration timestamps
- **Signature**: HMAC SHA256 signature

### OAuth2 Providers
- **Google**: OAuth2 authentication with Google accounts
- **GitHub**: OAuth2 authentication with GitHub accounts

### Password Security
- **Algorithm**: BCrypt with configurable strength
- **Salting**: Automatic salt generation per password
- **Hashing**: One-way hashing for maximum security

### Request Validation
- **Input Sanitization**: XSS prevention
- **Type Validation**: DTO validation annotations
- **Size Limits**: Request size constraints

---

## 🚀 Deployment Information

### Container Support
- **Docker**: Full containerization support with Dockerfile
- **Docker Compose**: Local development environment setup
- **Image Building**: Maven plugin for OCI image creation

### Service Discovery
- **Eureka Registration**: Automatic service registration with Spring Cloud Netflix Eureka
- **Health Checks**: Spring Boot Actuator endpoints for load balancer health verification

### Configuration Management
- **Environment Variables**: Externalized configuration
- **Property Files**: Multiple environment profiles (dev, test, prod)
- **Secrets Management**: Secure credential handling

---

## 📦 Dependencies Summary

### Core Framework
- spring-boot-starter-webmvc
- spring-boot-starter-security
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- spring-boot-starter-aop

### Data & Caching
- spring-boot-starter-data-redis-reactive
- spring-data-redis
- mysql-connector-java

### Messaging
- spring-boot-starter-kafka

### OAuth2 & Authentication
- spring-boot-starter-security-oauth2-client
- jjwt (JSON Web Token Library)

### Service Discovery
- spring-cloud-starter-netflix-eureka-client

### Utilities
- lombok
- modelmapper
- jackson-databind
- spring-boot-devtools

### Monitoring
- spring-boot-starter-actuator

---

## 🧪 Testing

The project includes comprehensive test suites:
- Unit tests for service layer
- Integration tests for controllers
- Security tests for authentication flows
- AOP tests for cross-cutting concerns

Test dependencies include:
- spring-boot-starter-test
- spring-security-test
- testcontainers for integration tests

---

## 🔄 Event-Driven Flow

### Kafka Event Publishing
1. **Security Audit Events**: Login attempts, token generation, OAuth2 events
2. **Performance Events**: Method execution time monitoring
3. **Exception Events**: Error tracking and logging
4. **Trace Events**: Distributed tracing information

Topics are configured in `KafkaTopics` configuration class.

---

## 📈 Performance Optimization

### Caching Strategy
- Redis caching for frequently accessed user data
- Token blacklisting using Redis
- Session-like behavior with TTL (Time To Live)

### Async Processing
- Kafka for decoupled event processing
- Non-blocking I/O with reactive Redis
- Parallel request handling

### Database Optimization
- JPA query optimization
- Connection pooling
- Index management

---

## 🛠️ Development Tools

- **Maven**: Build and dependency management
- **Spring Boot DevTools**: Live reload during development
- **Lombok**: Boilerplate code reduction
- **ModelMapper**: Automatic DTO mapping

---

## 📋 Configuration Files

### Application Configuration
- `application.yml`: Main application configuration
- `application-dev.yml`: Development profile
- `application-prod.yml`: Production profile
- `.env.local`: Local environment variables

### Docker Configuration
- `Dockerfile`: Container image definition
- `docker-compose.local.yml`: Local development stack

---

## 🤝 Contributing

This is an enterprise microservice component. For contributions, please:
1. Follow the existing code structure and naming conventions
2. Ensure all tests pass before submission
3. Update documentation for significant changes
4. Adhere to security best practices

---

## 📞 Support & Documentation

- For API documentation, access the Actuator endpoints at `/actuator`
- Health check available at `/actuator/health`
- Metrics available at `/actuator/metrics`

---

<br/>

## 👥 Development Team

Meet the talented developers who built InterviewMate Authentication Service:

<div align="center">

| <img src="https://avatars.githubusercontent.com/u/soumyadip-adak99?v=4&s=150" width="150" height="150" style="border-radius: 50%; border: 3px solid #007bff;"/><br/><b>Soumyadip Adak</b><br/>Associate Full Stack Developer<br/>[GitHub](https://github.com/soumyadip-adak99) | <img src="https://avatars.githubusercontent.com/u/swatishaw1?v=4&s=150" width="150" height="150" style="border-radius: 50%; border: 3px solid #28a745;"/><br/><b>Swati Shaw</b><br/>Associate Full Stack Developer<br/>[GitHub](https://github.com/swatishaw1) | <img src="https://avatars.githubusercontent.com/u/MrPal28?v=4&s=150" width="150" height="150" style="border-radius: 50%; border: 3px solid #ffc107;"/><br/><b>Arindam Pal</b><br/>Backend Developer & DevOps<br/>[GitHub](https://github.com/MrPal28) | <img src="https://avatars.githubusercontent.com/u/AtanuGayen?v=4&s=150" width="150" height="150" style="border-radius: 50%; border: 3px solid #dc3545;"/><br/><b>Atanu Gayen</b><br/>Technical Associate<br/>[GitHub](https://github.com/AtanuGayen) |
|:---:|:---:|:---:|:---:|
| Full-stack development across frontend and backend | Full-stack development across frontend and backend | Infrastructure and DevOps expertise | Technical support and quality assurance |

</div>

### Team Contributions

- **Soumyadip Adak** - API development, service layer architecture, database schema design
- **Swati Shaw** - API development, security implementation, OAuth2 integration
- **Arindam Pal** - DevOps infrastructure, Docker containerization, Kubernetes deployment, CI/CD pipeline
- **Atanu Gayen** - Testing and quality assurance, performance optimization, monitoring setup

---

## 📄 Version Information

- **Project Version**: 0.0.1-SNAPSHOT
- **Spring Boot Version**: 4.0.6
- **Java Version**: 21 (LTS)
- **Spring Cloud Version**: 2025.1.1

---

## 📝 License

This project is part of the InterviewMate platform ecosystem.

---

<div align="center">

**Built with ❤️ by the InterviewMate Team**

[Back to Top](#interviewmate-authentication-service)

</div>
