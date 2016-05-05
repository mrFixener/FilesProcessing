https://travis-ci.org/mrFixener/FilesProcessing.svg?branch=master
### Íàñòðîéêà áàçû äàííûõ PostgreSQL:

--Øàã 1
--Ñîçäàòü ÁÄ hello ñ ïîìîùüþ shell psql èëè ëþáûì óäîáíûì ñïîñîáîì :)
CREATE DATABASE hello
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
--Ñîçäàòü òàáëèöó contacts      
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
--[ëîã âûïîëíåíèÿ psqlCmd.log]

--Øàã 2
--Ñäåëàòü ðåñòîð èç contactsBackup.backup ñ ïîìîùüþ ..PostgreSQL/bin/pg_restore 
--ñëåäóþùåé êîìàíäîé  
pg_restore --host localhost --port 5432 --username "postgres" --dbname "hello" --password  --data-only --table contacts --verbose "G:\Java_Project\ContactsSeeker\contactsBackup.backup"
--[ëîã âûïîëíåíèÿ pg_restoreCmd.log]
-Îáðàòèòü âíèìàíèå íà ôàéë databaseConfig.xml â íåì ïðîèñõîäèò íàñòðîéêà JDBC, à òàêæå ïîäêëþ÷åíèÿ ê Postgresql

### Êàê ñîáðàòü àðòåôàêò (*.war):

-Èñïîëüçóåì êîìàíäó mvn clean install
-Ñìîòðèì ëîãè âñå ëè ñîáðàëîñü, âûïîëíèëèñü ëè òåñòû

### Äåïëîé ïðèëîæäåíèÿ:
-Äåïëîèì ïðèëîæåíèå ñïîìîùüþ mvn tomcat:deploy (ìàíóàë http://programador.ru/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-maven-tomcat/)
ëèáî ñ  ïîìîùüþ ëþáîãî IDE, èìåþùåãî òàêîé ôóíêöèîíàë
-Ðàçâåðòûâàíèå ïðîåêòà îñóùåñòâëÿëîñü ïîä Apache Tomcat 8.0.9 (âîçìîæåí çàïóñê è íà áîëåå ðàííèõ âåðñèÿõ),
ïëàòôîðìà Java EE 7


### Ñèãíàòóðà ñåðâèñà:

/hello/contacts?nameFilter=val [ &limit=500000 ïî óìîë÷àíèþ ]
ãäå
nameFilter - âõîäíîå ðåãóëÿðíîå âûðàæåíèå, èñêëþ÷àþùåå â îòâåòå äàííûå êîíòàêòû
limit      - ëèìèò êîíòàêòîâ â îòâåòå ñåðâèñà, óñòàíîâëåíî 500000 ïî óìîë÷àíèþ

Ïðèìåðû âûçîâîâ:
Ïî óìîë÷àíèþ
http://localhost:8084/ContactsSeeker/hello/contacts?nameFilter=^.*[aei].*$

Òîï 40 000 êîíòàêòîâ â îòâåòå 
http://localhost:8084/ContactsSeeker/hello/contacts?nameFilter=^A.*$&limit=40000
