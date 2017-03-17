CREATE TABLE TRAINING
  (
     id         BIGINT auto_increment,
     name       VARCHAR(255) NOT NULL,
     address_id BIGINT,
     online     BOOLEAN,
     PRIMARY KEY (id)
  );

CREATE TABLE TRAINING_TASK
  (
     id          BIGINT auto_increment,
     name        VARCHAR(255) NOT NULL,
     description VARCHAR(255),
     weblink     VARCHAR(255),
     PRIMARY KEY (id)
  );

CREATE TABLE TRAINING_TASKS
  (
     training_id BIGINT,
     tasks_id    BIGINT,
     PRIMARY KEY (training_id, tasks_id)
  );

CREATE TABLE PERSON_TRAINING
  (
     id          BIGINT auto_increment,
     start_date  BIGINT,
     end_date    BIGINT,
     hided       BOOLEAN,
     progress    VARCHAR(255),
     person_id   BIGINT,
     training_id BIGINT,
     PRIMARY KEY (id)
  );

CREATE TABLE PERSON_TRAINING_TASK_COMPLETION
  (
     person_training_id BIGINT,
     tasks_id           BIGINT,
     PRIMARY KEY (person_training_id, tasks_id)
  );

insert into TRAINING(id, name, address_id, online) values(1, 'Pivotal training', 1, FALSE);
insert into TRAINING(id, name, online) values(2, 'Another training', TRUE);

insert into TRAINING_TASK(id, name, description, weblink) values (1, 'Watch video about Spring Boot', 'Spring Boot is awesome.', 'http://training.org/1');
insert into TRAINING_TASK(id, name, description, weblink) values (2, 'Watch video about HATEOAS', 'HATEOAS is awesome', 'http://training.org/2');
insert into TRAINING_TASK(id, name, description, weblink) values (3, 'Watch all videos of Josh Long', 'Josh Long is awesome', 'http://training.org/3');
insert into TRAINING_TASK(id, name, description, weblink) values (4, 'Watch another video', 'Some description', 'http://training.org/1');
insert into TRAINING_TASK(id, name, description, weblink) values (5, 'Implement lab 2', 'The Computational Neuroscience Research Group (CNRG) is dedicated to developing and using a unified mathematical framework for modeling large-scale neurobiological systems.', 'http://training.org/2');

insert into TRAINING_TASKS(training_id, tasks_id) values (1, 1);
insert into TRAINING_TASKS(training_id, tasks_id) values (1, 2);
insert into TRAINING_TASKS(training_id, tasks_id) values (1, 3);
insert into TRAINING_TASKS(training_id, tasks_id) values (2, 4);
insert into TRAINING_TASKS(training_id, tasks_id) values (2, 5);

insert into PERSON_TRAINING(id, start_date, end_date, hided, person_id, training_id) values (1, 1487203200000, 1487548800000, false, 12, 1);
insert into PERSON_TRAINING(id, start_date, end_date, hided, person_id, training_id) values (2, 1487203200000, 1487548800000, false, 16, 2);
insert into PERSON_TRAINING(id, start_date, end_date, hided, person_id, training_id) values (3, 1487203200000, 1487548800000, false, 15, 2);

insert into PERSON_TRAINING_TASK_COMPLETION(person_training_id, tasks_id) values (1,1);
insert into PERSON_TRAINING_TASK_COMPLETION(person_training_id, tasks_id) values (2,4);