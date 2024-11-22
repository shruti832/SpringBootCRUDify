
# CRUDify - REST API for Product Management

CRUDify is a Spring Boot-based REST API application that provides CRUD operations for managing products. This application uses Hibernate for ORM, a MySQL database for persistence, and Swagger for API documentation.

---

## Features

- Add, update, delete, and fetch product details.
- Input validation for entity attributes using annotations.
- Global exception handling for better error management.
- API documentation with Swagger (OpenAPI).

---

## Prerequisites

Before building and running the application, ensure the following software is installed:

1. **Java Development Kit (JDK):** Version 17 or higher.
2. **Maven:** For dependency management and build lifecycle.
3. **MySQL Database:** Set up a database to store the data.
4. **Postman or Curl:** For testing API endpoints (optional).

---

## Installation and Setup

### Step 1: Clone the Repository
Clone the repository to your local machine:
```bash
git clone https://github.com/your-repository/crudify.git
cd crudify
```

---

### Step 2: Configure the Database
1. **Create a database in MySQL:**
   ```sql
   CREATE DATABASE crudify_db;
   ```
   
2. **Configure your database settings in the `application.properties` file located in `src/main/resources/`:**
   Update the following properties:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/crudify_db
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

   Replace `your_mysql_username` and `your_mysql_password` with your actual MySQL credentials. 

---

### Step 3: Build the Application
Run the following command to clean and build the application:
```bash
mvn clean install
```

---

### Step 4: Run the Application
After building the application, use one of the following methods to start the server:

#### Option 1: Run with Maven
```bash
mvn spring-boot:run
```

#### Option 2: Run the Generated JAR
After building the application, run the generated JAR file:
```bash
java -jar target/crudify-0.0.1-SNAPSHOT.jar
```

If the server starts successfully, you should see the log indicating that the application is running on:
```
http://localhost:8080
```

---

## API Endpoints

Once the application is running, you can access the API endpoints at `http://localhost:8080`.

### **Product Endpoints**

| HTTP Method | Endpoint           | Description              |
|-------------|--------------------|--------------------------|
| **POST**    | `/products`        | Add a new product        |
| **GET**     | `/products`        | Fetch all products       |
| **GET**     | `/products/{id}`   | Fetch a product by ID    |
| **PUT**     | `/product/{id}`    | Update a product by ID   |
| **DELETE**  | `/product/{id}`    | Delete a product by ID   |

---

## Example API Requests

### 1. **Add a Product**  
   **Request:**
   ```bash
   curl -X POST http://localhost:8080/products      -H "Content-Type: application/json"      -d '{"productName": "Laptop", "productPrice": 1200.99, "productDescription": "A high-performance laptop"}'
   ```

### 2. **Get All Products**  
   **Request:**
   ```bash
   curl -X GET http://localhost:8080/products
   ```

### 3. **Get Product by ID**  
   **Request:**
   ```bash
   curl -X GET http://localhost:8080/products/1
   ```

### 4. **Update a Product**  
   **Request:**
   ```bash
   curl -X PUT http://localhost:8080/product/1      -H "Content-Type: application/json"      -d '{"productName": "Laptop Pro", "productPrice": 1500.99, "productDescription": "An updated high-performance laptop"}'
   ```

### 5. **Delete a Product**  
   **Request:**
   ```bash
   curl -X DELETE http://localhost:8080/product/1
   ```

---

## Testing the API

### Swagger UI
You can interact with the API using the Swagger UI. Once the application is running, visit:
```
http://localhost:8080/swagger-ui.html
```

### Postman or Curl
You can test the API endpoints using Postman or Curl. For example:
```bash
# Example: Fetch all products
curl -X GET http://localhost:8080/products
```

---

## Troubleshooting

1. **Database Connection Issues:**
   - Ensure MySQL is running.
   - Verify the credentials in `application.properties`.

2. **Port Conflicts:**
   - Check if port `8080` is already in use. You can change the port in the `application.properties` file:
     ```properties
     server.port=8085
     ```

## Demo Video

Click [here](https://drive.google.com/file/d/1k5f_3dXnqdvtYejnTtXDhaKpiLmeuzMH/view?usp=sharing) to watch the demo video.


---