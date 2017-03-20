--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(1, '10 Park Ave' , 'Manhatten' , 'NY' , '10010', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(2, '100 Business Ave', 'Manhatten' , 'NY' , '10012', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(3, '100 Comcast Ave', 'Philadelphia', 'PA', '88790', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(4, '10 INSYS Ave', 'Rochelle Park', 'NJ', '06786', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(5, '3 Gerson Rd', 'Robbinsville', 'NJ', '08691', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(6, '9 Tindal Ave', 'Rochelle Park', 'NJ', '08796', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(7, '21 Sesame St', 'Raleigh', 'NC', '77796', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(8, '88 100th St', 'Herndon', 'VA', '59877', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(9, '23 1st St', 'Secaucus', 'NJ', '09877', 'USA', 1);
--INSERT INTO address(id, address_1 , city , state, zip_code, country, version) VALUES(10, '11 Independance Ave', 'Pittsburg', 'PA', '09327', 'USA', 1);
--
--INSERT INTO business(id, name, description, business_type, version) VALUES(15, 'Comcast Inc', 'Comcast' , 'Client', 1);
--INSERT INTO business(id, name, description, business_type, version) VALUES(16, 'INSYS Group Inc', 'INSYS Group Pivotal Practice', 'INSYS', 1);
--INSERT INTO business(id, name, description, business_type, version) VALUES(17, 'Pivotal Cloud Foundry', 'Pivotal Cloud Foundry', 'Pivotal', 1);
--INSERT INTO business(id, name, description, business_type, version) VALUES(18, 'Pivotal Labs', 'Pivotal Labs', 'PivotalLabs', 1);
--INSERT INTO business(id, name, description, business_type, version) VALUES(19, 'Apptium Inc', 'Apptium Solutions', 'Vendor', 1);
--
--INSERT INTO location(business_id , address_id) VALUES(15 , 1);
--INSERT INTO location(business_id , address_id) VALUES(15 , 3);
--INSERT INTO location(business_id , address_id) VALUES(15 , 2);
--INSERT INTO location(business_id , address_id) VALUES(16 , 4);
--
--INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) VALUES(10, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'Employee', 5, 16, 1);
--INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) VALUES(11, 'Brad', 'Starkenberg', '876-99-3427', 'bstarken@insys.com', 'Architect', 'Employee', 6, 16, 1);
--INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) VALUES(12, 'Eric', 'Pereira', '876-99-3427', 'epereira@insys.com', 'Architect', 'Employee', 7, 16, 1);
--INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) VALUES(13, 'Michael', 'Orth', '237-28-3427', 'morth@apptium.com', 'Manager', 'Vendor', 8, 19, 1);
--INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) VALUES(14, 'Kevin', 'Meloney', '872-28-1297', 'kmulder@comcast.com', 'Manager', 'Client', 9, 15, 1);
--INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id, version) VALUES(15, 'Chris', 'Umbel', '297-28-1276', 'cumbel@pivotal.com', 'Architect', 'Pivotal', 10, 17, 1);
--
--INSERT INTO person_SKILL(id, person_id, name, scale, version) VALUES(1, 10, 'Spring', 8, 1);
--
--INSERT INTO ROLE(id , name, version) VALUES(1 , 'Developer', 1);
--INSERT INTO SKILL(id , name, version) VALUES(1 , 'Java', 1);
--INSERT INTO ROLE_SKILL VALUES(1 , 1);

INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(1, '10 Park Ave' , 'Manhatten' , 'NY' , '10010', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(2, '100 Business Ave', 'Manhatten' , 'NY' , '10012', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(3, '100 Comcast Ave', 'Philadelphia', 'PA', '88790', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(4, '10 INSYS Ave', 'Rochelle Park', 'NJ', '06786', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(5, '3 Gerson Rd', 'Robbinsville', 'NJ', '08691', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(6, '9 Tindal Ave', 'Rochelle Park', 'NJ', '08796', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(7, '21 Sesame St', 'Raleigh', 'NC', '77796', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(8, '88 100th St', 'Herndon', 'VA', '59877', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(9, '23 1st St', 'Secaucus', 'NJ', '09877', 'USA');
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(10, '11 Independance Ave', 'Pittsburg', 'PA', '09327', 'USA');

INSERT INTO business(id, name, description, business_type) VALUES(15, 'Comcast Inc', 'Comcast' , 'Client');
INSERT INTO business(id, name, description, business_type) VALUES(16, 'INSYS Group Inc', 'INSYS Group Pivotal Practice', 'INSYS');
INSERT INTO business(id, name, description, business_type) VALUES(17, 'Pivotal Cloud Foundry', 'Pivotal Cloud Foundry', 'Pivotal');
INSERT INTO business(id, name, description, business_type) VALUES(18, 'Pivotal Labs', 'Pivotal Labs', 'PivotalLabs');
INSERT INTO business(id, name, description, business_type) VALUES(19, 'Apptium Inc', 'Apptium Solutions', 'Vendor');

INSERT INTO location(business_id , address_id) VALUES(15, 1 );
INSERT INTO location(business_id , address_id) VALUES(15, 3);
INSERT INTO location(business_id , address_id) VALUES(15, 2);
INSERT INTO location(business_id , address_id) VALUES(16, 4);

INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(10, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'Employee', 5, 16);
INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(11, 'Brad', 'Starkenberg', '876-99-3427', 'bstarken@insys.com', 'Architect', 'Employee', 6, 16);
INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(12, 'Eric', 'Pereira', '876-99-3427', 'epereira@insys.com', 'Architect', 'Employee', 7, 16);
INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(13, 'Michael', 'Orth', '237-28-3427', 'morth@apptium.com', 'Manager', 'Vendor', 8, 19);
INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(14, 'Kevin', 'Meloney', '872-28-1297', 'kmulder@comcast.com', 'Manager', 'Client', 9, 15);
INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(15, 'Chris', 'Umbel', '297-28-1276', 'cumbel@pivotal.com', 'Architect', 'Pivotal', 10, 17);

INSERT INTO person_skill(id, person_id, name, scale) VALUES(1, 10, 'Spring', 8);

INSERT INTO role(id , name) VALUES(1 , 'Developer');
INSERT INTO skill(id , name) VALUES(1 , 'Java');
INSERT INTO role_skill VALUES(1 , 1);

INSERT INTO user (username, password, person_id, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES('msabir', 'password', 10, true, true, true, true);
INSERT INTO user_authority(id, username, authority) VALUES (1, 'msabir', 'ADMIN');

INSERT INTO Client (client_id, client_secret, secret_required, scopes, scoped, grant_types, access_token_validity_seconds,refresh_token_validity_seconds, auto_approve)
VALUES ('trapps-application-iwquy-ienke-qnbek-iuysc-pwirh', 'trapps-application-secret-oiweds-iujwsd-ujrqcd-iorcl-klmvek', true, 'read,write', true, 'authorization_code,refresh_token,password,client_credentials', 86400, 0, true);

INSERT INTO state(id , state_code) VALUES(1 , 'AL');
INSERT INTO state(id , state_code) VALUES(2 , 'AK');
INSERT INTO state(id , state_code) VALUES(3 , 'AZ');
INSERT INTO state(id , state_code) VALUES(4 , 'AR');
INSERT INTO state(id , state_code) VALUES(5 , 'CA');
INSERT INTO state(id , state_code) VALUES(6 , 'CO');
INSERT INTO state(id , state_code) VALUES(7 , 'CT');
INSERT INTO state(id , state_code) VALUES(8 , 'DE');
INSERT INTO state(id , state_code) VALUES(9 , 'DC');
INSERT INTO state(id , state_code) VALUES(10 , 'FL');
INSERT INTO state(id , state_code) VALUES(11 , 'GA');
INSERT INTO state(id , state_code) VALUES(12 , 'HI');
INSERT INTO state(id , state_code) VALUES(13 , 'ID');
INSERT INTO state(id , state_code) VALUES(14 , 'IL');
INSERT INTO state(id , state_code) VALUES(15 , 'IN');
INSERT INTO state(id , state_code) VALUES(16 , 'IA');
INSERT INTO state(id , state_code) VALUES(17 , 'KS');
INSERT INTO state(id , state_code) VALUES(18 , 'KY');
INSERT INTO state(id , state_code) VALUES(19 , 'LA');
INSERT INTO state(id , state_code) VALUES(20 , 'ME');
INSERT INTO state(id , state_code) VALUES(21 , 'MD');
INSERT INTO state(id , state_code) VALUES(22 , 'MA');
INSERT INTO state(id , state_code) VALUES(23 , 'MI');
INSERT INTO state(id , state_code) VALUES(24 , 'MN');
INSERT INTO state(id , state_code) VALUES(25 , 'MS');
INSERT INTO state(id , state_code) VALUES(26 , 'MO');
INSERT INTO state(id , state_code) VALUES(27 , 'MT');
INSERT INTO state(id , state_code) VALUES(28 , 'NE');
INSERT INTO state(id , state_code) VALUES(29 , 'NV');
INSERT INTO state(id , state_code) VALUES(30 , 'NH');
INSERT INTO state(id , state_code) VALUES(31 , 'NJ');
INSERT INTO state(id , state_code) VALUES(32 , 'NM');
INSERT INTO state(id , state_code) VALUES(33 , 'NY');
INSERT INTO state(id , state_code) VALUES(34 , 'NC');
INSERT INTO state(id , state_code) VALUES(35 , 'ND');
INSERT INTO state(id , state_code) VALUES(36 , 'OH');
INSERT INTO state(id , state_code) VALUES(37 , 'OK');
INSERT INTO state(id , state_code) VALUES(38 , 'OR');
INSERT INTO state(id , state_code) VALUES(39 , 'PA');
INSERT INTO state(id , state_code) VALUES(40 , 'RI');
INSERT INTO state(id , state_code) VALUES(41 , 'SC');
INSERT INTO state(id , state_code) VALUES(42 , 'SD');
INSERT INTO state(id , state_code) VALUES(43 , 'TN');
INSERT INTO state(id , state_code) VALUES(44 , 'TX');
INSERT INTO state(id , state_code) VALUES(45 , 'UT');
INSERT INTO state(id , state_code) VALUES(46 , 'VT');
INSERT INTO state(id , state_code) VALUES(47 , 'VA');
INSERT INTO state(id , state_code) VALUES(48 , 'WA');
INSERT INTO state(id , state_code) VALUES(49 , 'WV');
INSERT INTO state(id , state_code) VALUES(50 , 'WV');
INSERT INTO state(id , state_code) VALUES(51 , 'WY');

INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(11, '2101 N Ursula St Apt. 323', 'Aurora', 'CO', '80045', 'USA');
INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(16, 'Armando', 'Reyna', '720-560-8971', 'areyna@insys.com', 'Architect', 'Employee', 11, 16);
INSERT INTO user (username, password, person_id, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES('areyna', 'cb5af25a063f751372465c0f27ef9cada13ecd8d850564ebcab690d0c725ffddf6786887c444f016', 16, true, true, true, true);
INSERT INTO user_authority(id, username, authority) VALUES (2, 'areyna', 'ADMIN');

insert into PERSON(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) values(17, 'John', 'Snow', '111-11-1111', 'jsnow@gmail.com', 'Developer', 'Candidate', 11, 16);
