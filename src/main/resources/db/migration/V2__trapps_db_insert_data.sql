--
-- Dumping data for table state
--
INSERT INTO state VALUES (1,'AL',NULL),(2,'AK',NULL),(3,'AZ',NULL),(4,'AR',NULL),(5,'CA',NULL),(6,'CO',NULL),
  (7,'CT',NULL),(8,'DE',NULL),(9,'DC',NULL),(10,'FL',NULL),(11,'GA',NULL),(12,'HI',NULL),(13,'ID',NULL),
  (14,'IL',NULL),(15,'IN',NULL),(16,'IA',NULL),(17,'KS',NULL),(18,'KY',NULL),(19,'LA',NULL),(20,'ME',NULL),
  (21,'MD',NULL),(22,'MA',NULL),(23,'MI',NULL),(24,'MN',NULL),(25,'MS',NULL),(26,'MO',NULL),(27,'MT',NULL),
  (28,'NE',NULL),(29,'NV',NULL),(30,'NH',NULL),(31,'NJ',NULL),(32,'NM',NULL),(33,'NY',NULL),(34,'NC',NULL),
  (35,'ND',NULL),(36,'OH',NULL),(37,'OK',NULL),(38,'OR',NULL),(39,'PA',NULL),(40,'RI',NULL),(41,'SC',NULL),
  (42,'SD',NULL),(43,'TN',NULL),(44,'TX',NULL),(45,'UT',NULL),(46,'VT',NULL),(47,'VA',NULL),(48,'WA',NULL),
  (49,'WV',NULL),(50,'WV',NULL),(51,'WY',NULL);

--
-- Dumping data for table client
--
INSERT INTO client VALUES
  ('trapps-application-iwquy-ienke-qnbek-iuysc-pwirh','trapps-application-secret-oiweds-iujwsd-ujrqcd-iorcl-klmvek',1,'read,write',1,'authorization_code,refresh_token,password,client_credentials',86400,0,1);

--
-- Dumping data for table address
--
INSERT INTO address VALUES
  (1,'2101 N Ursula St Apt. 323',NULL,'Aurora','USA','CO','80045'),
  (2,'395 W Passaic St #4',NULL,'Rochelle Park','USA','NJ','07662'),
  (3,'875 Howard Street','Fifth Floor','San Francisco','USA','CA','94103'),
  (4,'1701 Pearl Street','Suite 200','Boulder','USA','CO','80302');

--
-- Dumping data for table business
--
INSERT INTO business VALUES
  (1,'INSYS','INSYS Group Pivotal Practice','INSYS Group Inc'),
  (2,'Pivotal','Pivotal Cloud Foundry','Pivotal Cloud Foundry'),
  (3,'PivotalLabs','Pivotal Labs','Pivotal Labs');

--
-- Dumping data for table location
--
INSERT INTO location VALUES (1,2),(2,3),(3,4);

--
-- Dumping data for table person
--
INSERT INTO person VALUES
  (1,'test@luxoft.com','Admin','Admin','Employee','00000000','Admin',1,1),
  (2,'areyna@luxoft.com','Armando','Reyna','Employee','720-560-8971','Architect',1,1),
  (3,'msabir@luxoft.com','Muhammad','Sabir','Employee','631-983-9075','Architect',null,2),
  (4,'bstarkenberg@luxoft.com','Brad','Starkenberg','Employee','876-99-3427','Chief Architect',null,2),
  (5,'epereira@luxoft.com','Eric','Pereira','Employee','876-99-3427','Architect',null,2),
  (6,'kkrishna@luxoft.com','Kris','Krishna','Employee','876-99-3427','Architect',null,2),
  (7,'vnalitkin@insys.com','Vladimir','Nalitkin','Employee','876-99-3427','Architect',null,2),
  (8,'vmaevskiy@luxoft.com','Victor','Maevskiy','Employee','111-11-1111','Architect',null,2),
  (9,'dalambert@luxoft.com','David','Lambert','Employee','111-11-1111','Project Manager',null,2);

--
-- Dumping data for table user
--
INSERT INTO user VALUES (1,'admin','cb5af25a063f751372465c0f27ef9cada13ecd8d850564ebcab690d0c725ffddf6786887c444f016',1,1,1,1,1);

--
-- Dumping data for table user_authority
--
INSERT INTO user_authority VALUES (1,'ADMIN','admin');

--
-- Dumping data for table role
--
INSERT INTO role VALUES (1,'Developer');

--
-- Dumping data for table skill
--
INSERT INTO skill VALUES (1,'Java');

--
-- Dumping data for table role_skill
--
INSERT INTO role_skill VALUES (1,1);