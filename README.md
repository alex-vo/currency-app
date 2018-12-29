This is a source code repository of the Currency Application.
To run tests:
```
./gradlew clean test
```
To run application:
```
./gradlew clean bootRun
```
Currency retrieval endpoint will be available at /currency/{code}, e. g.
```
http://localhost:8080/currency/EUR
```
Request log will be available at
```
http://localhost:8080/log
```