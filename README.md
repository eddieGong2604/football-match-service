# Football Match Service

This Spring Boot application exposes several endpoint capable of adding and reading information of a football association, including Countries, Leagues, Teams, and Matches.

### How to run

Make sure Docker is installed.

Clone this repository to your local machine, change current directory into /scripts folder and run deploy.sh with argument "football-match-service".

```
git clone https://github.com/eddieGong2604/football-match-service.git
cd football-match-service/scripts
chmod +x deploy.sh
./deploy.sh football-match-service
```

The service will be running at http://localhost:8080.

### API Docs

Swagger is used to generate API Docs from Spring controllers. Once the application is up, API Docs will be available at http://localhost:8080/swagger-ui.html.
