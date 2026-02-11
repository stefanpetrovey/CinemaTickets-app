# Cinema Tickets Web Application

A full-stack web application for managing cinema tickets, built with Spring Boot and Thymeleaf. This application allows users to browse movies, purchase tickets, and manage their shopping cart.

## Features

### User Features
- Browse available movies with details (title, image, rating, production company)
- Select movie tickets with date and quantity
- Shopping cart functionality
- Order confirmation and ticket management
- User authentication and registration
- User profile management

### Admin Features
- Add new movies
- Edit existing movies
- Delete movies
- Manage production companies
- User management

## Technology Stack

### Backend
- **Java** - Programming language
- **Spring Boot** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database (development)
- **Maven** - Dependency management

### Frontend
- **Thymeleaf** - Server-side template engine
- **HTML5/CSS3** - Markup and styling
- **Bootstrap** - UI framework

## Project Structure

```
CinemaTicket-web-app/
├── src/
│   ├── main/
│   │   ├── java/mk/ukim/finki/wp/lab/
│   │   │   ├── bootstrap/          # Data initialization
│   │   │   ├── config/             # Security and configuration
│   │   │   ├── model/              # Entity classes
│   │   │   ├── repository/         # Data access layer
│   │   │   ├── service/            # Business logic
│   │   │   └── web/                # Controllers and servlets
│   │   └── resources/
│   │       ├── templates/          # Thymeleaf templates
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Git
```

Access the application:

http://localhost:9091/movies
```

## Key Models

- **Movie** - Movie information (title, rating, image, production)
- **Production** - Production company details
- **TicketOrder** - Ticket booking information
- **ShoppingCart** - User shopping cart
- **User** - User account information
- **Price** - Pricing information for tickets

## Security

The application uses Spring Security with:
- Custom username/password authentication
- Role-based access control (USER, ADMIN)
- Session management
- Password encryption

## API Endpoints

### Public Endpoints
- `GET /movies` - List all movies
- `GET /login` - Login page
- `GET /register` - Registration page

### Authenticated Endpoints
- `GET /shopping-cart` - View shopping cart
- `POST /shopping-cart/add` - Add tickets to cart
- `GET /ticket-order` - View orders
- `POST /movies/add` - Add new movie (Admin)
- `POST /movies/{id}/edit` - Edit movie (Admin)
- `DELETE /movies/{id}/delete` - Delete movie (Admin)

## Screenshots

### Movies List Page
The main page displays all available movies with their details, ratings, and production companies. Users can select dates, choose ticket quantities, and add items to their cart.

## Future Enhancements

- [ ] Payment gateway integration
- [ ] Seat selection functionality
- [ ] Movie reviews and ratings
- [ ] Email notifications
- [ ] Mobile responsive design improvements
- [ ] REST API for mobile applications

## Author

**Stefan Petrov**
- GitHub: [@stefanpetrovey](https://github.com/stefanpetrovey)
