## Sock Warehouse Automation Application

#### Developer:
[Korolchuk Vladislav](https://github.com/VladislavKorolchuk)
***
### Application functionality ###
**The application for accounting socks in the warehouse has been implemented. Implemented in the application:**

- coming and going of socks;
- number of socks of a certain color and percentage of cotton.


The application interface is presented as a REST API.

***
### List of URL HTTP Methods:
1. POST /api/socks/income

The arrival of socks to the warehouse.

Request parameters are passed in the request body as a JSON object with the following attributes:

- color — socks color;
- cottonPart — percentage of cotton - a number from 0 to 100;
- quantity — the number of pairs of socks, the number is greater than 0..

HTTP request result:

- HTTP 200 — ok;
- HTTP 400 — Query parameters are missing or are not in the correct format;
- HTTP 500 — server error.

2. POST /api/socks/outcome

Release of socks from the warehouse.

3. GET /api/socks

The total number of socks in stock that match those passed in the query parameters.

Request parameters:

- color — socks color;
- operation — cotton content comparison operator (moreThan, lessThan, equal);
- cottonPart — cotton percentage value.

HTTP request result:

- HTTP 200 — ok;
- HTTP 400 — Query parameters are missing or are not in the correct format;
- HTTP 500 — server error.

***
### Technology stack: ###

- Programming language *Java*
- Database *PostgreSQL*
- *Spring Framework*,
- *Spring Boot*,
- *Hibernate*, 
- *Liquibase*,
- *SpringDoc*,
- *Swagger UI*
- *Docker*
- *Mockito*

***

### Socks Warehouse API: ###

http://localhost:8080/swagger-ui/index.html