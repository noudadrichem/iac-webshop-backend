# iac webshop back-end

![CI](https://github.com/noudadrichem/iac-webshop-backend/workflows/CI/badge.svg)

Install packages
```bash
mvn clean install
```

Run application
```bash
mvn spring-boot:run
```

## Postgres
Start docker
```docker-compose up```

PGAdmin op localhost:80
<br/>
#### Login met
username: admin@root.com <br/>
password: SuperSecret

# CI/CD
2 main branches, master and development.
The branch names zeggen al wat ze doen.

We werken met feature branches.

- feature/iets-dat-je-doet
- fix/iets-dat-je-fixed
- rebase/iets-dat-je-rebased
- etc.

Via Github actions wordt `mvn test` gerunned en wordt `mvn package` gerunned om een artifact te genereren.
