Inventory Management API
REST API built with Spring Boot to manage Categories and Products with validation and global exception handling.

Tech Stack
Java 17, Spring Boot, Spring Web, Spring Data JPA, H2 Database (in-memory), Bean Validation (Jakarta Validation)

Project Structure
[parth@archlinux inventory-management]$ tree
.
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── assesment
│   │   │           └── inventory_management
│   │   │               ├── controller
│   │   │               │   ├── CategoryController.java
│   │   │               │   └── ProductController.java
│   │   │               ├── dto
│   │   │               │   ├── CategoryRequest.java
│   │   │               │   ├── CategoryResponse.java
│   │   │               │   ├── ErrorResponse.java
│   │   │               │   ├── ProductRequest.java
│   │   │               │   └── ProductResponse.java
│   │   │               ├── exception
│   │   │               │   ├── CategoryNotFoundException.java
│   │   │               │   ├── DuplicateNameException.java
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   └── ProductNotFoundException.java
│   │   │               ├── InventoryManagementApplication.java
│   │   │               ├── model
│   │   │               │   ├── Category.java
│   │   │               │   └── Product.java
│   │   │               ├── repository
│   │   │               │   ├── CategoryRepo.java
│   │   │               │   └── ProductRepo.java
│   │   │               └── service
│   │   │                   ├── CategoryService.java
│   │   │                   └── ProductService.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── assesment
│                   └── inventory_management
│                       └── InventoryManagementApplicationTests.java


34 directories, 43 files

Prerequisites: JDK 17+, Maven 3.8+

Set up
1. git clone <https://github.com/StonerSensei/inventory-management>
   cd inventory-management
2. mvn clean install
3. mvn spring-boot:run

4. Application Properties
    spring.application.name=inventory-management
    server.port=8080
    
    spring.datasource.url=jdbc:h2:mem:inventorydb
    spring.datasource.driver-class-name=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    
    logging.level.org.springframework.web=INFO
    logging.level.com.yourname.inventory=DEBUG


API Endpoints and Request
1. Create Category
    POST /api/categories
    Content-Type: application/json
    
    {
      "name": "Electronics",
      "description": "Electronic devices and accessories"
    }
2. Get Category by ID
   GET /api/categories/1
3. Get All Categories
   GET /api/categories
4. Delete Category
   DELETE /api/categories/1
5. Product Endponts
    POST /api/products
    Content-Type: application/json
    
    {
      "name": "Laptop",
      "price": 45999.99,
      "quantity": 10,
      "categoryId": 1
    }
6. Get Product by ID
   GET /api/products/1
7. Get All Products
   GET /api/products
8. Get Products by Category
   GET /api/products?categoryId=1
9. Get Products by Price Range
    GET /api/products?minPrice=1000&maxPrice=50000
10. Get Products by Category and Price Range
    GET /api/products?categoryId=1&minPrice=1000&maxPrice=50000
11. Update Product
    PUT /api/products/1
    Content-Type: application/json
    
    {
      "name": "Gaming Laptop",
      "price": 55999.99,
      "quantity": 8,
      "categoryId": 1
    }





