# Movie Ticket Booking Platform

## Overview
This project demonstrates a simplified backend for an online movie ticket booking system.
It implements:
- Browse shows by movie and city
- Book seats for a show (transaction-safe)

## Architecture
- Spring Boot REST API
- MySQL/Postgres (design)
- Redis (optional lock)
- AWS (design)

## API
GET /movies/{movieId}/shows?city=Bangalore&date=YYYY-MM-DD
POST /bookings

## Booking Flow
1. Lock seats
2. Validate availability
3. Create booking
4. Commit transaction

## Non Functional
- Scalability: Horizontal scaling, caching
- Availability: Multi-AZ DB, health checks
- Security: JWT, validation, rate limits
- Payments: External gateway (design)
- Compliance: PCI, GDPR

## Skipped
- UI
- Bulk booking
- Payment integration
