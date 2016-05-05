### ��������� ���� ������ PostgreSQL:

--��� 1
--������� �� hello � ������� shell psql ��� ����� ������� �������� :)
CREATE DATABASE hello
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
--������� ������� contacts      
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
--[��� ���������� psqlCmd.log]

--��� 2
--������� ������ �� contactsBackup.backup � ������� ..PostgreSQL/bin/pg_restore 
--��������� ��������  
pg_restore --host localhost --port 5432 --username "postgres" --dbname "hello" --password  --data-only --table contacts --verbose "G:\Java_Project\ContactsSeeker\contactsBackup.backup"
--[��� ���������� pg_restoreCmd.log]
-�������� �������� �� ���� databaseConfig.xml � ��� ���������� ��������� JDBC, � ����� ����������� � Postgresql

### ��� ������� �������� (*.war):

-���������� ������� mvn clean install
-������� ���� ��� �� ���������, ����������� �� �����

### ������ �����������:
-������� ���������� �������� mvn tomcat:deploy (������ http://programador.ru/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-maven-tomcat/)
���� �  ������� ������ IDE, �������� ����� ����������
-������������� ������� �������������� ��� Apache Tomcat 8.0.9 (�������� ������ � �� ����� ������ �������),
��������� Java EE 7


### ��������� �������:

/hello/contacts?nameFilter=val [ &limit=500000 �� ��������� ]
���
nameFilter - ������� ���������� ���������, ����������� � ������ ������ ��������
limit      - ����� ��������� � ������ �������, ����������� 500000 �� ���������

������� �������:
�� ���������
http://localhost:8084/ContactsSeeker/hello/contacts?nameFilter=^.*[aei].*$

��� 40 000 ��������� � ������ 
http://localhost:8084/ContactsSeeker/hello/contacts?nameFilter=^A.*$&limit=40000