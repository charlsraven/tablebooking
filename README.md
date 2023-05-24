# table booking

# Многомодульное web приложение Spring Boot + Spring Data

## Модули:

### tablebooking-api:
  Хранит доменную модель и интерфейсы
  
  
### tablebooking-core:
  Основная библиотека с бизнес-логикой и DAL.
  
  
### tablebooking-gateway:
  Gateway для бизнес-логики. Использует university-core как библиотеку. Написан с приминениеи шаблонизатора thymeleaf. Единственный имеет точку входа (main).

## Сторонние сервисы:

PostgreSQL 14.2

Базу удобно поднимать в докере: 
docker run --name postgresql -d -p 5432:5432 -e POSTGRES_USER=university_user -e POSTGRES_PASSWORD=university_pass -e POSTGRES_DB=university postgres:14.2


## Литература:
https://temofeev.ru/info/articles/sozdanie-mnogomodulnogo-gradle-proekta-springboot-angular-v-idea/
https://github.com/kpratikin/Hotel-Reservation-System-Database-/tree/master
https://www.planyo.com/learn-more.php
https://oleg0potapov.medium.com/how-to-design-a-booking-system-to-avoid-overlapping-reservation-fe17194c1337
https://habr.com/ru/articles/265061/
https://www.baeldung.com/jpa-default-column-values

https://dev.to/francescoxx/java-crud-rest-api-using-spring-boot-hibernate-postgres-docker-and-docker-compose-5cln

