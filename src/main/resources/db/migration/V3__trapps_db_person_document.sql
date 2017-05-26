ALTER TABLE feedback MODIFY comment text;
ALTER TABLE answer MODIFY comment text;

--
-- Table structure for table person_document
--
DROP TABLE IF EXISTS person_document;
CREATE TABLE person_document (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  person_id bigint(20) NOT NULL,
  file_name varchar(250) NOT NULL,
  file_size bigint(20) NOT NULL,
  date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  s3key varchar(250) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_person_document_person_id FOREIGN KEY (person_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;