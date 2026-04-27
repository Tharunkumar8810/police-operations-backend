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

## 4. Data Models (Database)

I used PostgreSQL. Here are the main tables:
- `users`: stores email, password, and roles.
- `patrols`: details about the patrol (area, start/end time, status).
- `assignments`: maps the patrol to the officer. Tracks when they acknowledge it and complete it.
- `locations`: logs the lat/long of officers during a patrol.
- `alerts`: tracks emergency SOS or missed check-ins.

## 5. How to Test & Run It

### Option 1: Use the Live Hosted API (Zero-Config)
The easiest way to test this project is to use the **Live API Base URL** provided at the top of this document. The application is fully hosted on **Render** and connected to a live **Neon PostgreSQL database**. You don't need to run or install anything!

### Option 2: Run it Locally
If you prefer to run the code on your own machine, you can do so using Docker or Maven. 
*(Note: You must have a local PostgreSQL database running and you will need to update `application.properties` with your own database credentials before starting).*

**Using Docker (Recommended)**
1. Build the image: `docker build -t copmap-backend .`
2. Run the container: `docker run -p 8080:8080 copmap-backend`

**Manual Run (Maven/Java)**
1. Build the project:
   `mvn clean package -DskipTests`
2. Run the jar file:
   `java -jar target/police-operations-backend-0.0.1-SNAPSHOT.jar`

## 6. Video Walkthrough
[Add your video link here]
