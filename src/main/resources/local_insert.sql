--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(1, '10 Park Ave' , 'Manhatten' , 'NY' , '10010', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(2, '100 Business Ave', 'Manhatten' , 'NY' , '10012', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(3, '100 Comcast Ave', 'Philadelphia', 'PA', '88790', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(4, '10 INSYS Ave', 'Rochelle Park', 'NJ', '06786', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(5, '3 Gerson Rd', 'Robbinsville', 'NJ', '08691', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(6, '9 Tindal Ave', 'Rochelle Park', 'NJ', '08796', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(7, '21 Sesame St', 'Raleigh', 'NC', '77796', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(8, '88 100th St', 'Herndon', 'VA', '59877', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(9, '23 1st St', 'Secaucus', 'NJ', '09877', 'USA', 1);
--insert into ADDRESS(id, address_1 , city , state, zip_code, country, version) values(10, '11 Independance Ave', 'Pittsburg', 'PA', '09327', 'USA', 1);
--
--insert into BUSINESS(id, name, description, business_type, version) values(15, 'Comcast Inc', 'Comcast' , 'Client', 1);
--insert into BUSINESS(id, name, description, business_type, version) values(16, 'INSYS Group Inc', 'INSYS Group Pivotal Practice', 'INSYS', 1);
--insert into BUSINESS(id, name, description, business_type, version) values(17, 'Pivotal Cloud Foundry', 'Pivotal Cloud Foundry', 'Pivotal', 1);
--insert into BUSINESS(id, name, description, business_type, version) values(18, 'Pivotal Labs', 'Pivotal Labs', 'PivotalLabs', 1);
--insert into BUSINESS(id, name, description, business_type, version) values(19, 'Apptium Inc', 'Apptium Solutions', 'Vendor', 1);
--
--insert into LOCATION(business_id , address_id) values(15 , 1);
--insert into LOCATION(business_id , address_id) values(15 , 3);
--insert into LOCATION(business_id , address_id) values(15 , 2);
--insert into LOCATION(business_id , address_id) values(16 , 4);
--
--insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(10, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'Employee', 5, 16, 1);
--insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(11, 'Brad', 'Starkenberg', '876-99-3427', 'bstarken@insys.com', 'Architect', 'Employee', 6, 16, 1);
--insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(12, 'Eric', 'Pereira', '876-99-3427', 'epereira@insys.com', 'Architect', 'Employee', 7, 16, 1);
--insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(13, 'Michael', 'Orth', '237-28-3427', 'morth@apptium.com', 'Manager', 'Vendor', 8, 19, 1);
--insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(14, 'Kevin', 'Meloney', '872-28-1297', 'kmulder@comcast.com', 'Manager', 'Client', 9, 15, 1);
--insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(15, 'Chris', 'Umbel', '297-28-1276', 'cumbel@pivotal.com', 'Architect', 'Pivotal', 10, 17, 1);
--
--insert into PERSON_SKILL(id, person_id, name, scale, version) values(1, 10, 'Spring', 8, 1);
--
--insert into ROLE(id , name, version) values(1 , 'Developer', 1);
--insert into SKILL(id , name, version) values(1 , 'Java', 1);
--insert into ROLE_SKILL values(1 , 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(1, '10 Park Ave' , 'Manhatten' , 'NY' , '10010', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(2, '100 Business Ave', 'Manhatten' , 'NY' , '10012', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(3, '100 Comcast Ave', 'Philadelphia', 'PA', '88790', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(4, '10 INSYS Ave', 'Rochelle Park', 'NJ', '06786', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(5, '3 Gerson Rd', 'Robbinsville', 'NJ', '08691', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(6, '9 Tindal Ave', 'Rochelle Park', 'NJ', '08796', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(7, '21 Sesame St', 'Raleigh', 'NC', '77796', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(8, '88 100th St', 'Herndon', 'VA', '59877', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(9, '23 1st St', 'Secaucus', 'NJ', '09877', 'USA');
insert into ADDRESS(id, address_1 , city , state, zip_code, country) values(10, '11 Independance Ave', 'Pittsburg', 'PA', '09327', 'USA');

insert into BUSINESS(id, name, description, business_type) values(15, 'Comcast Inc', 'Comcast' , 'Client');
insert into BUSINESS(id, name, description, business_type) values(16, 'INSYS Group Inc', 'INSYS Group Pivotal Practice', 'INSYS');
insert into BUSINESS(id, name, description, business_type) values(17, 'Pivotal Cloud Foundry', 'Pivotal Cloud Foundry', 'Pivotal');
insert into BUSINESS(id, name, description, business_type) values(18, 'Pivotal Labs', 'Pivotal Labs', 'PivotalLabs');
insert into BUSINESS(id, name, description, business_type) values(19, 'Apptium Inc', 'Apptium Solutions', 'Vendor');

insert into LOCATION(business_id , address_id) values(15, 1 );
insert into LOCATION(business_id , address_id) values(15, 3);
insert into LOCATION(business_id , address_id) values(15, 2);
insert into LOCATION(business_id , address_id) values(16, 4);

insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(10, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'Employee', 5, 16);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(11, 'Brad', 'Starkenberg', '876-99-3427', 'bstarken@insys.com', 'Architect', 'Employee', 6, 16);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(12, 'Eric', 'Pereira', '876-99-3427', 'epereira@insys.com', 'Architect', 'Employee', 7, 16);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(13, 'Michael', 'Orth', '237-28-3427', 'morth@apptium.com', 'Manager', 'Vendor', 8, 19);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(14, 'Kevin', 'Meloney', '872-28-1297', 'kmulder@comcast.com', 'Manager', 'Client', 9, 15);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(15, 'Chris', 'Umbel', '297-28-1276', 'cumbel@pivotal.com', 'Architect', 'Pivotal', 10, 17);

insert into PERSON_SKILL(id, person_id, name, scale) values(1, 10, 'Spring', 8, 1);

insert into ROLE(id , name) values(1 , 'Developer');
insert into SKILL(id , name) values(1 , 'Java');
insert into ROLE_SKILL values(1 , 1);