# mvc_sec_sql
This demo project uses spring-mvc, spring-security, jpa-provider hibernate, mysql as docker-container, in-memory-database h2 and template-engine thymeleaf.

![home](https://user-images.githubusercontent.com/26798159/40691610-2112d592-63ad-11e8-9532-5c8bae9609b7.png)

## Demo

### In-memory h2-db
- Set `spring.profiles.active=h2` in `application.properties`.
- Enable `@Profile("h2")` in `BootstrapDataConfig.class` (if commented out)
- Run `mvn spring-boot:run` or maven wrapper. Navigate to `http://localhost:8080/`.
- Register a new user or log in with one of the two test-accounts (username/pw):
    - mary/jane (user)
    - joe/doe (admin)
    
### Persistent mysql-db
- Set `spring.profiles.active=persist` in `application.properties`.
- Run `docker-compose up -d` in `resources`-folder.
- Initialize database:
    - Run `db_create.sql` to set up tables (from preferred db-client).
    - Run `data-h2.sql` to insert roles.
    - Comment out `@Profile("h2")` in `BootstrapDataConfig.class` to insert bootstrap-data (only for first run).
- Run `mvn spring-boot:run` or maven wrapper. Navigate to `http://localhost:8080/`.
- Register or log in with test-accounts ...