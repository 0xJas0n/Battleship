# Battleship

This guide will help you set up and run the application.

## Links

| Page            | URL                                                                                                   |
|-----------------|-------------------------------------------------------------------------------------------------------|
| Gateway Swagger | [localhost:30400/webjars/swagger-ui/index.html](http://localhost:30400/webjars/swagger-ui/index.html) |

### Game flow

- Create Game
- Create Player 1
- Create Player 2
- Add Players to Game
- Place Ships for each Player
- Shoot opponent board

## Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Starting the Databases

1. Open a terminal in the project root directory
2. Run the following command to start all PostgreSQL containers:
   ```bash
   docker-compose up -d
   ```

This will start the PostgreSQL databases on following ports:

| Service         | Port  |
|-----------------|-------|
| Gateway service | 30300 |
| Game service    | 30301 |
| Player service  | 30302 |
| Board service   | 30303 |

### Start the application

Start all 4 services

### Stopping the Databases

To stop all the PostgreSQL containers, run:
```bash
docker-compose down
```

### Circuit breaker

To check on the Circuit breakers you can check the [Status page](http://localhost:30400/actuator/circuitbreakers)


### Messaging

On game creation messaging will handle the logging from the game service back to the gateway service.
