[![Build Status](https://travis-ci.org/mrFixener/FilesProcessing.svg?branch=master)](https://travis-ci.org/mrFixener/FilesProcessing)
Настройка базы данных PostgreSQL
==================
#### Создать БД processing с помощью shell psql или любым удобным способом :)

```sql 
CREATE DATABASE processing
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
```
#### Создать таблицу filesprocessing      
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

Как собрать артефакт (*.jar)
================
####Используем команду 
```shell
    gradle build
```
Смотрим логи все ли собралось, выполнились ли тесты
<b>Сам запускаемый файл будет находиться {Path_To_Project}/FilesProcessing/build/libs/FilesProcessing.jar (собранный артефакт находиться в корне проекта)</b>
#### Запуск тестов отдельно:
```shell
gradle :cleanTest :test
Arguments: [-Dtest.single=com/filesprocessing/test/FilesListenerTest, -c, settings.gradle]

gradle :cleanTest :test
Arguments: [-Dtest.single=com/filesprocessing/test/FilesProcessingServiceTest, -c, settings.gradle]
```


Примеры вызовов:
По умолчанию
http://localhost:8084/ContactsSeeker/hello/contacts?nameFilter=^.*[aei].*$

Топ 40 000 контактов в ответе 
http://localhost:8084/ContactsSeeker/hello/contacts?nameFilter=^A.*$&limit=40000
