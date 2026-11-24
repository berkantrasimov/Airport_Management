# Airport Management System (Java)

An object-oriented console application written in Java SE for managing airport operations, aircraft fleets, flight scheduling, crew assignments, gate allocation, and passenger ticketing.

## Architecture & Domain Model
- **Aircraft Hierarchy & Interfaces:**
  - `Aircraft` (base class) extended by `PassengerPlane` and `CargoPlane`
  - `Flyable` and `Loadable` interfaces defining flight and payload behaviors
- **Operations & Scheduling:**
  - `Airport`, `Gate`, `Schedule`, and `Flight` (with `FlightStatus` and `FlightType` enums)
- **Personnel & Ticketing:**
  - `Crew`, `Pilot`, `Passenger`, and `Ticket`
- **Application Layer:**
  - `SystemManager` — Coordinates CRUD operations, validations, and file-based persistence
  - `Menu` & `Main` — Interactive CLI interface

## Key Features
- **Flight & Gate Scheduling:** Create, update, and track flights with status transitions and gate assignments.
- **Fleet & Crew Management:** Assign aircraft (`PassengerPlane` / `CargoPlane`), pilots, and crew members to scheduled flights.
- **Passenger Bookings:** Issue and manage passenger tickets with seat/capacity tracking.
- **Persistent Storage:** Saves and restores system state across sessions via Java File I/O.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher

### Build & Run
```bash
javac *.java
java Main
