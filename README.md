# Spring Boot CRM

## Spring Boot CRM Web Application inluding RESTful API

The project is based on special customer requirements.

A publishing company has a couple of travel related books.
Employees try to sell the books to bookstores and are paid 
based on sales volume and commission level.

The code should be easily adaptable for other situations.

### Business Logic for Web and RESTful API

The application allows the app user to create, read, update books, customers and employees.
OrderItems can be inserted based on a specific book, customer and employee.

The web interface is secured using form based authentication, 
the RESTful API can be accessed via Basic Auth.

Entity classes: Book, Customer, Employee, OrderItem, User, Role

Views and validation messages are in German, but should be self explanatory,
as code is well documented.
