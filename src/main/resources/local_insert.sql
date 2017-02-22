insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(1, '10 Park Ave' , 'Manhattan' , 'NY' , '10010', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(2, '100 Business Ave', 'Manhattan' , 'NY' , '10012', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(3, '100 Comcast Ave', 'Philadelphia', 'PA', '88790', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(4, '10 INSYS Ave', 'Rochelle Park', 'NJ', '06786', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(5, '3 Gerson Rd', 'Robbinsville', 'NJ', '08691', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(6, '9 Tindal Ave', 'Rochelle Park', 'NJ', '08796', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(7, '21 Sesame St', 'Raleigh', 'NC', '77796', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(8, '88 100th St', 'Herndon', 'VA', '59877', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(9, '23 1st St', 'Secaucus', 'NJ', '09877', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(10, '11 Independance Ave', 'Pittsburg', 'PA', '09327', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(11, '11 Independance Ave', 'Pittsburg', 'PA', '09327', 'USA');

insert into BUSINESS(id, name, description, business_type) values(15, 'Comcast Inc', 'Comcast' , 'Client');
insert into BUSINESS(id, name, description, business_type) values(16, 'INSYS Group Inc', 'INSYS Group Pivotal Practice', 'INSYS');
insert into BUSINESS(id, name, description, business_type) values(17, 'Pivotal Cloud Foundry', 'Pivotal Cloud Foundry', 'Pivotal');
insert into BUSINESS(id, name, description, business_type) values(18, 'Pivotal Labs', 'Pivotal Labs', 'PivotalLabs');
insert into BUSINESS(id, name, description, business_type) values(19, 'Apptium Inc', 'Apptium Solutions', 'Vendor');

insert into LOCATION(business_id , address_id) values(15, 1);
insert into LOCATION(business_id , address_id) values(15, 3);
insert into LOCATION(business_id , address_id) values(15, 2);
insert into LOCATION(business_id , address_id) values(16, 4);

insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(10, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'Employee', 5, 16);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(11, 'Brad', 'Starkenberg', '876-99-3427', 'bstarken@insys.com', 'Architect', 'Employee', 6, 16);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(12, 'Eric', 'Pereira', '876-99-3427', 'epereira@insys.com', 'Architect', 'Employee', 7, 16);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(13, 'Michael', 'Orth', '237-28-3427', 'morth@apptium.com', 'Manager', 'Vendor', 8, 19);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(14, 'Kevin', 'Meloney', '872-28-1297', 'kmulder@comcast.com', 'Manager', 'Client', 9, 15);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(15, 'Chris', 'Umbel', '297-28-1276', 'cumbel@pivotal.com', 'Architect', 'Pivotal', 10, 17);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(16, 'John', 'Snow', '111-11-1111', 'jsnow@gmail.com', 'Developer', 'Candidate', 11, 16);

insert into ROLE(id , name) values(1 , 'Java Developer');
insert into SKILL(id , name) values(1 , 'Java JDK');
insert into SKILL(id , name) values(2 , 'Spring MVC');
insert into SKILL(id , name) values(3 , 'Spring Boot');
insert into ROLE_SKILL values(1 , 1);
insert into ROLE_SKILL values(1 , 2);
insert into ROLE_SKILL values(1 , 3);

insert into ROLE(id , name) values(2 , 'Web Developer');
insert into SKILL(id , name) values(4 , 'Angular');
insert into SKILL(id , name) values(5 , 'Bootstrap');
insert into SKILL(id , name) values(6 , 'Node');
insert into ROLE_SKILL values(2 , 4);
insert into ROLE_SKILL values(2 , 5);
insert into ROLE_SKILL values(2 , 6);

insert into INTERVIEW(id, date, name, phone, person_id, role_id) values(1, 1482475703, 'Interview 1', '7035551234', 10, 1);
insert into QUESTION(id, question, answer, comment, quality) values(0, 'What do?', 'Stuff', 'Comment', 'Excellent');

insert into PERSON_SKILL(id, person_id, name, scale) values(1, 10, 'Spring', 8);

insert into STATE(id , state_code) values(1 , 'AL');
insert into STATE(id , state_code) values(2 , 'AK');
insert into STATE(id , state_code) values(3 , 'AZ');
insert into STATE(id , state_code) values(4 , 'AR');
insert into STATE(id , state_code) values(5 , 'CA');
insert into STATE(id , state_code) values(6 , 'CO');
insert into STATE(id , state_code) values(7 , 'CT');
insert into STATE(id , state_code) values(8 , 'DE');
insert into STATE(id , state_code) values(9 , 'DC');
insert into STATE(id , state_code) values(10 , 'FL');
insert into STATE(id , state_code) values(11 , 'GA');
insert into STATE(id , state_code) values(12 , 'HI');
insert into STATE(id , state_code) values(13 , 'ID');
insert into STATE(id , state_code) values(14 , 'IL');
insert into STATE(id , state_code) values(15 , 'IN');
insert into STATE(id , state_code) values(16 , 'IA');
insert into STATE(id , state_code) values(17 , 'KS');
insert into STATE(id , state_code) values(18 , 'KY');
insert into STATE(id , state_code) values(19 , 'LA');
insert into STATE(id , state_code) values(20 , 'ME');
insert into STATE(id , state_code) values(21 , 'MD');
insert into STATE(id , state_code) values(22 , 'MA');
insert into STATE(id , state_code) values(23 , 'MI');
insert into STATE(id , state_code) values(24 , 'MN');
insert into STATE(id , state_code) values(25 , 'MS');
insert into STATE(id , state_code) values(26 , 'MO');
insert into STATE(id , state_code) values(27 , 'MT');
insert into STATE(id , state_code) values(28 , 'NE');
insert into STATE(id , state_code) values(29 , 'NV');
insert into STATE(id , state_code) values(30 , 'NH');
insert into STATE(id , state_code) values(31 , 'NJ');
insert into STATE(id , state_code) values(32 , 'NM');
insert into STATE(id , state_code) values(33 , 'NY');
insert into STATE(id , state_code) values(34 , 'NC');
insert into STATE(id , state_code) values(35 , 'ND');
insert into STATE(id , state_code) values(36 , 'OH');
insert into STATE(id , state_code) values(37 , 'OK');
insert into STATE(id , state_code) values(38 , 'OR');
insert into STATE(id , state_code) values(39 , 'PA');
insert into STATE(id , state_code) values(40 , 'RI');
insert into STATE(id , state_code) values(41 , 'SC');
insert into STATE(id , state_code) values(42 , 'SD');
insert into STATE(id , state_code) values(43 , 'TN');
insert into STATE(id , state_code) values(44 , 'TX');
insert into STATE(id , state_code) values(45 , 'UT');
insert into STATE(id , state_code) values(46 , 'VT');
insert into STATE(id , state_code) values(47 , 'VA');
insert into STATE(id , state_code) values(48 , 'WA');
insert into STATE(id , state_code) values(49 , 'WV');
insert into STATE(id , state_code) values(50 , 'WV');
insert into STATE(id , state_code) values(51 , 'WY');

--insert into INTERVIEW_TEMPLATE() values();
insert into TRAINING(id, name, address_id, online) values(1, 'Pivotal training', 1, FALSE);

insert into TRAINING_TASK(id, name, weblink) values (1, 'Training Task 1', 'http://training.org/1');
insert into TRAINING_TASK(id, name, weblink) values (2, 'Training Task 2', 'http://training.org/2');
insert into TRAINING_TASK(id, name, weblink) values (3, 'Training Task 3', 'http://training.org/3');

insert into TRAINING_TASKS(training_id, tasks_id) values (1, 1);
insert into TRAINING_TASKS(training_id, tasks_id) values (1, 2);
insert into TRAINING_TASKS(training_id, tasks_id) values (1, 3);
