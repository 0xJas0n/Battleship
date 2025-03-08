# Battleship

This guide will help you set up and run the application.

## Usage

The game will be available on [localhost:8080](http://localhost:8080)

## Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Starting the Database

1. Open a terminal in the project root directory
2. Run the following command to start the PostgreSQL container:
   ```bash
   docker-compose up -d
   ```

This will start the PostgreSQL database with the following configuration:
- Host: localhost
- Port: 5432
- Username: postgres
- Password: postgres

### Stopping the Database

To stop the PostgreSQL container, run:
```bash
docker-compose down
```
