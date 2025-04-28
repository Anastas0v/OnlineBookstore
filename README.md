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

### How to Run

