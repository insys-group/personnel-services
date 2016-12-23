insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(1, '10 Park Ave' , 'Manhatten' , 'NY' , '10010', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(2, '100 Business Ave', 'Manhatten' , 'NY' , '10012', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(3, '100 Comcast Ave', 'Philadelphia', 'PA', '88790', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(4, '10 INSYS Ave', 'Rochelle Park', 'NJ', '06786', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(5, '3 Gerson Rd', 'Robbinsville', 'NJ', '08691', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(6, '9 Tindal Ave', 'Rochelle Park', 'NJ', '08796', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(7, '21 Sesame St', 'Raleigh', 'NC', '77796', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(8, '88 100th St', 'Herndon', 'VA', '59877', 'USA', 1);

insert into BUSINESS(id, name, description, business_type, version) values(15, 'Comcast Inc', 'Comcast' , 'Client', 1);
insert into BUSINESS(id, name, description, business_type, version) values(16, 'INSYS Group Inc', 'INSYS Group Pivotal Practice', 'INSYS', 1);
insert into BUSINESS(id, name, description, business_type, version) values(17, 'Pivotal Cloud Foundry', 'Pivotal Cloud Foundry', 'Pivotal', 1);
insert into BUSINESS(id, name, description, business_type, version) values(18, 'Pivotal Labs', 'Pivotal Labs', 'PivotalLabs', 1);
insert into BUSINESS(id, name, description, business_type, version) values(19, 'Apptium Inc', 'Apptium Solutions', 'Vendor', 1);

insert into LOCATION(business_id , address_id) values(15 , 1);
insert into LOCATION(business_id , address_id) values(15 , 2);
insert into LOCATION(business_id , address_id) values(16 , 3);

insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(10, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'Employee', 5, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(11, 'Brad', 'Starkenberg', '876-99-3427', 'bstarken@insys.com', 'Architect', 'Employee', 6, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(12, 'Eric', 'Pereira', '876-99-3427', 'epereira@insys.com', 'Architect', 'Employee', 7, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(13, 'Michael', 'Orth', '237-28-3427', 'morth@apptium.com', 'Manager', 'Vendor', 8, 19, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(14, 'Kevin', 'Mulder', '872-28-1297', 'kmulder@comcast.com', 'Manager', 'Client', 7, 15, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(15, 'Chris', 'Umbel', '297-28-1276', 'cumbel@pivotal.com', 'Architect', 'Pivotal', 5, 17, 1);

insert into ROLE(id , name, version) values(1 , 'Developer' , 1);
insert into SKILL(id , name, version) values(1 , 'Java' , 1);
insert into ROLE_SKILL values(1 , 1);

insert into INTERVIEW(id, date, phone, person_id, role_id, version) values(0, 1482475703, '7035551234', 10, 1, 1);
insert into QUESTION(id, question, answer, comment, quality, version) values(0, 'What do?', 'Stuff', 'Comment', 'Excellent', 1);
insert into FEEDBACK(id, person_id, comment, version) values(0, 11, 'Comment', 1);

insert into INTERVIEWERS(interview_id, person_id) values(0, 11);
insert into QUESTIONS(interview_id, question_id) values(0, 0);
insert into INT_FEEDBACK(interview_id, feedback_id) values(0, 0);


