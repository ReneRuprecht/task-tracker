# 📋 Task Tracker – Event-driven Microservices mit Spring Boot & Kafka

Ein Backend-Projekt zur praxisnahen Umsetzung moderner Microservice-Architekturen mit Fokus auf Event-Driven Design, Apache Kafka und Hexagonal Architecture.

Ziel ist es, ein realitätsnahes System zu bauen, das lose gekoppelte Services, klare Domänenlogik und robuste Event-Kommunikation kombiniert.

## 🎯 Projektziel

Dieses Projekt dient dazu, folgende Konzepte praktisch zu verstehen:

- Hexagonale Architektur
- Event-driven Microservices mit Kafka
- Asynchrone Kommunikation zwischen Services
- Domain-driven Design in der Praxis
- Aufbau wartbarer und testbarer Backend-Systeme

## 🧱 Systemübersicht

Das System besteht aus mehreren unabhängig deploybaren Services:

### 👤 User Service

Verantwortlich für:

- Verwaltung von Benutzern
- Veröffentlichung von Domain Events (z. B. UserCreated)
- Bereitstellung der User-Domäne als „Source of Truth“

### 📋 Task Service

Verantwortlich für:

- Verwaltung von Tasks
- Zuweisung von Tasks an User
- Aufbau eines lokalen User Read Models (Snapshot)
- Konsum von User Events aus Kafka

## 🔄 Event-driven Kommunikation

Die Services kommunizieren ausschließlich über Kafka Events.

Beispiel Flow:
1. User wird im UseCase erstellt
2. Domäne erzeugt UserCreatedEvent
3. Event wird über Outbound Port publiziert
4. Kafka Adapter übernimmt das Senden
5. Task Service konsumiert Event über Inbound Adapter
6. Task Service speichert User Snapshot

## 🛠️ Tech Stack
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Apache Kafka
- PostgreSQL
- Docker & Docker Compose

## 🚧 Aktueller Stand

### 👤 User Service
- ✔ Erstellung implementiert
- ⏳ Abfragen
- ⏳ Aktualisierung
- ⏳ Kafka Integration

### 📋 Task Service
- ✔ Erstellung implementiert
- ✔ Abfrage implementiert
- ✔ Aktualisierung implementiert
- ⏳ Kafka Integration



