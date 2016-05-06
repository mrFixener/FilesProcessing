CREATE DATABASE processing
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
       
CREATE TABLE public.filesprocessing
(
  id bigint NOT NULL, -- Unique id
  content character(1024), -- Content from xml file
  creationdate timestamp without time zone, -- Content creation date
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
COMMENT ON COLUMN public.filesprocessing.procdate IS 'Processing date'
