DROP TABLE IF EXISTS PERSON_TRAINING_TASK_COMPLETION;
DROP TABLE IF EXISTS TRAINING_TASKS;
DROP TABLE IF EXISTS PERSON_PERSON_TRAININGS;
DROP TABLE IF EXISTS PERSON_TRAINING;
DROP TABLE IF EXISTS TRAINING_TASK;
DROP TABLE IF EXISTS TRAINING;

DROP TABLE IF EXISTS person_person_skills;
DROP TABLE IF EXISTS person_skill;

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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table training_tasks
--
DROP TABLE IF EXISTS training_tasks;
CREATE TABLE training_tasks (
  training_id bigint(20) NOT NULL,
  tasks_id bigint(20) NOT NULL,
  PRIMARY KEY (training_id,tasks_id),
  UNIQUE KEY uk_training_tasks_tasks_id (tasks_id),
  CONSTRAINT fk_training_tasks_training_id FOREIGN KEY (training_id) REFERENCES training (id),
  CONSTRAINT fk_training_tasks_tasks_id FOREIGN KEY (tasks_id) REFERENCES training_task (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table training_tasks
--
CREATE TABLE person_training (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  training_id bigint(20) DEFAULT NULL,
  end_date bigint(20) NOT NULL,
  hided bit(1) NOT NULL,
  progress varchar(12) DEFAULT 'NOT_STARTED',
  start_date bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_person_training_training_id FOREIGN KEY (training_id) REFERENCES training (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table person_person_trainings
--
DROP TABLE IF EXISTS person_person_trainings;
CREATE TABLE person_person_trainings (
  person_id bigint(20) NOT NULL,
  person_trainings_id bigint(20) NOT NULL,
  PRIMARY KEY (person_id,person_trainings_id),
  UNIQUE KEY uk_person_person_trainings_person_trainings_id (person_trainings_id),
  CONSTRAINT fk_person_person_trainings_person_trainings_id FOREIGN KEY (person_trainings_id) REFERENCES person_training (id),
  CONSTRAINT fk_person_person_trainings_person_id FOREIGN KEY (person_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table person_training_task_completion
--
DROP TABLE IF EXISTS person_training_task_completion;
CREATE TABLE person_training_task_completion (
  person_training_id bigint(20) NOT NULL,
  tasks_id bigint(20) NOT NULL,
  PRIMARY KEY (person_training_id,tasks_id),
  CONSTRAINT fk_person_training_task_completion_tasks_id FOREIGN KEY (tasks_id) REFERENCES training_task (id),
  CONSTRAINT fk_person_training_task_completion_person_training_id FOREIGN KEY (person_training_id) REFERENCES person_training (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table person_skill
--
DROP TABLE IF EXISTS person_skill;
CREATE TABLE person_skill (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  skill_id bigint(20) NOT NULL,
  scale INTEGER NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_person_skill_skill_id FOREIGN KEY (skill_id) REFERENCES skill (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table person_skill
--
DROP TABLE IF EXISTS person_person_skills;
CREATE TABLE person_person_skills (
  person_id bigint(20) NOT NULL,
  person_skills_id bigint(20) NOT NULL,
  PRIMARY KEY (person_id, person_skills_id),
  UNIQUE KEY uk_person_person_skills_person_skills_id (person_skills_id),
  CONSTRAINT fk_person_skills_person_id FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT fk_person_skills_person_skill_id FOREIGN KEY (person_skills_id) REFERENCES person_skill (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;