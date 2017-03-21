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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table interview
--
DROP TABLE IF EXISTS interview;
CREATE TABLE interview (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  date datetime NOT NULL,
  name varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL,
  role_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT interview_person_id FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT interview_role_id FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table answer
--
DROP TABLE IF EXISTS answer;
CREATE TABLE answer (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  answer varchar(255) DEFAULT NULL,
  comment varchar(255) DEFAULT NULL,
  quality varchar(255) DEFAULT NULL,
  question_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT answer_question_id FOREIGN KEY (question_id) REFERENCES question (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table interview_answers
--
DROP TABLE IF EXISTS interview_answers;
CREATE TABLE interview_answers (
  interview_id bigint(20) NOT NULL,
  answers_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_id,answers_id),
  CONSTRAINT interview_answers_interview_id FOREIGN KEY (interview_id) REFERENCES interview (id),
  CONSTRAINT interview_answers_answers_id FOREIGN KEY (answers_id) REFERENCES answer (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table interview_feedbacks
--
DROP TABLE IF EXISTS interview_feedbacks;
CREATE TABLE interview_feedbacks (
  interview_id bigint(20) NOT NULL,
  feedbacks_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_id,feedbacks_id),
  CONSTRAINT interview_feedbacks_feedbacks_id FOREIGN KEY (feedbacks_id) REFERENCES feedback (id),
  CONSTRAINT interview_feedbacks_interview_id FOREIGN KEY (interview_id) REFERENCES interview (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table interview_interviewers
--
DROP TABLE IF EXISTS interview_interviewers;
CREATE TABLE interview_interviewers (
  interview_id bigint(20) NOT NULL,
  interviewers_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_id,interviewers_id),
  CONSTRAINT interview_interviewers_interviewers_id FOREIGN KEY (interviewers_id) REFERENCES person (id),
  CONSTRAINT interview_interviewers_interview_id FOREIGN KEY (interview_id) REFERENCES interview (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table interview_template_questions
--
DROP TABLE IF EXISTS interview_template_questions;
CREATE TABLE interview_template_questions (
  interview_template_id bigint(20) NOT NULL,
  questions_id bigint(20) NOT NULL,
  PRIMARY KEY (interview_template_id,questions_id),
  CONSTRAINT interview_template_questions_questions_id FOREIGN KEY (questions_id) REFERENCES question (id),
  CONSTRAINT interview_template_questions_interview_template_id FOREIGN KEY (interview_template_id) REFERENCES interview_template (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;