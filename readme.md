# iac webshop back-end

![CI](https://github.com/noudadrichem/iac-webshop-backend/workflows/CI/badge.svg)

Install packages
```bash
mvn clean install
```

Run application
```bash
mvn package && docker-compose up --build
```

## Postgres
Runs on port 5432

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
- testing/iets-dat-je-test
- etc.

Via Github actions wordt `mvn test` gerunned en wordt `mvn package` gerunned om een ~artifact te genereren~.

### Postman Scripts
In root :: 'iac-webshop-backend.postman_collection'
