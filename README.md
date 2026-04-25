# CopMap Backend Project

**Live API Base URL:** [Add your hosted link here, e.g., https://your-app.onrender.com/api]

This is the backend for the CopMap assignment. It handles the core police workflows like patrolling and nakabandi using Spring Boot and Postgres.

## 1. Research & Understanding

**Patrolling:** 
This is basically when officers are sent out to a specific area to keep an eye on things, maintain law and order, and react to crimes quickly. It's usually a dynamic route and they need to constantly report where they are.

**Bandobast & Nakabandi:**
- Bandobast is more static. It's planned for big events, protests, or VIP movements where lots of officers just guard a specific zone.
- Nakabandi means setting up checkpoints on roads to check cars, usually to catch suspects or for routine security checks.

**How it works here:**
The SHO (Station House Officer) is the one doing the planning. They create the patrol in the system and assign officers. The officers execute it by going to the location, acknowledging the assignment, and updating their GPS. Supervisors can just watch the live feeds and see if anything goes wrong.

## 2. Actors and Roles

I used standard JWT auth with role-based access.

- **SHO:** Plans operations. Can create patrols, assign officers, and resolve alerts.
- **OFFICER:** The person actually on the ground. They can accept their assignments, send live locations, and trigger SOS alerts if there's an emergency.
- **SUPERVISOR:** Higher rank, mostly for monitoring the situation.

## 3. System Design

The project uses standard Spring Boot layer architecture (Controllers -> Services -> Repositories). 

**What I built:**
- JWT login and registration flows
- Patrol CRUD operations
- Assignment mapping (linking officers to patrols)
- Live location tracking. I used WebSockets (`SimpMessagingTemplate`) for this so the frontend can get live updates on `/topic/patrol-{id}` without polling the server constantly.
- Alerting system for SOS or manual alerts.

**Trade-offs & what I skipped:**
- I didn't use microservices. A monolith makes much more sense for this scale and is easier to test.
- I skipped Redis cache because the Postgres DB is fast enough for this MVP, and I wanted to keep the setup simple.
- Skipped Dockerizing to keep the run instructions simple (just maven and java). 

## 4. Data Models

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
2. Make sure your postgres username/password is `postgres`/`postgres`. If not, just change it in the `application.properties` file.
3. Build the project:
   `mvn clean package -DskipTests`
4. Run the jar:
   `java -jar target/SecurityWithJwtTemplate-0.0.1-SNAPSHOT.jar`

The server will run on `http://localhost:8080`.

## 6. Video Walkthrough
[Add your video link here]
