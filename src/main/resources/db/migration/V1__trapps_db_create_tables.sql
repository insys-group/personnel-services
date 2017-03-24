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

CREATE TABLE location
  (
     business_id BIGINT NOT NULL,
     address_id  BIGINT NOT NULL,
     PRIMARY KEY (business_id, address_id)
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
    authentication_id VARCHAR(100),
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
    token_id VARCHAR(100),
    token BLOB,
    authentication BLOB,
    PRIMARY KEY (token_id)
  );

ALTER TABLE location
  ADD CONSTRAINT fk_loc_add FOREIGN KEY (address_id) REFERENCES
  address(id);

ALTER TABLE location
  ADD CONSTRAINT fk_loc_bus FOREIGN KEY (business_id)
  REFERENCES business(id);

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