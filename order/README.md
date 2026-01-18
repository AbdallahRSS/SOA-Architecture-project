#  Order Service

Order Service is a **microservice responsible for managing orders** in a delivery system built using **SOA / Microservices architecture**.  
It handles order creation, retrieval, status updates, and deletion while enforcing business rules.

---

##  Architecture Overview

- Independent Spring Boot microservice
- Communicates with **Delivery Service via REST (Synchronous communication)**
- Uses **PostgreSQL** as the database
- Containerized with **Docker**

---

##  Service Configuration

| Property | Value |
|--------|------|
| Service Name | order-service |
| Port | 8081 |
| Database | PostgreSQL |
| Database Name | order_db |

---

##  Order Entity

```json
{
  "id": 1,
  "customerName": "John Doe",
  "address": "Nouakchott",
  "status": "CREATED",
  "createdAt": "2026-01-17T23:55:01"
}

```

## Order Status Values
- CREATED

- CONFIRMED

- DELIVERED

- CANCELLED

## API Endpoints

Base URL:

```
http://localhost:8081/orders
```

## Testing Endpoints Using Postman

### 1. Create Order

Endpoint:

```
POST /orders
```

Body:
```
{
  "customerName": "abda ahmed",
  "address": "Nouakchott, Aravat"
}
```

Expected Response:
- Status: `201 CREATED`

```
{
  "id": 1,
  "customerName": "abda ahmed",
  "address": "Nouakchott, Aravat",
  "status": "CREATED",
  "createdAt": "2026-01-17T23:55:01"
}
```

### 2. Get All Orders

Endpoint:

```
GET /orders
```

Expected Response:

```
[
  {
    "id": 1,
    "customerName": "abda ahmed",
    "address": "Nouakchott, Aravat",
    "status": "CREATED",
    "createdAt": "2026-01-17T23:55:01"
  }
]
```

### 3. Get Order by ID

Endpoint:

```
GET /orders/{id}
```

### 4. Confirm Order (Called by Delivery Service)

⚠️ This endpoint is not meant to be called directly by users.
It is called automatically by Delivery Service when a delivery is assigned.


Endpoint:

```
PUT /orders/{id}/confirm
```

Result:

```
"status": "CONFIRMED"
```

### 5. Deliver Order (Called by Delivery Service)

Endpoint:

```
PUT /orders/{id}/deliver
```

Result:

```
"status": "DELIVERED"
```

### 6. Cancel Order

❗ Only orders with status `CREATED` can be cancelled.


Endpoint:

```
PUT /orders/{id}/cancel
```

### 7. Delete Order

❗ Only orders with status `CANCELLED` can be deleted.


Endpoint:

```
DELETE /orders/{id}
```

Expected Response:

- Status: 204 NO CONTENT


## Running with Docker

From the project root:

```
docker compose up --build
```

Order Service will be available at:

```
http://localhost:8081
```





