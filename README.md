# CopMap Backend Project

**Live API Base URL:** [Add your hosted link here]

This is the backend for the CopMap assignment. It handles the core police workflows like patrolling and nakabandi using Spring Boot and Postgres.

## 1. Problem Understanding

**What is Patrolling?**
Patrolling is when officers move around a specific area to keep it safe and report their location.

**What is Bandobast / Nakabandi?**
Bandobast is when officers stand guard at a specific event. Nakabandi is when they set up a checkpoint on a road to check vehicles.

**How the flow works:**
1. **SHO** (Station House Officer) creates a patrol in the system and assigns an officer to it.
2. **Officer** sees the assignment, accepts it, and goes to the location.
3. **Officer** updates their live location while patrolling.
4. **Supervisor / SHO** can monitor everything.
5. **Officer** completes the patrol.

## 2. Actor & Role Design

I used standard JWT auth with role-based access.

- **SHO:** The boss. They create patrols, assign officers, and check alerts.
- **OFFICER:** The person on the ground. They accept assignments, send their GPS location, and can raise an SOS alert if there is trouble.
- **SUPERVISOR:** Higher rank, just for monitoring the system.

## 3. System Design

The project uses standard Spring Boot layer architecture (Controllers -> Services -> Repositories). 

**What I implemented:**
- JWT login and security.
- CRUD APIs for Patrols, Assignments, and Alerts.
- Live location tracking using WebSockets (so the frontend gets live updates without refreshing).

**Trade-offs & what I skipped:**
- I didn't use microservices. A monolith makes much more sense for this scale and is easier to test.
- I skipped Redis cache because the Postgres DB is fast enough for this MVP, and I wanted to keep the setup simple.
- Skipped Dockerizing to keep the run instructions simple (just maven and java). 

## 4. Data Models (Database)

I used PostgreSQL. Here are the main tables:
- `users`: stores email, password, and roles.
- `patrols`: details about the patrol (area, start/end time, status).
- `assignments`: maps the patrol to the officer. Tracks when they acknowledge it and complete it.
- `locations`: logs the lat/long of officers during a patrol.
- `alerts`: tracks emergency SOS or missed check-ins.

## 5. How to run it

You just need Java 21 and PostgreSQL running on your machine.

1. Open pgAdmin or psql and create the database:
   `CREATE DATABASE policedb;`
2. Update `application.properties` if your postgres username/password is not `postgres`/`postgres`.
3. Build the project:
   `mvn clean package -DskipTests`
4. Run the jar file:
   `java -jar target/SecurityWithJwtTemplate-0.0.1-SNAPSHOT.jar`

The server will run on `http://localhost:8080`.

## 6. Video Walkthrough
[Add your video link here]
