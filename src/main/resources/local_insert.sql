insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(1, '10 Park Ave' , 'Manhatten' , 'NY' , '10010', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(2, '100 Business Ave', 'Manhatten' , 'NY' , '10012', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(3, '100 Comcast Ave', 'Philadelphia', 'PA', '88790', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(4, '10 INSYS Ave', 'Rochelle Park', 'NJ', '06786', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(5, '3 Gerson Rd', 'Robbinsville', 'NJ', '08691', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(6, '9 Tindal Ave', 'Rochelle Park', 'NJ', '08796', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(7, '21 Sesame St', 'Raleigh', 'NC', '77796', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(8, '88 100th St', 'Herndon', 'VA', '59877', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(9, '23 1st St', 'Secaucus', 'NJ', '09877', 'USA', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(10, '11 Independance Ave', 'Pittsburg', 'PA', '09327', 'USA', 1);

insert into BUSINESS(id, name, description, business_type, version) values(15, 'Comcast Inc', 'Comcast' , 'Client', 1);
insert into BUSINESS(id, name, description, business_type, version) values(16, 'INSYS Group Inc', 'INSYS Group Pivotal Practice', 'INSYS', 1);
insert into BUSINESS(id, name, description, business_type, version) values(17, 'Pivotal Cloud Foundry', 'Pivotal Cloud Foundry', 'Pivotal', 1);
insert into BUSINESS(id, name, description, business_type, version) values(18, 'Pivotal Labs', 'Pivotal Labs', 'PivotalLabs', 1);
insert into BUSINESS(id, name, description, business_type, version) values(19, 'Apptium Inc', 'Apptium Solutions', 'Vendor', 1);

insert into LOCATION(business_id , address_id) values(15 , 1);
insert into LOCATION(business_id , address_id) values(15 , 3);
insert into LOCATION(business_id , address_id) values(15 , 2);
insert into LOCATION(business_id , address_id) values(16 , 4);

insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(10, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'Employee', 5, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(11, 'Brad', 'Starkenberg', '876-99-3427', 'bstarken@insys.com', 'Architect', 'Employee', 6, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(12, 'Eric', 'Pereira', '876-99-3427', 'epereira@insys.com', 'Architect', 'Employee', 7, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(13, 'Michael', 'Orth', '237-28-3427', 'morth@apptium.com', 'Manager', 'Vendor', 8, 19, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(14, 'Kevin', 'Meloney', '872-28-1297', 'kmulder@comcast.com', 'Manager', 'Client', 9, 15, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(15, 'Chris', 'Umbel', '297-28-1276', 'cumbel@pivotal.com', 'Architect', 'Pivotal', 10, 17, 1);

insert into ROLE(id , name, version) values(1 , 'Developer' , 1);
insert into SKILL(id , name, version) values(1 , 'Java' , 1);
insert into ROLE_SKILL values(1 , 1);

insert into INTERVIEW(id, date, phone, person_id, role_id, version) values(0, 1482475703, '7035551234', 10, 1, 1);
insert into QUESTION(id, question, answer, comment, quality, version) values(0, 'What do?', 'Stuff', 'Comment', 'Excellent', 1);
insert into FEEDBACK(id, person_id, comment, version) values(0, 11, 'Comment', 1);

insert into INTERVIEWERS(interview_id, person_id) values(0, 11);
insert into QUESTIONS(interview_id, question_id) values(0, 0);
insert into INT_FEEDBACK(interview_id, feedback_id) values(0, 0);

insert into PERSON_SKILL(id, person_id, name, scale, version) values(1, 10, 'Spring', 8, 1);

insert into ROLE(id , name, version) values(1 , 'Developer', 1);
insert into SKILL(id , name, version) values(1 , 'Java', 1);
insert into ROLE_SKILL values(1 , 1);

insert into STATE(id , state_code, version) values(1 , 'AL', 1);
insert into STATE(id , state_code, version) values(2 , 'AK', 1);
insert into STATE(id , state_code, version) values(3 , 'AZ', 1);
insert into STATE(id , state_code, version) values(4 , 'AR', 1);
insert into STATE(id , state_code, version) values(5 , 'CA', 1);
insert into STATE(id , state_code, version) values(6 , 'CO', 1);
insert into STATE(id , state_code, version) values(7 , 'CT', 1);
insert into STATE(id , state_code, version) values(8 , 'DE', 1);
insert into STATE(id , state_code, version) values(9 , 'DC', 1);
insert into STATE(id , state_code, version) values(10 , 'FL', 1);
insert into STATE(id , state_code, version) values(11 , 'GA', 1);
insert into STATE(id , state_code, version) values(12 , 'HI', 1);
insert into STATE(id , state_code, version) values(13 , 'ID', 1);
insert into STATE(id , state_code, version) values(14 , 'IL', 1);
insert into STATE(id , state_code, version) values(15 , 'IN', 1);
insert into STATE(id , state_code, version) values(16 , 'IA', 1);
insert into STATE(id , state_code, version) values(17 , 'KS', 1);
insert into STATE(id , state_code, version) values(18 , 'KY', 1);
insert into STATE(id , state_code, version) values(19 , 'LA', 1);
insert into STATE(id , state_code, version) values(20 , 'ME', 1);
insert into STATE(id , state_code, version) values(21 , 'MD', 1);
insert into STATE(id , state_code, version) values(22 , 'MA', 1);
insert into STATE(id , state_code, version) values(23 , 'MI', 1);
insert into STATE(id , state_code, version) values(24 , 'MN', 1);
insert into STATE(id , state_code, version) values(25 , 'MS', 1);
insert into STATE(id , state_code, version) values(26 , 'MO', 1);
insert into STATE(id , state_code, version) values(27 , 'MT', 1);
insert into STATE(id , state_code, version) values(28 , 'NE', 1);
insert into STATE(id , state_code, version) values(29 , 'NV', 1);
insert into STATE(id , state_code, version) values(30 , 'NH', 1);
insert into STATE(id , state_code, version) values(31 , 'NJ', 1);
insert into STATE(id , state_code, version) values(32 , 'NM', 1);
insert into STATE(id , state_code, version) values(33 , 'NY', 1);
insert into STATE(id , state_code, version) values(34 , 'NC', 1);
insert into STATE(id , state_code, version) values(35 , 'ND', 1);
insert into STATE(id , state_code, version) values(36 , 'OH', 1);
insert into STATE(id , state_code, version) values(37 , 'OK', 1);
insert into STATE(id , state_code, version) values(38 , 'OR', 1);
insert into STATE(id , state_code, version) values(39 , 'PA', 1);
insert into STATE(id , state_code, version) values(40 , 'RI', 1);
insert into STATE(id , state_code, version) values(41 , 'SC', 1);
insert into STATE(id , state_code, version) values(42 , 'SD', 1);
insert into STATE(id , state_code, version) values(43 , 'TN', 1);
insert into STATE(id , state_code, version) values(44 , 'TX', 1);
insert into STATE(id , state_code, version) values(45 , 'UT', 1);
insert into STATE(id , state_code, version) values(46 , 'VT', 1);
insert into STATE(id , state_code, version) values(47 , 'VA', 1);
insert into STATE(id , state_code, version) values(48 , 'WA', 1);
insert into STATE(id , state_code, version) values(49 , 'WV', 1);
insert into STATE(id , state_code, version) values(50 , 'WV', 1);
insert into STATE(id , state_code, version) values(51 , 'WY', 1);
