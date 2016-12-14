CREATE SCHEMA `journals` DEFAULT CHARACTER SET utf8 ;

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `journal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `journal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_in_queue` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) NOT NULL,
  `publish_date` datetime NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `publisher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8yg6hsabxw2lgqjkbkij55qqx` (`category_id`),
  KEY `FK_c7picib39dl7kxro2349cnpn9` (`publisher_id`),
  CONSTRAINT `FK_8yg6hsabxw2lgqjkbkij55qqx` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_c7picib39dl7kxro2349cnpn9` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ml1xc0aovqkkm2p1lssgjkfas` (`user_id`),
  CONSTRAINT `FK_ml1xc0aovqkkm2p1lssgjkfas` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5n1jngces3c64v9dapehv1mae` (`category_id`),
  KEY `FK_tq3cq3gmsss8jjyb2l5sb1o6k` (`user_id`),
  CONSTRAINT `FK_5n1jngces3c64v9dapehv1mae` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_tq3cq3gmsss8jjyb2l5sb1o6k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `login_name` varchar(255) NOT NULL,
  `mail_address` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `event_queue`;
CREATE TABLE `event_queue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `day_turn` varchar(255) DEFAULT NULL,
  `event_type` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `journal_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9i67w6a2buxhu34q484d3bnwh` (`journal_id`),
  CONSTRAINT `FK_9i67w6a2buxhu34q484d3bnwh` FOREIGN KEY (`journal_id`) REFERENCES `journal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `daily_job_log`;
CREATE TABLE `daily_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` bigint(20) NOT NULL,
  `event` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id_idx` (`event`),
  KEY `daily_user_fk_idx` (`user`),
  CONSTRAINT `daily_event_fk` FOREIGN KEY (`event`) REFERENCES `event_queue` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `daily_user_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `immediately_job_log`;
CREATE TABLE `immediately_job_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id_idx` (`event`),
  KEY `user_id_idx` (`user`),
  CONSTRAINT `event_id` FOREIGN KEY (`event`) REFERENCES `event_queue` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
