--
-- Table structure for table address
--
DROP TABLE IF EXISTS address;
CREATE TABLE address (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address_1 varchar(255) NOT NULL,
  address_2 varchar(255) DEFAULT NULL,
  city varchar(255) NOT NULL,
  country varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  zip_code varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table business
--
DROP TABLE IF EXISTS business;
CREATE TABLE business (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  business_type varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table person
--
DROP TABLE IF EXISTS person;
CREATE TABLE person (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  person_type varchar(255) NOT NULL,
  phone varchar(255) DEFAULT NULL,
  skype varchar(255) NULL,
  title varchar(255) DEFAULT NULL,
  address_id bigint(20) DEFAULT NULL,
  business_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_per_add FOREIGN KEY (address_id) REFERENCES address (id),
  CONSTRAINT fk_per_bus FOREIGN KEY (business_id) REFERENCES business (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table user
--
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  person_id bigint(20) NOT NULL,
  username varchar(50) NOT NULL,
  password varchar(500) NOT NULL,
  account_non_expired tinyint(1) NOT NULL,
  account_non_locked tinyint(1) NOT NULL,
  credentials_non_expired tinyint(1) NOT NULL,
  enabled tinyint(1) NOT NULL,
  password_changed bit(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (username),
  CONSTRAINT fk_user_person_id FOREIGN KEY (person_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table user_authority
--
DROP TABLE IF EXISTS user_authority;
CREATE TABLE user_authority (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  authority varchar(25) NOT NULL,
  username varchar(25) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_use_aut_use FOREIGN KEY (username) REFERENCES user (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table oauth_refresh_token
--
DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id varchar(100) NOT NULL,
  token blob,
  authentication blob,
  PRIMARY KEY (token_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table skill
--
DROP TABLE IF EXISTS skill;
CREATE TABLE skill (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table role
--
DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table role_skill
--
DROP TABLE IF EXISTS role_skill;
CREATE TABLE role_skill (
  role_id bigint(20) NOT NULL,
  skill_id bigint(20) NOT NULL,
  PRIMARY KEY (role_id,skill_id),
  CONSTRAINT fk_rol_ski FOREIGN KEY (skill_id) REFERENCES skill (id),
  CONSTRAINT fk_rol_ski_rol FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table client
--
DROP TABLE IF EXISTS client;
CREATE TABLE client (
  client_id varchar(255) NOT NULL,
  client_secret varchar(255) NOT NULL,
  secret_required tinyint(1) NOT NULL,
  scopes varchar(255) DEFAULT NULL,
  scoped tinyint(1) NOT NULL,
  grant_types varchar(255) NOT NULL,
  access_token_validity_seconds bigint(20) NOT NULL,
  refresh_token_validity_seconds bigint(20) NOT NULL,
  auto_approve tinyint(1) NOT NULL,
  PRIMARY KEY (client_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table engagement
--
DROP TABLE IF EXISTS engagement;
CREATE TABLE engagement (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comments varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  CONSTRAINT engagement_opening_address_id FOREIGN KEY (address_id) REFERENCES address (id),
  CONSTRAINT engagement_opening_engagement_id FOREIGN KEY (engagement_id) REFERENCES engagement (id),
  CONSTRAINT engagement_opening_role_id FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table feedback
--
DROP TABLE IF EXISTS feedback;
CREATE TABLE feedback (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comment varchar(255) NOT NULL,
  person_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT feedback_person_id FOREIGN KEY (person_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table location
--
DROP TABLE IF EXISTS location;
CREATE TABLE location (
  business_id bigint(20) NOT NULL,
  address_id bigint(20) NOT NULL,
  PRIMARY KEY (business_id,address_id),
  CONSTRAINT fk_loc_add FOREIGN KEY (address_id) REFERENCES address (id),
  CONSTRAINT fk_loc_bus FOREIGN KEY (business_id) REFERENCES business (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table oauth_access_token
--
DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  authentication_id varchar(100) NOT NULL,
  token_id varchar(256) DEFAULT NULL,
  token blob,
  user_name varchar(256) DEFAULT NULL,
  client_id varchar(256) DEFAULT NULL,
  authentication blob,
  refresh_token varchar(256) DEFAULT NULL,
  PRIMARY KEY (authentication_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table opportunity
--
DROP TABLE IF EXISTS opportunity;
CREATE TABLE opportunity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  person_id bigint(20) DEFAULT NULL,
  comments varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  business_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT opportunity_business_id FOREIGN KEY (business_id) REFERENCES business (id),
  CONSTRAINT opportunity_person_id FOREIGN KEY (person_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table opportunity_contact
--

DROP TABLE IF EXISTS opportunity_contact;
CREATE TABLE opportunity_contact (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  opportunity_id bigint(20) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT opportunity_contact_opportunity_id FOREIGN KEY (opportunity_id) REFERENCES opportunity (id),
  CONSTRAINT opportunity_contact_person_id FOREIGN KEY (person_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table opportunity_engagements
--
DROP TABLE IF EXISTS opportunity_engagements;
CREATE TABLE opportunity_engagements (
  opportunity_id bigint(20) NOT NULL,
  engagements_id bigint(20) NOT NULL,
  PRIMARY KEY (opportunity_id,engagements_id),
  CONSTRAINT opportunity_engagements_engagements_id FOREIGN KEY (engagements_id) REFERENCES engagement (id),
  CONSTRAINT opportunity_engagements_opportunity_id FOREIGN KEY (opportunity_id) REFERENCES opportunity (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table opportunity_step
--
DROP TABLE IF EXISTS opportunity_step;
CREATE TABLE opportunity_step (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comments varchar(255) DEFAULT NULL,
  step_timestamp datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table person_skill
--
DROP TABLE IF EXISTS person_skill;
CREATE TABLE person_skill (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  skill_id bigint(20) NOT NULL,
  scale int(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_person_skill_skill_id FOREIGN KEY (skill_id) REFERENCES skill (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table person_person_skills
--
DROP TABLE IF EXISTS person_person_skills;
CREATE TABLE person_person_skills (
  person_id bigint(20) NOT NULL,
  person_skills_id bigint(20) NOT NULL,
  PRIMARY KEY (person_id,person_skills_id),
  UNIQUE KEY uk_person_person_skills_person_skills_id (person_skills_id),
  CONSTRAINT fk_person_skills_person_id FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT fk_person_skills_person_skill_id FOREIGN KEY (person_skills_id) REFERENCES person_skill (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table state
--
DROP TABLE IF EXISTS state;
CREATE TABLE state (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  state_code varchar(255) NOT NULL,
  version bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table training
--
DROP TABLE IF EXISTS training;
CREATE TABLE training (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  online bit(1) NOT NULL,
  address_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_training_address_id FOREIGN KEY (address_id) REFERENCES address (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table person_training
--
DROP TABLE IF EXISTS person_training;
CREATE TABLE person_training (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  training_id bigint(20) DEFAULT NULL,
  end_date bigint(20) NOT NULL,
  hided bit(1) NOT NULL,
  progress varchar(12) DEFAULT 'NOT_STARTED',
  start_date bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_person_training_training_id FOREIGN KEY (training_id) REFERENCES training (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table person_person_trainings
--
DROP TABLE IF EXISTS person_person_trainings;
CREATE TABLE person_person_trainings (
  person_id bigint(20) NOT NULL,
  person_trainings_id bigint(20) NOT NULL,
  PRIMARY KEY (person_id,person_trainings_id),
  UNIQUE KEY uk_person_person_trainings_person_trainings_id (person_trainings_id),
  CONSTRAINT fk_person_person_trainings_person_id FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT fk_person_person_trainings_person_trainings_id FOREIGN KEY (person_trainings_id) REFERENCES person_training (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table training_task
--
DROP TABLE IF EXISTS training_task;
CREATE TABLE training_task (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  name varchar(255) NOT NULL,
  weblink varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table training_tasks
--
DROP TABLE IF EXISTS training_tasks;
CREATE TABLE training_tasks (
  training_id bigint(20) NOT NULL,
  tasks_id bigint(20) NOT NULL,
  PRIMARY KEY (training_id,tasks_id),
  UNIQUE KEY uk_training_tasks_tasks_id (tasks_id),
  CONSTRAINT fk_training_tasks_tasks_id FOREIGN KEY (tasks_id) REFERENCES training_task (id),
  CONSTRAINT fk_training_tasks_training_id FOREIGN KEY (training_id) REFERENCES training (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table person_training_task_completion
--
DROP TABLE IF EXISTS person_training_task_completion;
CREATE TABLE person_training_task_completion (
  person_training_id bigint(20) NOT NULL,
  tasks_id bigint(20) NOT NULL,
  PRIMARY KEY (person_training_id,tasks_id),
  CONSTRAINT fk_person_training_task_completion_person_training_id FOREIGN KEY (person_training_id) REFERENCES person_training (id),
  CONSTRAINT fk_person_training_task_completion_tasks_id FOREIGN KEY (tasks_id) REFERENCES training_task (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table interview_template
--
DROP TABLE IF EXISTS interview_template;
CREATE TABLE interview_template (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  date datetime NOT NULL,
  name varchar(255) DEFAULT NULL,
  role_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT interview_template_role_id FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table interview
--
DROP TABLE IF EXISTS interview;
CREATE TABLE interview (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  date datetime NOT NULL,
  contact_type varchar(255) NOT NULL,
  contact varchar(255) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL,
  role_id bigint(20) NOT NULL,
  status int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (id),
  CONSTRAINT interview_person_id FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT interview_role_id FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table question
--
DROP TABLE IF EXISTS question;
CREATE TABLE question (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  answer varchar(255) DEFAULT NULL,
  comment varchar(255) DEFAULT NULL,
  quality varchar(255) DEFAULT NULL,
  question varchar(255) NOT NULL,
  interview_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT question_interview_id FOREIGN KEY (interview_id) REFERENCES interview (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table answer
--
DROP TABLE IF EXISTS answer;
CREATE TABLE answer (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  answer varchar(255) DEFAULT NULL,
  rate int(1) DEFAULT NULL,
  comment varchar(255) DEFAULT NULL,
  quality varchar(255) DEFAULT NULL,
  question_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT answer_question_id FOREIGN KEY (question_id) REFERENCES question (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table interview_answers
--
DROP TABLE IF EXISTS interview_answers;
CREATE TABLE interview_answers (
  interview_id bigint(20) NOT NULL,
  answers_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_id,answers_id),
  CONSTRAINT interview_answers_answers_id FOREIGN KEY (answers_id) REFERENCES answer (id),
  CONSTRAINT interview_answers_interview_id FOREIGN KEY (interview_id) REFERENCES interview (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table interview_feedbacks
--
DROP TABLE IF EXISTS interview_feedbacks;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE interview_feedbacks (
  interview_id bigint(20) NOT NULL,
  feedbacks_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_id,feedbacks_id),
  CONSTRAINT interview_feedbacks_feedbacks_id FOREIGN KEY (feedbacks_id) REFERENCES feedback (id),
  CONSTRAINT interview_feedbacks_interview_id FOREIGN KEY (interview_id) REFERENCES interview (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table interview_interviewers
--
DROP TABLE IF EXISTS interview_interviewers;
CREATE TABLE interview_interviewers (
  interview_id bigint(20) NOT NULL,
  interviewers_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_id,interviewers_id),
  CONSTRAINT interview_interviewers_interview_id FOREIGN KEY (interview_id) REFERENCES interview (id),
  CONSTRAINT interview_interviewers_interviewers_id FOREIGN KEY (interviewers_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table interview_questions
--
DROP TABLE IF EXISTS interview_questions;
CREATE TABLE interview_questions (
  interview_id bigint(20) NOT NULL,
  questions_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_id,questions_id),
  CONSTRAINT interview_questions_interview_id FOREIGN KEY (interview_id) REFERENCES interview (id),
  CONSTRAINT interview_questions_questions_id FOREIGN KEY (questions_id) REFERENCES question (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table interview_template_questions
--
DROP TABLE IF EXISTS interview_template_questions;
CREATE TABLE interview_template_questions (
  interview_template_id bigint(20) NOT NULL,
  questions_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_template_id,questions_id),
  CONSTRAINT interview_template_questions_interview_template_id FOREIGN KEY (interview_template_id) REFERENCES interview_template (id),
  CONSTRAINT interview_template_questions_questions_id FOREIGN KEY (questions_id) REFERENCES question (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;