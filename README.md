# arrow-app

Здесь вы можете видеть пример простого REST API приложения c использованием:
- Java
- Maven
- Spring Boot
- Hibernate
- PostgreSQL
- Docker compose

Команды выполняются в корне проекта.
-----------------------------------------------------------------------
- Сбилдить проект:
### `$ mvn spring-boot:run`

- Стянуть образы для докера и запустить контейнеры c PostgreSQL и PGAdmin:
### `$ docker-compose up`
### `$ docker-compose start`

PGAdmin:
-----------------------------------------------------------------------
[http://localhost:5555/]()
- Login: admin@admin.com
- Password: 12345

Фронтенд можно взять здесь:
-----------------------------------------------------------------------
[https://github.com/nelsonstepnog/arrow-app-front.git]()

Проверка запросами JS в консоли devTools, данные сохраняются в БД:
-----------------------------------------------------------------------
1.) Создать одного пользователя:

fetch('/users', 
  { 
    method: 'POST', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ 'name': '1 user create', 'age': '21', 'country': 'USA' })
  }
).then(result => result.json().then(console.log))

-----------------------------------------------------------------------
2.) Получить пользователя:

fetch('/users/1').then(response => response.json().then(console.log))

-----------------------------------------------------------------------
3.) Обновить одного пользователя:

fetch('/users/1', 
  { 
    method: 'PUT', 
    headers: { 'Content-Type': 'application/json' }, 
    body: JSON.stringify({ 'name': '1 user update', 'age': '16', 'country': 'KAZ' })
  }
).then(result => result.json().then(console.log));

-----------------------------------------------------------------------
4.) Удалить одного пользователя:

fetch('/users/1', { method: 'DELETE' }).then(result => console.log(result))

-----------------------------------------------------------------------


Проверка запросами в терминале через CURL, данные сохраняются в БД:
-----------------------------------------------------------------------
1.) CREATE

curl -X POST "http://localhost:8080/users" -H 'Content-Type: application/json' -d '{ "name": "1 user create", "age": "34", "country": "USA" }'

2.) VIEW list(GET)

curl -X GET "http://localhost:8080/users" -H 'Content-Type: application/json'

3.) VIEW one(GET)

curl -X GET "http://localhost:8080/users/1" -H 'Content-Type: application/json'

4.) UPDATE

curl -X PUT "http://localhost:8080/users/1" -H 'Content-Type: application/json' -d '{ "name": "1 user update", "age": "22", "country": "Russia" }'

5.) DELETE

curl -X DELETE "http://localhost:8080/users/1" -H 'Content-Type: application/json'

