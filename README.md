# Book Discount Calculator

A Spring Boot app that calculates the price of a shopping basket of books using discount rules.

---

## Run the App

mvn spring-boot:run


## Or with test profile:

mvn spring-boot:run -Dspring-boot.run.profiles=test


---

## Run Tests

mvn test


---

## API Usage (via Postman)

### 1. Add a Book

curl -X POST \
http://localhost:9090/api/v1/books \
-H 'Content-Type: application/json' \
-d '{
"isbn": "123",
"title": "Clean Code",
"author": "Robert Martin",
"publicationYear": 2008
}'

---

### 2. find all Books

curl -X POST \
http://localhost:9090/api/v1/books/search \
-H 'Content-Type: application/json' \
-d '{}'

---

### 3. Calculate Basket Price

curl -X POST \
http://localhost:9090/api/v1/shopping-basket/calculate-price \
-H 'Content-Type: application/json' \
-d '[
{
"book": {
"isbn": "123",
"title": "Clean Code",
"author": "Robert Martin",
"publicationYear": 2008
},
"quantity": 2
},
{
"book": {
"isbn": "111",
"title": "Clean Coder",
"author": "Robert Martin",
"publicationYear": 2011
},
"quantity": 1
}
]'

---

## Configurable Discounts

In `application.yml` or `application-test.yml`:

book-shopping-basket:
discount-groups:
- size: 5
  discount: 0.25
- size: 4
  discount: 0.20
- size: 3
  discount: 0.10
- size: 2
  discount: 0.05
- size: 1
  discount: 0.0

