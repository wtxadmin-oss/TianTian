# Java JWT Token Lifecycle Lab

A compact Java 8 project for exploring the JWT lifecycle: user authentication, token issuance, signature validation, expiration checks, and token refresh.

## What it demonstrates

- HMAC-signed JWT creation with `jjwt`
- Subject, issue time, expiration time, and custom claims
- Token validation and username extraction
- A simple refresh flow for valid tokens
- Separation between CLI, service, model, and utility layers

## Project structure

```text
src/main/java/com/example/
├── App.java                  # CLI entry point
├── controller/              # Console interaction
├── service/TokenService.java
├── model/Token.java
└── util/JwtTokenUtil.java   # JWT encode/decode operations
```

## Run locally

Requirements: JDK 8+ and Maven 3.8+.

Set a signing secret of at least 32 bytes before starting the application:

```bash
export JWT_SECRET='replace-with-a-random-secret-at-least-32-bytes'
mvn clean test
mvn exec:java -Dexec.mainClass=com.example.App
```

## Design notes

This repository intentionally keeps persistence and transport concerns small so the token lifecycle remains easy to inspect. Users are stored in memory and the interface is command-line based.

For production use, replace the in-memory credential store with a database-backed identity provider, hash passwords, rotate signing keys, use short-lived access tokens with dedicated refresh tokens, and never print tokens to application logs.

## Tech stack

- Java 8
- Maven
- JJWT 0.11.5
- JUnit 5
