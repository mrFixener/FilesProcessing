[![Build Status](https://travis-ci.org/mrFixener/FilesProcessing.svg?branch=master)](https://travis-ci.org/mrFixener/FilesProcessing)
### Настройка базы данных PostgreSQL:

##### Шаг 1
#####Создать БД processing с помощью shell psql или любым удобным способом :)
================
```sql 
CREATE DATABASE processing
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
       ```
 --Создать таблицу filesprocessing      
 ```sql
 CREATE TABLE public.filesprocessing
 (
  id bigint NOT NULL, -- Unique id
  content character(1024), -- Content from xml file
  creationdate timestamp without time zone,
  procdate timestamp without time zone NOT NULL, -- Processing date
  CONSTRAINT filesprocessing_pkey PRIMARY KEY (id)
 )
   WITH (
    OIDS=FALSE
   );
   ALTER TABLE public.filesprocessing
    OWNER TO postgres;
    COMMENT ON TABLE public.filesprocessing
    IS 'Processing files data';
    COMMENT ON COLUMN public.filesprocessing.id IS 'Unique id';
    COMMENT ON COLUMN public.filesprocessing.content IS 'Content from xml file';
    COMMENT ON COLUMN public.filesprocessing.procdate IS 'Processing date';
```
================
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
