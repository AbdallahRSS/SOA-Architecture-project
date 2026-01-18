# Delivery Service

##  Overview
The **Delivery Service** is a Spring Boot microservice responsible for managing deliveries in a simple SOA / microservices architecture.

It works together with the **Order Service** and updates order status **synchronously (REST)** when delivery actions occur.

---

##  Responsibilities
- Create a delivery for an existing order
- Assign a delivery to a driver
- Complete a delivery
- Track delivery status
- Notify Order Service via REST calls

---

##  Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- REST (synchronous communication)
- Docker

---

##  Project Structure

```
delivery/
├── controller/
│   └── DeliveryController.java
├── service/
│   └── DeliveryService.java
├── entity/
│   ├── Delivery.java
│   └── DeliveryStatus.java
├── dto/
│   └── AssignDeliveryRequest.java
├── repository/
│   └── DeliveryRepository.java
├── exception/
│   ├── DeliveryActionException.java
│   └── DeliveryNotFoundException.java
├── DeliveryApplication.java
└── application.properties
```

---

## Running the Service

### Option 1: Run locally

```bash
./mvnw spring-boot:run
```

The service will start on:

```
http://localhost:8082
```

---

### Option 2: Run with Docker Compose

Make sure **order-service** and **PostgreSQL** are running.

```bash
docker compose up --build
```

---

##  Service Communication

Delivery Service communicates with Order Service **synchronously** using REST:

| Delivery Action | Order Status Update |
|-----------------|--------------------|
| Assign delivery | CONFIRMED          |
| Complete        | DELIVERED          |

Order Service base URL:

```
http://order-service:8081/orders
```

---

## REST API Endpoints

### Create Delivery

```
POST /deliveries?orderId=1
```

---

###  Assign Delivery

```
PUT /deliveries/{id}/assign
```

**Request Body**
```json
{
  "driverName": "Mohamed",
  "latitude": 18.0735,
  "longitude": -15.9582
}
```

---

###  Complete Delivery

```
PUT /deliveries/{id}/complete
```

---

###  Get Delivery by ID

```
GET /deliveries/{id}
```

---

###  Get All Deliveries

```
GET /deliveries
```

---

##  Testing with Postman

### Step 1: Create Order (Order Service)
```http
POST http://localhost:8081/orders
```

```json
{
  "customerName": "Ahmed",
  "address": "Nouakchott"
}
```

---

### Step 2: Create Delivery
```http
POST http://localhost:8082/deliveries?orderId=1
```

---

### Step 3: Assign Delivery
```http
PUT http://localhost:8082/deliveries/1/assign
```

```json
{
  "driverName": "Mohamed",
  "latitude": 18.07,
  "longitude": -15.95
}
```

 Order status becomes **CONFIRMED**

---

### Step 4: Complete Delivery
```http
PUT http://localhost:8082/deliveries/1/complete
```

 Order status becomes **DELIVERED**

---

## ⚠️ Important Notes
- Delivery Service **depends on Order Service**
- Order Service must be running before assigning or completing deliveries
- Communication is **REST synchronous** (no messaging broker)

---

## Future Improvements
- Asynchronous messaging (Kafka / RabbitMQ)
- Circuit breaker (Resilience4j)
- API Gateway
- Centralized configuration

---
