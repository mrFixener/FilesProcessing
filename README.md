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
*Настройка коннекта к базе находиться в*  **databaseConfig.xml** 
Как собрать артефакт (*.jar)
================
####Используем команду 
```shell
    gradle build
```
Смотрим логи все ли собралось, выполнились ли тесты
<br>
<b>Сам запускаемый файл будет находиться {Path_To_Project}/FilesProcessing/build/libs/FilesProcessing.jar (собранный артефакт находиться в корне проекта)</b>
#### Запуск тестов по отдельности:
```shell
gradle :cleanTest :test
Arguments: [-Dtest.single=com/filesprocessing/test/FilesListenerTest, -c, settings.gradle]

gradle :cleanTest :test
Arguments: [-Dtest.single=com/filesprocessing/test/FilesProcessingServiceTest, -c, settings.gradle]
```
Конфигурация приложения
================
####Вся настройка приложения находиться в файле конфигурвции: *configuration.properties*, который находиться в выполняемом файле FilesProcessing.jar и редактируется с помощью 7Zip, WinRar и т.д.
