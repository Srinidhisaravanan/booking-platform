# Movie Booking Platform - Architecture Guide

## What We're Building

A scalable movie booking platform solving real-world challenges:

- **Concurrent bookings** - thousands trying to book the same seat simultaneously
- **Multi-tenant system** - supporting diverse theatre partners with different needs
- **Real-time availability** - accurate seat status across all channels
- **Transactional integrity** - reliable payment processing with zero data loss

## System Architecture

API Gateway (Load Balancer + Security)
  |
Spring Boot Application (Modular Monolith)
  - User Management (Auth, Profiles, Partners)
  - Movie Catalog (Content, Shows, Search)
  - Theatre Network (Venues, Screens, Seats)
  - Booking Engine (Reservations, Pricing)
  - Payment & Notifications
  
PostgreSQL + Redis + File Storage

## Why Modular Monolith?

Benefits for booking transactions:
- ACID compliance - seat booking requires atomic operations
- Performance - no network latency between modules  
- Simplicity - single deployment, easier debugging
- Future-ready - clear module boundaries enable microservice extraction

Module isolation principles:
- Clean interfaces between business domains
- Minimal cross-module dependencies
- Separate database schemas per module

## Core Modules

### User Management
- Customer registration, authentication, profiles
- Theatre partner onboarding and verification
- Role-based access control (Customer, Partner, Admin)

### Theatre Network
- Venue registration and management
- Screen configuration (IMAX, Dolby, Regular)
- Seat layout and pricing setup

### Movie Catalog  
- Movie information and content management
- Show scheduling across theatres
- Search and filtering capabilities

### Booking Engine
- Seat availability and locking mechanisms
- Pricing calculations with discount rules
- Payment processing and confirmation

### Support Services
- Payment gateway integration
- Notification delivery (Email, SMS)
- Audit logging and analytics

## Booking Flow

Customer Journey:
1. Discovery: Browse movies → filter by city, date, theatre
2. Selection: Choose show → select seats → temporary lock (10 mins)
3. Pricing: Calculate total with discounts (50% 3rd ticket, 20% afternoon)
4. Payment: Process transaction → confirm or release seats
5. Confirmation: Generate tickets → send notifications → update analytics

**Technical Implementation:**
```java
@Transactional
public BookingResponse createBooking(BookingRequest request) {
    // 1. Acquire distributed locks on selected seats
    seatLockService.lockSeats(request.getSeats(), request.getUserId());
    
    // 2. Validate availability and calculate pricing
    validateAvailability(request.getSeats());
    BigDecimal total = pricingService.calculate(request);
    
    // 3. Process payment and create booking atomically
    Payment payment = paymentService.processPayment(total, request.getPaymentDetails());
    Booking booking = bookingRepository.save(createBooking(request, payment));
    
    // 4. Confirm seat reservations and release locks
    seatService.confirmReservation(request.getSeats(), booking.getId());
    return mapToResponse(booking);
}
```

## Concurrency & Reliability

**Seat Locking Strategy:**
```java
// Redis distributed lock implementation
public boolean lockSeat(String showId, String seatId, String userId) {
    String lockKey = "seat_lock:" + showId + ":" + seatId;
    return redisTemplate.execute(script, List.of(lockKey), userId, "600"); // 10-min TTL
}
```

**Database Consistency:**
```sql
-- Optimistic locking prevents race conditions
UPDATE show_seats 
SET status = 'BOOKED', version = version + 1, booked_by = ?
WHERE show_id = ? AND seat_id = ? AND status = 'AVAILABLE' AND version = ?
```

**Failure Scenarios & Recovery:**
- **Payment timeout**: Auto-release seat locks after expiry
- **Database failure**: Request retry with idempotency keys
- **Gateway errors**: Graceful fallback with user notifications



## Technology Stack

Application Layer:
- Java 21 + Spring Boot 3.2 (Enterprise-ready, strong ecosystem)
- Maven (Dependency management)
- JUnit 5 + Testcontainers (Testing strategy)

Data Layer:
- H2 Database (development), PostgreSQL (production)
- JPA/Hibernate for ORM
- Optional Redis for caching

Infrastructure:
- Spring Boot application deployment
- Database configuration (H2 for development)
- Caching layer integration ready

---

## Summary

This modular monolith architecture delivers:

- **Strong consistency** for transactional booking workflows
- **Clear module boundaries** enabling future microservice extraction
- **Scalable foundation** supporting growth from startup to enterprise scale  
- **Production-ready** design patterns and practices

The design prioritizes correctness and simplicity while maintaining flexibility for future architectural evolution.

For detailed information on scalability, availability, security, compliance, and performance targets, refer to the [Non-Functional Requirements (NFR) document](docs/NON_FUNCTIONAL_REQUIREMENTS.md).