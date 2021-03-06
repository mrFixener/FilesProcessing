[![Build Status](https://travis-ci.org/mrFixener/FilesProcessing.svg?branch=master)](https://travis-ci.org/mrFixener/FilesProcessing)
Настройка базы данных PostgreSQL
==================
#### Создать БД processing с помощью shell psql или любым удобным способом

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
    COMMENT ON COLUMN public.filesprocessing.creationdate IS 'Content creation date';
    COMMENT ON COLUMN public.filesprocessing.procdate IS 'Processing date';
```
*Настройка конекта к базе находиться в*  **databaseConfig.xml** 
Как собрать артефакт (*.jar)
================
####Используем команду 
```shell
    gradle build
```
Смотрим логи все ли собралось, выполнились ли тесты
<br>
<b>После сборки запускаемый файл будет находиться {Path_To_Project}/FilesProcessing/build/libs/FilesProcessing.jar (копия собранного артефакта находиться в корне проекта)</b>
#### Выполнение тестов
#### Запуск тестов по отдельности:
```shell
gradle :cleanTest :test
Arguments: [-Dtest.single=com/filesprocessing/test/FilesListenerTest, -c, settings.gradle]

gradle :cleanTest :test
Arguments: [-Dtest.single=com/filesprocessing/test/FilesProcessingServiceTest, -c, settings.gradle]
```
Конфигурация приложения
================
####Все настройка приложения находиться в файле конфигурации: *configuration.properties*, который расположен в исполняемом файле FilesProcessing.jar и редактируется с помощью 7Zip, WinRar и т.д.
####Структура конфигурационного файла configuration.properties:
```properties
#Директория мониторинга;
conf.dirInp=src/main/resources/inp
#Директория обработанных файлов;
conf.dirOut=src/main/resources/out
#Директория файлов, обработка которых не удалась;
conf.dirFail=src/main/resources/fail
#Период мониторинга. Используются выражения Unix cron ( https://en.wikipedia.org/wiki/Cron ). Пример выражения: 0 0/1 * * * ?  запуск каждую минуту; 0/20 * * * * ? - каждые 20 секунд
conf.monitorPeriod=0/20 * * * * ? 
#Регулярное выражение (regex) для поиска файлов, которые необходимо обработать.Для примера нужно найти файлы xml | XML, в названии которых есть слово inp: .*inp.*\\.(xml|XML)
conf.regExMask=.*inp.*\\.(xml|XML)
#Количество потоков в пуле. 
conf.numPoolThread=24
```
Скрипты для запуска запуска на Win и *nix-платформах
================
####Находяться в корне проекта:
- run.bat
- run.sh

Логирование выполнения программы
================
####Файл логирования создается  в корне проекта автоматически:
*applog.log*
