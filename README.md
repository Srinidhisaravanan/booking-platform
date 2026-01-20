# Movie Booking Platform 

A comprehensive movie ticket booking system built with Spring Boot demonstrating modular monolith architecture for both B2B (theatre partners) and B2C (end customers) clients.

## Quick Start

```bash
mvn clean install
mvn spring-boot:run
```

Application starts at: http://localhost:8080  
H2 Console: http://localhost:8080/h2-console

## Implementation Focus

### Core Features (Full Implementation)
   - **READ Scenario**: Browse shows by city/date with discount pricing
   - **WRITE Scenario**: Book tickets with seat selection and pricing  
   - **B2B Partner Onboarding**: Registration and profile management
   - **Discount Engine**: 50% third ticket + 20% afternoon show discounts

### Key Optional Feature (Full Implementation)  
   - **Seat Inventory Management**: Theatre partners can update seat availability and block seats

### Additional Features (Architecture/Stubs)
   - Show management APIs (basic implementation)
   - Bulk operations (interface ready, minimal implementation)
   - Advanced analytics (documented strategy)

2. Non-Functional Requirements (Architecture & Design)
   - NFR Document: Scalability, availability, security, compliance coverage
   - Transaction Design: ACID compliance, concurrency handling
   - Theatre Integration: API strategy for existing and new theatre systems
   - Payment Integration: Gateway strategy and failure handling
   - Security: OWASP Top 10 threat protection approach

3. Platform Provisioning (Architecture Artifacts)  
   - Technology Choices: Java/Spring Boot stack with justification
   - Database Design: Complete data model with transaction strategy
   - COTS Systems: Payment, communication, analytics integration
   - Hosting Strategy: Multi-cloud approach with sizing calculations
   - Release Management: CI/CD pipeline and geo distribution
   - Monitoring: Comprehensive observability and alerting strategy
   - KPIs: Technical, business, and operational metrics
   - Project Plan: 10-month roadmap with effort estimates (Rs 2.1Cr budget)

## API Documentation

B2C Customer APIs:
- Browse shows: GET /shows?city=Mumbai&date=2026-01-20
- View seats: GET /shows/1/seats
- Create booking: POST /bookings
- Get booking: GET /bookings/{bookingReference}

B2B Theatre Partner APIs:
- Register partner: POST /api/partners/register
- Create show: POST /api/partners/{partnerId}/shows
- Update inventory: PUT /api/partners/{partnerId}/inventory/shows/{showId}

## Architecture

Modular Structure:
- movie/ - Movie catalog and show management
- theatre/ - Theatre and seat management
- booking/ - Booking engine with pricing logic  
- partner/ - B2B theatre partner onboarding and management
- shared/ - Common entities

Key Features:
- JPA entities with proper relationships
- Transaction management for booking consistency
- Optimistic locking for concurrency control
- Discount calculation engine
- Theatre partner onboarding workflow
- Multi-tenant architecture support

## Documentation

- Architecture Guide (ARCHITECTURE.md): System design and technical patterns
- NFR Requirements (docs/NON_FUNCTIONAL_REQUIREMENTS.md): Scalability, security, compliance approach
- Platform Provisioning (docs/PLATFORM_PROVISIONING.md): Technology stack, hosting, monitoring strategy
- Project Plan (docs/PROJECT_PLAN.md): 10-month roadmap with effort estimates

## Sample Data

Includes pre-loaded data:
- 2 Movies (Spider-Man, Avengers)  
- 2 Theatres in Mumbai
- Multiple shows with different timings
- Seat layouts with pricing
- Sample theatre partner data

## Tech Stack

- Java 21 + Spring Boot 3.2
- H2 Database (development), PostgreSQL (production)
- JPA/Hibernate + Bean Validation
- Maven build system
- Modular monolith architecture

