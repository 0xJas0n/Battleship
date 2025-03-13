# Battleship

This guide will help you set up and run the application.

## Links

| Page            | URL                                                                                                   |
|-----------------|-------------------------------------------------------------------------------------------------------|
| App             | [localhost:30400]( http://localhost:30400)                                                            |
| Gateway Swagger | [localhost:30400/webjars/swagger-ui/index.html](http://localhost:30400/webjars/swagger-ui/index.html) |

### Game flow

- Create Game
- Create Player 1
  - Enter player name
  - Place ships
- Create Player 2
   - Enter player name
   - Place ships
- Click on the top board to select which cell to shoot
- Red cells mark hits and orange cells mark misses

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

| Service        | Port  |
|----------------|-------|
| Gateway        | 30300 |
| Game service   | 30301 |
| Player service | 30302 |
| Board service  | 30303 |

### Stopping the Databases

To stop all the PostgreSQL containers, run:
```bash
docker-compose down
```
