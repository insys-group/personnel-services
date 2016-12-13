insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(7, 'address 1' , 'Paramus' , 'NJ' , '34343', CURRENT_TIMESTAMP());
insert into ADDRESS(id, address_1 , city , state, zip_code, version) values(18, 'address 2' , 'Paramus2' , 'NS' , '37743', CURRENT_TIMESTAMP());
insert into BUSINESS_ENTITY(id, name , descr, entity_type, version) values(15, 'business_entity 1', 'business_entity 1' , 'INSYS', CURRENT_TIMESTAMP());
insert into LOCATION(business_entity_id , address_id) values(15 , 7);
insert into LOCATION(business_entity_id , address_id) values(15 , 18);

insert into OPPORTUNITY(id, business_entity_id , comments, version) values(9, 15 , 'opportunity 1', CURRENT_TIMESTAMP());
insert into OPPORTUNITY_STEP(id, opportunity_id , step_timestamp,  comments, version) values(25, 9 , CURRENT_TIMESTAMP(), 'opportunity 1 step', CURRENT_TIMESTAMP());
insert into OPPORTUNITY(id, business_entity_id , comments, version) values(10, 15 , 'opportunity 2 ', CURRENT_TIMESTAMP());
insert into OPPORTUNITY_STEP(id, opportunity_id , step_timestamp,  comments, version) values(26, 10 ,CURRENT_TIMESTAMP(), 'opportunity 2 step ', CURRENT_TIMESTAMP());

insert into ENGAGEMENT(id, opportunity_id , comments, version) values(11, 9 , 'engagement 1', CURRENT_TIMESTAMP());
insert into ENGAGEMENT(id, opportunity_id , comments, version) values(12, 10 , 'engagement 2', CURRENT_TIMESTAMP());

insert into ROLE(id, name, version) values(1, 'Test Role 1', CURRENT_TIMESTAMP());
insert into ROLE(id, name, version) values(2, 'Test Role 2', CURRENT_TIMESTAMP());

insert into SKILL(id, name, version) values(3, 'Test skill 1', CURRENT_TIMESTAMP());
insert into SKILL(id, name, version) values(4, 'Test skill 2', CURRENT_TIMESTAMP());

insert into ROLE_SKILL(ROLE_ID, SKILL_ID) values(1, 3);
insert into ROLE_SKILL(ROLE_ID, SKILL_ID) values(2, 4);

insert into ENGAGEMENT_OPENING(id, engagement_id , role_id , address_id , rate , comments, version)  values(13, 11 , 1, 7, 2343.4415, 'engagement_opening 1', CURRENT_TIMESTAMP());
insert into ENGAGEMENT_OPENING(id, engagement_id , role_id , address_id , rate , comments, version)  values(14, 12 , 2, 18, 44.215, 'engagement_opening 2', CURRENT_TIMESTAMP());

insert into PERSON(id, first_name, last_name , phone, email, title ,person_type, address_id, business_entity_id) values(17, 'Muhammad', 'Sabir', '631-983-9075', 'msabir@insys.com', 'Architect', 'EMPLOYEE' , 7, 15);

insert into SEQUENCES(gen_key, gen_value) values('SEQUENCE_ID', 30);