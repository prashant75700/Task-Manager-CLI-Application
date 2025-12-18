# Task Master - Premium Web Application

A modern, full-stack Task Management application built with React and Node.js.

## Features
- ✨ **Beautiful UI**: Modern dark-themed glassmorphism design.
- ⚡ **Real-time Interaction**: Instant updates and responsive controls.
- 📊 **Dashboard Analytics**: Track your progress with real-time statistics.
- 🚀 **Full-Stack Architecture**: React Frontend + Node.js/Express Backend.

## Technology Stack
- **Frontend**: React, Vite, CSS3 (Variables & Grid)
- **Backend**: Node.js, Express
- **State Management**: React Hooks

## How to Run

### 1. Start the Backend (Option A: Node.js - Recommended for Quick Start)
Open a terminal in the `backend-node` directory:
```bash
cd backend-node
npm install
node server.js
```
The server will start on `http://localhost:5000`.

### 1. Start the Backend (Option B: Java Spring Boot - For Java Developers)
**Prerequisites:** You must have JDK 17+ and Gradle installed.

Open a terminal in the `backend` directory:
```bash
cd backend
gradle bootRun
```
*Note: The Java backend has been updated to support the same API endpoints as the Node.js version, including the `toggle` and `stats` features.*

### 2. Start the Frontend
Open a NEW terminal in the root directory:
```bash
cd frontend
npm install
npm run dev
```
The application will open at `http://localhost:5173`.

## Project Structure
- `frontend/`: React + Vite web application.
- `backend-node/`: Lightweight Node.js Express backend (Active).
- `backend/`: Java Spring Boot backend (Reference implementation).

