# 📚 OnlineBookstore

A simple backend bookstore project built with Java 21 and Spring Boot.
It supports book management, customer loyalty points, and dynamic pricing rules.

---
## Design Decisions

### Technologies Used

**As a Java Engineer with a main focus on the Spring Framework**, Spring Boot was a natural choice. 
It allows me to build and deliver clean, scalable, and robust applications. The Spring ecosystem (Spring Data, Spring Web, etc.) takes care of most infrastructure concerns, so I can focus on the core domain logic.

- **Java 21**  
  Leveraged the latest Java features for modern syntax and strong performance.

- **Spring Boot 3.4**  
  Rapid backend development with powerful REST API support and dependency injection.

- **Spring Data JPA**  
  Clean database access without writing boilerplate SQL.

- **H2 Database**  
  Perfect for simple, fast in-memory storage during development.

---

### Architecture
- **Layered Structure**:  
  Organized by `model`, `repository`, `service`, and `controller` layers for clear separation of concerns.

- **DTO Usage**:  
  DTOs (e.g., `BookDTO`, `RequestPurchaseDTO`, `LoyaltyPointsDTO`) decouple API contracts from entities, keeping models clean.

- **PricingService**:  
  Encapsulates all pricing and discount logic, making the system easily extendable for future book types.

### Loyalty Points (Customization)
- Introduced a `useLoyaltyPoints` boolean in the **POST /purchases** request.
- Allows customers to choose whether to redeem 10+ loyalty points for a free book.
- Adds flexibility compared to automatic point usage.

### Extensibility
- **BookType Enum**:  
  Designed to easily add new book types like `SPECIAL_EDITION` in the future.

- **PricingService**:  
  Supports different pricing rules per book type with minimal code changes.

---

## Error Handling

- Uses custom runtime exceptions (e.g., `BookException`, `CustomerException`) for clear and simple error reporting.
- Validates presence of customers and books at the service layer.
- Returns meaningful error messages for missing or invalid input.

---

## Testing

- Unit tests cover critical functionality:
    - PricingService logic (different book types, loyalty points, bundle discounts)
    - Service layer validation and CRUD operations
- Ensures business rules are properly enforced.

- Run tests with: `mvn test`;

---

## Key Assumptions

- **Book Availability**:  
  All books requested for purchase are available (no stock quantity tracking).

- **Loyalty Points**:
    - 10 points are needed to redeem a free book.
    - Only one free book per purchase, even with 20+ points.
    - Loyalty points reset to 0 after redemption; new points are earned after purchase.

- **Bundle Discounts**:
    - Buying 3 or more books qualifies for a bundle discount.
    - Discounts apply multiplicatively where relevant (Old Edition books get multiple discounts).

- **Pricing**:
    - Discounts are properly applied before total price calculation.
    - Negative pricing is not allowed (base prices must be positive).

---

## How to Run

- **Clone the Repository:** `git clone https://github.com/Anastas0v/OnlineBookstore.git`
- **Install Maven on your machine and install all dependencies.**
- **Run the application using the command** `mvn spring-boot:run`
- **Application will be available on** `http://localhost:8080`

### Database Control

- If you need to interact with the in-memory H2 databse:
  - URL: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:bookstore`
  - Username: `admin`
  - Password: `password`


## API Endpoints
### Book API

**Save New Book:**
- *Endpoint*: `POST /book/save`
- *Request Body*:
```json
{
    "title": "Book Title",
    "basePrice": 20.0,
    "type": "regular"
} 
```

**Get All Books And Book By ID:**
- *Endpoint*: `GET /book`
  - Returns a list of all saved books with details such as title, base price, and type.
- *Endpoint*: `GET /book/all`
  - Returns a list of all saved books with details such as title, base price, and type.
- *Endpoint*: `GET /book/{id}`
  - Returns the details of the book, including its title, base price, and type.

**Update Existing Book:**
- *Endpoint*: `PUT /book/update/{id}`
- *Request Body*:
```json
{
  "id": "{id}",
  "title": "Book Title - UPDATED",
  "basePrice": 13.99,
  "type": "OLD_EDITION"
}
```

**Delete Existing Book:**
- *Endpoint*: `DELETE /book/{id}`
  - Deletes a book based on the provided book ID.