ALTER TABLE feedback MODIFY comment text;
ALTER TABLE answer MODIFY comment text;

--
-- Table structure for table person_document
--
DROP TABLE IF EXISTS person_document;
CREATE TABLE person_document (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  file_name varchar(250) NOT NULL,
  file_size bigint(20) NOT NULL,
  date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  s3key varchar(250) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (file_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table person_person_skills
--
DROP TABLE IF EXISTS person_person_documents;
CREATE TABLE person_person_documents (
  person_id bigint(20) NOT NULL,
  person_documents_id bigint(20) NOT NULL,
  PRIMARY KEY (person_id,person_documents_id),
  CONSTRAINT fk_person_person_documents_person_id FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT fk_person_person_documents_person_documents_id FOREIGN KEY (person_documents_id) REFERENCES person_document (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;