CREATE TABLE address
  (
     id        BIGINT auto_increment,
     address_1 VARCHAR(255) NOT NULL,
     address_2 VARCHAR(255),
     city      VARCHAR(255) NOT NULL,
     country   VARCHAR(255) NOT NULL,
     state     VARCHAR(255) NOT NULL,
     zip_code  VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE business
  (
     id            BIGINT auto_increment,
     business_type VARCHAR(255) NOT NULL,
     description   VARCHAR(255) NOT NULL,
     name          VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE contract
  (
     id                    BIGINT auto_increment,
     comments              VARCHAR(255),
     engagement_opening_id BIGINT,
     person_id             BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE contract_detail
  (
     id          BIGINT auto_increment,
     comments    VARCHAR(255),
     end_date    DATE,
     rate        DECIMAL(19, 2),
     start_date  DATE,
     contract_id BIGINT NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE engagement
  (
     id             BIGINT auto_increment,
     comments       VARCHAR(255),
     opportunity_id BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE engagement_opening
  (
     id            BIGINT auto_increment,
     comments      VARCHAR(255),
     rate          DECIMAL(19, 2),
     address_id    BIGINT,
     engagement_id BIGINT,
     role_id       BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE location
  (
     business_id BIGINT NOT NULL,
     address_id  BIGINT NOT NULL,
     PRIMARY KEY (business_id, address_id)
  );

CREATE TABLE opportunity
  (
     id          BIGINT auto_increment,
     comments    VARCHAR(255),
     business_id BIGINT,
     person      BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE opportunity_contact
  (
     id             BIGINT auto_increment,
     opportunity_id BIGINT,
     person_id      BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE opportunity_step
  (
     id             BIGINT auto_increment,
     comments       VARCHAR(255),
     step_timestamp TIMESTAMP NOT NULL,
     opportunity_id BIGINT NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE person
  (
     id          BIGINT auto_increment,
     email       VARCHAR(255) NOT NULL,
     first_name  VARCHAR(255) NOT NULL,
     last_name   VARCHAR(255) NOT NULL,
     person_type VARCHAR(255) NOT NULL,
     phone       VARCHAR(255),
     title       VARCHAR(255),
     address_id  BIGINT,
     business_id BIGINT NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE person_document
  (
     id               BIGINT auto_increment,
     document         LONGBLOB NOT NULL,
     file_name        VARCHAR(255) NOT NULL,
     file_size        BIGINT NOT NULL,
     upload_timestamp TIMESTAMP NOT NULL,
     person_id        BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE person_skill
  (
     id        BIGINT auto_increment,
     name      VARCHAR(255) NOT NULL,
     scale     INTEGER NOT NULL,
     person_id BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE role
  (
     id   BIGINT auto_increment,
     name VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE role_skill
  (
     role_id  BIGINT NOT NULL,
     skill_id BIGINT NOT NULL,
     PRIMARY KEY (role_id, skill_id)
  );

CREATE TABLE skill
  (
     id   BIGINT auto_increment,
     name VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE state
  (
     id         BIGINT auto_increment,
     state_code VARCHAR(255) NOT NULL,
     version    BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE user
  (
     username                VARCHAR(50) NOT NULL,
     password                VARCHAR(500) NOT NULL,
     account_non_expired     BOOLEAN NOT NULL,
     account_non_locked      BOOLEAN NOT NULL,
     credentials_non_expired BOOLEAN NOT NULL,
     enabled                 BOOLEAN NOT NULL,
     person_id               BIGINT NOT NULL,
     PRIMARY KEY (username)
  );

CREATE TABLE user_authority
  (
     id        BIGINT auto_increment,
     authority VARCHAR(25) NOT NULL,
     username  VARCHAR(25) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE Client
  (
     client_id               VARCHAR(255) NOT NULL,
     client_secret           VARCHAR(255) NOT NULL,
     secret_required         BOOLEAN NOT NULL,
     scopes                  VARCHAR(255),
     scoped                  BOOLEAN NOT NULL,
     grant_types             VARCHAR(255) NOT NULL,
     access_token_validity_seconds     BIGINT NOT NULL,
     refresh_token_validity_seconds    BIGINT NOT NULL,
     auto_approve            BOOLEAN NOT NULL,
     PRIMARY KEY (client_id)
  );

CREATE TABLE oauth_access_token
  (
    authentication_id VARCHAR(256),
    token_id VARCHAR(256),
    token BLOB,
    user_name VARCHAR(256),
    client_id VARCHAR(256),
    authentication BLOB,
    refresh_token VARCHAR(256),
    PRIMARY KEY (authentication_id)
  );

CREATE TABLE oauth_refresh_token
  (
    token_id VARCHAR(256),
    token BLOB,
    authentication BLOB,
    PRIMARY KEY (token_id)
  );

ALTER TABLE contract
  ADD CONSTRAINT fk_con_eng_ope FOREIGN KEY (engagement_opening_id)
  REFERENCES engagement_opening(id);

ALTER TABLE contract
  ADD CONSTRAINT fk_con_per FOREIGN KEY (person_id) REFERENCES
  person(id);

ALTER TABLE contract_detail
  ADD CONSTRAINT fk_con_det_con FOREIGN KEY (contract_id)
  REFERENCES contract(id);

ALTER TABLE engagement
  ADD CONSTRAINT fk_eng_opp FOREIGN KEY (opportunity_id)
  REFERENCES opportunity(id);

ALTER TABLE engagement_opening
  ADD CONSTRAINT fk_eng_add FOREIGN KEY (address_id) REFERENCES
  address(id);

ALTER TABLE engagement_opening
  ADD CONSTRAINT fk_eng_ope_eng FOREIGN KEY (engagement_id)
  REFERENCES engagement(id);

ALTER TABLE engagement_opening
  ADD CONSTRAINT fk_eng_ope_rol FOREIGN KEY (role_id) REFERENCES
  role(id);

ALTER TABLE location
  ADD CONSTRAINT fk_loc_add FOREIGN KEY (address_id) REFERENCES
  address(id);

ALTER TABLE location
  ADD CONSTRAINT fk_loc_bus FOREIGN KEY (business_id)
  REFERENCES business(id);

ALTER TABLE opportunity
  ADD CONSTRAINT fk_opp_bus FOREIGN KEY (business_id)
  REFERENCES business(id);

ALTER TABLE opportunity
  ADD CONSTRAINT fk_opp_per FOREIGN KEY (person) REFERENCES
  person(id);

ALTER TABLE opportunity_contact
  ADD CONSTRAINT fk_opp_con_opp FOREIGN KEY (opportunity_id)
  REFERENCES opportunity(id);

ALTER TABLE opportunity_contact
  ADD CONSTRAINT fk_opp_con_per FOREIGN KEY (person_id) REFERENCES
  person(id);

ALTER TABLE opportunity_step
  ADD CONSTRAINT fk_opp_ste_opp FOREIGN KEY (opportunity_id)
  REFERENCES opportunity(id);

ALTER TABLE person
  ADD CONSTRAINT fk_per_add FOREIGN KEY (address_id) REFERENCES
  address(id);

ALTER TABLE person
  ADD CONSTRAINT fk_per_bus FOREIGN KEY (business_id)
  REFERENCES business(id);

ALTER TABLE person_document
  ADD CONSTRAINT fk_per_doc_per FOREIGN KEY (person_id) REFERENCES
  person(id);

ALTER TABLE person_skill
  ADD CONSTRAINT fk_per_det_per FOREIGN KEY (person_id) REFERENCES
  person(id);

ALTER TABLE role_skill
  ADD CONSTRAINT fk_rol_ski FOREIGN KEY (skill_id) REFERENCES
  skill(id);

ALTER TABLE role_skill
  ADD CONSTRAINT fk_rol_ski_rol FOREIGN KEY (role_id) REFERENCES
  role(id);

ALTER TABLE user_authority
  ADD CONSTRAINT fk_use_aut_use FOREIGN KEY (username) REFERENCES
  user(username);

-- drop table address if exists;
-- drop table business if exists;
-- drop table contract if exists;
-- drop table contract_detail if exists;
-- drop table engagement if exists;
-- drop table engagement_opening if exists;
-- drop table location if exists;
-- drop table opportunity if exists;
-- drop table opportunity_contact if exists;
-- drop table opportunity_step if exists;
-- drop table person if exists;
-- drop table person_document if exists;
-- drop table person_skill if exists;
-- drop table role if exists;
-- drop table role_skill if exists;
-- drop table skill if exists;
-- drop table state if exists;
-- drop table user if exists;
-- drop table user_authority if exists;
