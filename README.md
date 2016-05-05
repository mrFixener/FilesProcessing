### Настройка базы данных PostgreSQL:

--Шаг 1
--Создать БД hello с помощью shell psql или любым удобным способом :)
CREATE DATABASE hello
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
--Создать таблицу contacts      
CREATE TABLE contacts
(
  id bigint NOT NULL, -- User id
  name character(50) NOT NULL, -- User name
  CONSTRAINT "PK_ID" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contacts
  OWNER TO postgres;
COMMENT ON COLUMN contacts.id IS 'User id';
COMMENT ON COLUMN contacts.name IS 'User name';
--[лог выполнения psqlCmd.log]

--Шаг 2
--Сделать рестор из contactsBackup.backup с помощью ..PostgreSQL/bin/pg_restore 
--следующей командой  
pg_restore --host localhost --port 5432 --username "postgres" --dbname "hello" --password  --data-only --table contacts --verbose "G:\Java_Project\ContactsSeeker\contactsBackup.backup"
--[лог выполнения pg_restoreCmd.log]
-Обратить внимание на файл databaseConfig.xml в нем происходит настройка JDBC, а также подключения к Postgresql

### Как собрать артефакт (*.war):

-Используем команду mvn clean install
-Смотрим логи все ли собралось, выполнились ли тесты

### Деплой прилождения:
-Деплоим приложение спомощью mvn tomcat:deploy (мануал http://programador.ru/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-maven-tomcat/)
либо с  помощью любого IDE, имеющего такой функционал
-Развертывание проекта осуществлялось под Apache Tomcat 8.0.9 (возможен запуск и на более ранних версиях),
платформа Java EE 7


### Сигнатура сервиса:

/hello/contacts?nameFilter=val [ &limit=500000 по умолчанию ]
где
nameFilter - входное регулярное выражение, исключающее в ответе данные контакты
limit      - лимит контактов в ответе сервиса, установлено 500000 по умолчанию

Примеры вызовов:
По умолчанию
http://localhost:8084/ContactsSeeker/hello/contacts?nameFilter=^.*[aei].*$

Топ 40 000 контактов в ответе 
http://localhost:8084/ContactsSeeker/hello/contacts?nameFilter=^A.*$&limit=40000