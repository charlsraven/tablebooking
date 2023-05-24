# table booking

# Многомодульное web приложение Spring Boot + Spring Data

## Модули:

### tablebooking-api:

модуль, содержащий доменную модель (различные классы, интерфейсы, типы данных, правила, контракты), которые используются
в приложении.
Модуль дает возможность описывать абстрактные типы, используемые как входные и выходные данные в различных контроллерах,
сервисах и репозиториях.
Этот модуль также может содержать отдельный модуль под API документацию, которая описывает, какие
данные можно получить, какие запросы совершить или как их использовать

### tablebooking-core:

модуль, содержащий основную бизнес логику и DAL (data access layer), то есть код, который отвечает за взаимодействие с
базой данных или внешними сервисами. Этот модуль может содержать код таких функций, как обработка бизнес-логики (
например, блок бронирования столиков и предсказания количества посетителей), обработка запросов к базе данных, механизмы
кэширования или отображение состояния контроллеров в различных частях приложения.

### tablebooking-gateway:

модуль, предоставляющий точку входа в приложение. Он может быть реализован с помощью фреймворков веб-разработки (в
данном случае thymeleaf), который предоставляет инструмент для разработки пользовательского интерфейса (UI), отображения
данных и взаимодействия с браузером. Этот модуль может содержать логику перенаправления запросов, фильтрации, обработки
ошибок и т.д.

## Литература:

https://temofeev.ru/info/articles/sozdanie-mnogomodulnogo-gradle-proekta-springboot-angular-v-idea/
https://github.com/kpratikin/Hotel-Reservation-System-Database-/tree/master
https://www.planyo.com/learn-more.php
https://oleg0potapov.medium.com/how-to-design-a-booking-system-to-avoid-overlapping-reservation-fe17194c1337
https://habr.com/ru/articles/265061/
https://www.baeldung.com/jpa-default-column-values

https://dev.to/francescoxx/java-crud-rest-api-using-spring-boot-hibernate-postgres-docker-and-docker-compose-5cln

