--
-- Table structure for table opportunity
--
DROP TABLE IF EXISTS opportunity;
CREATE TABLE opportunity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comments varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  business_id bigint(20) DEFAULT NULL,
  person bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT opportunity_business_id FOREIGN KEY (business_id) REFERENCES business (id),
  CONSTRAINT opportunity_person FOREIGN KEY (person) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table opportunity_contact
--
DROP TABLE IF EXISTS opportunity_contact;
CREATE TABLE opportunity_contact (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  opportunity_id bigint(20) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT opportunity_contact_person_id FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT opportunity_contact_opportunity_id FOREIGN KEY (opportunity_id) REFERENCES opportunity (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table opportunity_step
--
DROP TABLE IF EXISTS opportunity_step;
CREATE TABLE opportunity_step (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comments varchar(255) DEFAULT NULL,
  step_timestamp datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table opportunity_opportunity_steps
--
DROP TABLE IF EXISTS opportunity_opportunity_steps;
CREATE TABLE opportunity_opportunity_steps (
  opportunity_id bigint(20) NOT NULL,
  opportunity_steps_id bigint(20) NOT NULL,
  PRIMARY KEY (opportunity_id,opportunity_steps_id),
  CONSTRAINT opportunity_opportunity_steps_opportunity_id FOREIGN KEY (opportunity_id) REFERENCES opportunity (id),
  CONSTRAINT opportunity_opportunity_steps_opportunity_steps_id FOREIGN KEY (opportunity_steps_id) REFERENCES opportunity_step (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table engagement
--
DROP TABLE IF EXISTS engagement;
CREATE TABLE engagement (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comments varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table opportunity_engagements
--
DROP TABLE IF EXISTS opportunity_engagements;
CREATE TABLE opportunity_engagements (
  opportunity_id bigint(20) NOT NULL,
  engagements_id bigint(20) NOT NULL,
  PRIMARY KEY (opportunity_id,engagements_id),
  CONSTRAINT opportunity_engagements_opportunity_id FOREIGN KEY (opportunity_id) REFERENCES opportunity (id),
  CONSTRAINT opportunity_engagements_engagements_id FOREIGN KEY (engagements_id) REFERENCES engagement (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table engagement_opening
--
DROP TABLE IF EXISTS engagement_opening;
CREATE TABLE engagement_opening (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comments varchar(255) DEFAULT NULL,
  rate decimal(19,2) DEFAULT NULL,
  address_id bigint(20) DEFAULT NULL,
  engagement_id bigint(20) DEFAULT NULL,
  role_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT engagement_opening_engagement_id FOREIGN KEY (engagement_id) REFERENCES engagement (id),
  CONSTRAINT engagement_opening_address_id FOREIGN KEY (address_id) REFERENCES address (id),
  CONSTRAINT engagement_opening_role_id FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table contract
--
DROP TABLE IF EXISTS contract;
CREATE TABLE contract (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comments varchar(255) DEFAULT NULL,
  engagement_opening_id bigint(20) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT contract_engagement_opening_id FOREIGN KEY (engagement_opening_id) REFERENCES engagement_opening (id),
  CONSTRAINT contract_person_id FOREIGN KEY (person_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table contract_detail
--
DROP TABLE IF EXISTS contract_detail;
CREATE TABLE contract_detail (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comments varchar(255) DEFAULT NULL,
  end_date tinyblob,
  rate decimal(19,2) DEFAULT NULL,
  start_date tinyblob,
  contract_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT contract_detail_contract_id FOREIGN KEY (contract_id) REFERENCES contract (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;