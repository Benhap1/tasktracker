# Task Tracker Application

![Java](https://img.shields.io/badge/Java-17-brightgreen.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-brightgreen.svg)
![Spring WebFlux](https://img.shields.io/badge/Spring%20WebFlux-Reactive-brightgreen.svg)
![MongoDB](https://img.shields.io/badge/MongoDB-Database-brightgreen.svg)
![MapStruct](https://img.shields.io/badge/MapStruct-Mapping-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-brightgreen.svg)

## Overview

The **Task Tracker** application is a reactive system for managing tasks and users, built using **Spring Boot** and **Spring WebFlux**. It leverages **MongoDB** as the database and uses **MapStruct** for mapping between entities and DTOs. This application supports full CRUD operations for both users and tasks, with additional functionalities to manage task observers.

## Features

- **Reactive Programming**: Uses `Mono` and `Flux` for non-blocking, asynchronous processing.
- **CRUD Operations**:
    - Manage Users (Create, Read, Update, Delete)
    - Manage Tasks (Create, Read, Update, Delete)
- **Task Observers**: Add and manage observers for tasks.
- **Entity Mapping**: Uses MapStruct for automatic mapping between entities and DTOs.
- **Database**: Integration with MongoDB in a reactive context.

## Technologies Used

- **Java 17**
- **Spring Boot 2.7.5**
- **Spring WebFlux**
- **MongoDB**
- **MapStruct**
- **Maven**

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB (running locally or remotely)

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Benhap1/tasktracker.git
   cd tasktracker


### API Endpoints
#### User API
Get all users: GET /users

Get user by ID: GET /users/{id}

Create user: POST /users

Update user: PUT /users/{id}

Delete user: DELETE /users/{id}


#### Task API
Get all tasks: GET /tasks

Get task by ID: GET /tasks/{id}

Create task: POST /tasks

Update task: PUT /tasks/{id}

Add observer to task: POST /tasks/{taskId}/observers/{observerId}

Delete task: DELETE /tasks/{id}

