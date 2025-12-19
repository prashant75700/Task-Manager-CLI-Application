# Deployment Guide for Task Manager

This guide explains how to deploy your **Task Manager** application so anyone on the internet can access it.

Since your app has two parts (Frontend & Backend), we will deploy them separately using free services.

## Prerequisites
- A **GitHub Account**.
- Your code pushed to a GitHub repository.

---

## Part 1: Deploy the Backend (Java Spring Boot)
We will use **Render** (render.com) with Docker to deploy your Java application.

1.  **Sign up/Login** to [Render.com](https://render.com) using your GitHub account.
2.  Click **"New +"** and select **"Web Service"**.
3.  Connect your GitHub repository.
4.  Configure the service:
    *   **Name:** `task-manager-api` (or unique name)
    *   **Root Directory:** `backend`
    *   **Environment:** Docker (It should detect the Dockerfile automatically)
    *   **Plan:** Free
5.  Click **"Create Web Service"**.
6.  **Wait** for the deployment to finish (It may take a few minutes to build the Java Gradle Docker image).
    Render will give you a URL like: `https://task-manager-api.onrender.com`
    **COPY THIS URL.**

---

## Part 2: Deploy the Frontend (React)
We will use **Vercel** (vercel.com) because it is optimized for Vite/React apps.

1.  **Sign up/Login** to [Vercel.com](https://vercel.com) using GitHub.
2.  Click **"Add New..."** -> **"Project"**.
3.  Import your GitHub repository.
4.  Configure the project:
    *   **Framework Preset:** Vite (should handle automatically)
    *   **Root Directory:** Click "Edit" and select `frontend`.
5.  **Environment Variables:**
    *   Expand the "Environment Variables" section.
    *   Key: `VITE_API_URL`
    *   Value: `https://task-manager-api.onrender.com/api` (Replace the URL with your actual Render URL).
6.  Click **"Deploy"**.

---

## Part 3: Verify
Once Vercel finishes, it will give you a Domain (e.g., `task-manager-app.vercel.app`).
Click it to open your live application!

It should automatically connect to your Render backend and show your tasks.
