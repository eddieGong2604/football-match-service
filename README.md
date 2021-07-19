# Football Match Service

This Spring Boot application exposes an endpoint capable of searching matches in a football league system based on league, country, team, and season.

### How to run

Clone this repository to your local machine, change current directory into /scripts folder and run deploy.sh.

```
chmod +x deploy.sh
./deploy.sh football-match-service
```

The service will be running at http://localhost:8080.

### API Docs

Swagger is used to generate API Docs from Spring controllers. Once the application is up, API Docs will be available at http://localhost:8080/swagger-ui.html.
