
INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(11, '2101 N Ursula St Apt. 323', 'Aurora', 'CO', '80045', 'USA');
INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(16, 'Armando', 'Reyna', '720-560-8971', 'areyna@insys.com', 'Architect', 'Employee', 11, 16);
INSERT INTO user (username, password, person_id, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES('areyna', 'password', 16, true, true, true, true);
INSERT INTO user_authority(id, username, authority) VALUES (2, 'areyna', 'ADMIN');