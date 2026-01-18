## Build and start all services

Run the following command from the root directory (where docker-compose.yml exists):

```
# Build and start in detached mode
docker-compose up -d --build

# View logs
docker-compose logs -f

# Stop services
docker-compose down

# Stop and remove volumes ( deletes database)
docker-compose down -v
```

This will start:

- PostgreSQL on port 5432

- Order Service on port 8081

- Delivery Service on port 8082


## Check running containers

```
docker ps
```

You should see:

- order-postgres

- order-service

- delivery-service




