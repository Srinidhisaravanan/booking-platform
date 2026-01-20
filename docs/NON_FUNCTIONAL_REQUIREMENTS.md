# Non-Functional Requirements (NFR)

## Overview

This document outlines how the platform addresses critical non-functional requirements such as scalability, availability, security, reliability, integrations, payments, and compliance. The focus is on design choices and trade-offs rather than full implementation.

## Scalability

### Goals
- Support concurrent users across multiple cities
- Handle peak booking traffic during popular show releases
- Maintain low latency for browsing and booking operations

### Design Decisions
- API services are stateless and horizontally scalable behind a load balancer
- Read-heavy endpoints (browse shows) use indexed queries and optional caching
- Database scaling is achieved using read replicas and partitioning by show or city
- Asynchronous workflows (notifications, analytics) can be offloaded via message queues

### Trade-offs
- Strong consistency is maintained for booking, which limits write throughput slightly but ensures correctness

## Availability (Target: 99.99%)

### Goals
- Minimize downtime and booking disruptions
- Gracefully degrade during partial failures

### Design Decisions
- Database deployed in multi-AZ configuration
- Health checks and auto-scaling groups ensure rapid recovery
- Circuit breakers protect downstream integrations
- Non-critical features can be degraded during peak load

## Transaction Management & Reliability

### Goals
- Prevent double booking
- Recover safely from partial failures

### Design Decisions
- Pessimistic row-level locking ensures exclusive seat ownership
- Idempotency keys prevent duplicate bookings
- Lock expiry releases abandoned seats
- Compensation logic handles payment failures

## Integration with Theatre Systems

### Goals
- Support onboarding of theatres with existing IT systems
- Enable automated synchronization of show schedules and inventory

### Design Decisions
- REST-based integration APIs for onboarding and updates
- Bulk ingestion endpoints for show data
- Event-based synchronization for real-time updates
- Validation pipelines to normalize data formats

## Payment Integration

### Goals
- Support secure and reliable ticket payments
- Handle retries and reconciliation

### Design Decisions
- External payment gateway integration using asynchronous callbacks
- Booking remains in PENDING state until payment confirmation
- Webhooks confirm payment status
- Idempotency ensures safe retries

## Security (OWASP Top 10)

### Goals
- Protect against common application vulnerabilities
- Secure sensitive user and payment data

### Design Decisions
- OAuth2/JWT authentication
- Role-based authorization
- Input validation and sanitization
- Rate limiting and throttling
- Secrets stored in secure vaults
- Audit logging for critical actions

## Compliance

### Goals
- Meet regulatory and privacy obligations

### Design Decisions
- PCI-DSS compliance for payment handling
- GDPR compliance for personal data handling
- Data retention and deletion policies
- Encrypted data at rest and in transit

## Observability & Monitoring

### Goals
- Detect issues quickly
- Measure system health and performance

### Design Decisions
- Centralized logging
- Metrics for booking success, latency, error rates
- Alerts for SLA breaches
- Dashboards for operational visibility

## Performance Targets (Sample KPIs)

- API latency < 200ms for reads
- Booking success rate > 99.9%
- Seat lock expiry < 5 minutes
- System uptime ≥ 99.99%