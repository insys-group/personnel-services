insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(1, '10 Park Ave' , 'Manhatten' , 'NY' , '10010', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(2, '100 Business Ave', 'Manhatten' , 'NY' , '10012', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(3, '100 Comcast Ave', 'Philadelphia', 'PA', '88790', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(4, '10 INSYS Ave', 'Rochelle Park', 'NJ', '06786', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(5, '3 Gerson Rd', 'Robbinsville', 'NJ', '08691', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(6, '9 Tindal Ave', 'Rochelle Park', 'NJ', '08796', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(7, '21 Sesame St', 'Raleigh', 'NC', '77796', 1);
insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(8, '88 100th St', 'Herndon', 'VA', '59877', 1);

insert into BUSINESS(id, name, description, business_type, version) values(15, 'Comcast Inc', 'Comcast' , 'Client', 1);
insert into BUSINESS(id, name, description, business_type, version) values(16, 'INSYS Group Inc', 'INSYS Group Pivotal Practice', 'INSYS', 1);
insert into BUSINESS(id, name, description, business_type, version) values(17, 'Pivotal Cloud Foundry', 'Pivotal Cloud Foundry', 'Pivotal', 1);
insert into BUSINESS(id, name, description, business_type, version) values(18, 'Pivotal Labs', 'Pivotal Labs', 'PivotalLabs', 1);
insert into BUSINESS(id, name, description, business_type, version) values(19, 'Apptium Inc', 'Apptium Solutions', 'Vendor', 1);

insert into LOCATION(business_id , address_id) values(15 , 3);
insert into LOCATION(business_id , address_id) values(15 , 8);
insert into LOCATION(business_id , address_id) values(16 , 4);

insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(10, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'Employee', 5, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(11, 'Brad', 'Starkenberg', '876-99-3427', 'bstarken@insys.com', 'Architect', 'Employee', 6, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(12, 'Eric', 'Pereira', '876-99-3427', 'epereira@insys.com', 'Architect', 'Employee', 7, 16, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(13, 'Michael', 'Orth', '237-28-3427', 'morth@apptium.com', 'Manager', 'Vendor', 1, 19, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(14, 'Kevin', 'Mulder', '872-28-1297', 'kmulder@comcast.com', 'Manager', 'Client', 3, 15, 1);
insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) values(15, 'Chris', 'Umbel', '297-28-1276', 'cumbel@pivotal.com', 'Architect', 'Pivotal', 2, 17, 1);

