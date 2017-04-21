alter TABLE user add column password_changed BIT NOT NULL DEFAULT 0;

delete from user_authority;
delete from user;

INSERT INTO address(id, address_1 , city , state, zip_code, country) VALUES(999, 'xxx', 'xxx', 'xxx', 'xxx', 'xxx');
INSERT INTO person(id, first_name, last_name , phone, email, title, person_type, address_id, business_id) VALUES(999, 'Admin', 'Admin', '00000000', 'test@insys.com', 'Admin', 'Employee', 999, 16);

INSERT INTO user (username, password, person_id, account_non_expired, account_non_locked, credentials_non_expired, enabled, password_changed) VALUES('admin', 'cb5af25a063f751372465c0f27ef9cada13ecd8d850564ebcab690d0c725ffddf6786887c444f016', 999, true, true, true, true, true);
INSERT INTO user_authority(username, authority) VALUES ('admin', 'ADMIN');