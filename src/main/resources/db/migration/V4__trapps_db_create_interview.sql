--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9fngeswvwow8hbw483eflv3l5` (`person_id`),
  CONSTRAINT `FK9fngeswvwow8hbw483eflv3l5` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `feedback_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interview`
--

DROP TABLE IF EXISTS `interview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbcp3sesuekgk12qhkyyobv3bi` (`person_id`),
  KEY `FKsex70unymye703aw5hl004in4` (`role_id`),
  CONSTRAINT `FKbcp3sesuekgk12qhkyyobv3bi` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKsex70unymye703aw5hl004in4` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `interview_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `interview_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `quality` varchar(255) DEFAULT NULL,
  `question` varchar(255) NOT NULL,
  `interview_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `question_interview_id` (`interview_id`),
  CONSTRAINT `question_interview_id` FOREIGN KEY (`interview_id`) REFERENCES `interview` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `quality` varchar(255) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8frr4bcabmmeyyu60qt7iiblo` (`question_id`),
  CONSTRAINT `FK8frr4bcabmmeyyu60qt7iiblo` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interview_answers`
--

DROP TABLE IF EXISTS `interview_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interview_answers` (
  `interview_id` bigint(20) NOT NULL,
  `answers_id` bigint(20) NOT NULL,
  PRIMARY KEY (`interview_id`,`answers_id`),
  UNIQUE KEY `UK_d9fnk2bnsbpoegoggtrd5ak2i` (`answers_id`),
  CONSTRAINT `FKimpwqj3mh6ebg1d52e9qm3srf` FOREIGN KEY (`interview_id`) REFERENCES `interview` (`id`),
  CONSTRAINT `FKra7biq7mbt3bavl6dpu16r8i9` FOREIGN KEY (`answers_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interview_feedbacks`
--

DROP TABLE IF EXISTS `interview_feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interview_feedbacks` (
  `interview_id` bigint(20) NOT NULL,
  `feedbacks_id` bigint(20) NOT NULL,
  PRIMARY KEY (`interview_id`,`feedbacks_id`),
  UNIQUE KEY `UK_qw3u7k1rmkxxfwmuisv52phh6` (`feedbacks_id`),
  CONSTRAINT `FKocj3gyf7k15ejabb5c5u3wqoy` FOREIGN KEY (`feedbacks_id`) REFERENCES `feedback` (`id`),
  CONSTRAINT `FKqna6jh2i160bb4cdsqgvq4677` FOREIGN KEY (`interview_id`) REFERENCES `interview` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interview_interviewers`
--

DROP TABLE IF EXISTS `interview_interviewers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interview_interviewers` (
  `interview_id` bigint(20) NOT NULL,
  `interviewers_id` bigint(20) NOT NULL,
  PRIMARY KEY (`interview_id`,`interviewers_id`),
  KEY `FKkqhmca3gp89yq9en02lk5g86s` (`interviewers_id`),
  CONSTRAINT `FKkqhmca3gp89yq9en02lk5g86s` FOREIGN KEY (`interviewers_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKqkw494hubriemxli1ly58fcsr` FOREIGN KEY (`interview_id`) REFERENCES `interview` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interview_questions`
--

DROP TABLE IF EXISTS `interview_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interview_questions` (
  `interview_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`interview_id`,`questions_id`),
  KEY `interview_questions_questions_id` (`questions_id`),
  CONSTRAINT `interview_questions_interview_id` FOREIGN KEY (`interview_id`) REFERENCES `interview` (`id`),
  CONSTRAINT `interview_questions_questions_id` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interview_template`
--

DROP TABLE IF EXISTS `interview_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interview_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmwo6324kqaxrhqyt9p3in5695` (`role_id`),
  CONSTRAINT `FKmwo6324kqaxrhqyt9p3in5695` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interview_template_questions`
--

DROP TABLE IF EXISTS `interview_template_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interview_template_questions` (
  `interview_template_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`interview_template_id`,`questions_id`),
  UNIQUE KEY `UK_7xggysl71jk0lt2tvtx3koiei` (`questions_id`),
  CONSTRAINT `FKbgj6ivbw8y32mlfcaeovrxf9l` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FKbhor99rk8o3u3ltp1nxcjh07r` FOREIGN KEY (`interview_template_id`) REFERENCES `interview_template` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;