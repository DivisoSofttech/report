-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: reportdb
-- ------------------------------------------------------
-- Server version	5.7.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2019-12-18 15:14:23',1,'EXECUTED','7:afe878fb30103f4b02982621400de20e','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20190808044727-1','jhipster','config/liquibase/changelog/20190808044727_added_entity_OrderMaster.xml','2019-12-18 15:14:23',2,'EXECUTED','7:9fbd01d1ada320cb86ce53892dc69438','createTable tableName=order_master; dropDefaultValue columnName=expected_delivery, tableName=order_master; dropDefaultValue columnName=order_place_at, tableName=order_master; dropDefaultValue columnName=order_accepted_at, tableName=order_master; d...','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20190808044728-1','jhipster','config/liquibase/changelog/20190808044728_added_entity_OrderLine.xml','2019-12-18 15:14:24',3,'EXECUTED','7:19eb4814f7509fb62eb4c201b470b67a','createTable tableName=order_line','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20190819104623-1','jhipster','config/liquibase/changelog/20190819104623_added_entity_AuxItem.xml','2019-12-18 15:14:24',4,'EXECUTED','7:00078b1e3b408537b2f6681dcecc7de3','createTable tableName=aux_item','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20190819104624-1','jhipster','config/liquibase/changelog/20190819104624_added_entity_ComboItem.xml','2019-12-18 15:14:24',5,'EXECUTED','7:47878e0adf533665f4ae5e86ef6fac59','createTable tableName=combo_item','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20191102042321-1','jhipster','config/liquibase/changelog/20191102042321_added_entity_Sale.xml','2019-12-18 15:14:25',6,'EXECUTED','7:dde727d01bbc136e0b312142dc1bb44c','createTable tableName=sale; dropDefaultValue columnName=jhi_date, tableName=sale','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20191102042322-1','jhipster','config/liquibase/changelog/20191102042322_added_entity_TicketLine.xml','2019-12-18 15:14:25',7,'EXECUTED','7:026dabf78431eacbe557e86fcd74b787','createTable tableName=ticket_line','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20191216062307-1','jhipster','config/liquibase/changelog/20191216062307_added_entity_OfferLine.xml','2019-12-18 15:14:25',8,'EXECUTED','7:2690c6c6694e7c82e51e0308f9a95aa2','createTable tableName=offer_line','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20190808044728-2','jhipster','config/liquibase/changelog/20190808044728_added_entity_constraints_OrderLine.xml','2019-12-18 15:14:26',9,'EXECUTED','7:e42cb9fe6b28b68cf1cc920b132b4cd5','addForeignKeyConstraint baseTableName=order_line, constraintName=fk_order_line_order_master_id, referencedTableName=order_master','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20190819104623-2','jhipster','config/liquibase/changelog/20190819104623_added_entity_constraints_AuxItem.xml','2019-12-18 15:14:27',10,'EXECUTED','7:1a62a1500ee7776ad07eb8f93785ae1d','addForeignKeyConstraint baseTableName=aux_item, constraintName=fk_aux_item_order_line_id, referencedTableName=order_line','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20190819104624-2','jhipster','config/liquibase/changelog/20190819104624_added_entity_constraints_ComboItem.xml','2019-12-18 15:14:27',11,'EXECUTED','7:6f0facfc3fe895eb86d71c9b46d6b188','addForeignKeyConstraint baseTableName=combo_item, constraintName=fk_combo_item_order_line_id, referencedTableName=order_line','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20191102042322-2','jhipster','config/liquibase/changelog/20191102042322_added_entity_constraints_TicketLine.xml','2019-12-18 15:14:28',12,'EXECUTED','7:7a86d7a4470a765d3fece97558d09f07','addForeignKeyConstraint baseTableName=ticket_line, constraintName=fk_ticket_line_sale_id, referencedTableName=sale','',NULL,'3.5.4',NULL,NULL,'6662258431'),('20191216062307-2','jhipster','config/liquibase/changelog/20191216062307_added_entity_constraints_OfferLine.xml','2019-12-18 15:14:29',13,'EXECUTED','7:a9a83c3bce682302bcde346c30585f61','addForeignKeyConstraint baseTableName=offer_line, constraintName=fk_offer_line_order_master_id, referencedTableName=order_master','',NULL,'3.5.4',NULL,NULL,'6662258431');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aux_item`
--

DROP TABLE IF EXISTS `aux_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aux_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `aux_item` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `order_line_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_aux_item_order_line_id` (`order_line_id`),
  CONSTRAINT `fk_aux_item_order_line_id` FOREIGN KEY (`order_line_id`) REFERENCES `order_line` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aux_item`
--

LOCK TABLES `aux_item` WRITE;
/*!40000 ALTER TABLE `aux_item` DISABLE KEYS */;
INSERT INTO `aux_item` VALUES (1,'salad',1,5,1),(2,'french fries',1,10.22,2);
/*!40000 ALTER TABLE `aux_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo_item`
--

DROP TABLE IF EXISTS `combo_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combo_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `combo_item` varchar(255) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `order_line_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_combo_item_order_line_id` (`order_line_id`),
  CONSTRAINT `fk_combo_item_order_line_id` FOREIGN KEY (`order_line_id`) REFERENCES `order_line` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo_item`
--

LOCK TABLES `combo_item` WRITE;
/*!40000 ALTER TABLE `combo_item` DISABLE KEYS */;
INSERT INTO `combo_item` VALUES (1,'pepsi',1,1),(2,'7up',2,2);
/*!40000 ALTER TABLE `combo_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'test','2019-12-18 04:20:22','AUTHENTICATION_SUCCESS'),(2,'guest','2019-12-18 04:51:54','AUTHENTICATION_SUCCESS'),(3,'guest','2019-12-18 04:55:40','AUTHENTICATION_SUCCESS'),(4,'guest','2019-12-18 05:54:37','AUTHENTICATION_SUCCESS'),(5,'guest','2019-12-19 01:17:00','AUTHENTICATION_SUCCESS'),(6,'guest','2019-12-19 01:19:43','AUTHENTICATION_SUCCESS'),(7,'guest','2019-12-19 01:23:43','AUTHENTICATION_SUCCESS'),(8,'guest','2019-12-20 05:23:05','AUTHENTICATION_SUCCESS'),(9,'guest','2019-12-20 05:24:21','AUTHENTICATION_SUCCESS'),(10,'guest','2019-12-20 06:21:27','AUTHENTICATION_SUCCESS'),(11,'guest','2019-12-20 06:22:14','AUTHENTICATION_SUCCESS'),(12,'test','2019-12-22 23:46:30','AUTHENTICATION_SUCCESS'),(13,'test','2019-12-23 00:37:23','AUTHENTICATION_SUCCESS'),(14,'test','2019-12-23 01:45:13','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(2,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(3,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(4,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(5,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(6,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(7,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(8,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(9,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(10,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(11,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(12,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(13,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(14,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>');
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` varchar(100) NOT NULL,
  `login` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(191) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` varchar(100) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer_line`
--

DROP TABLE IF EXISTS `offer_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `offer_ref` varchar(255) DEFAULT NULL,
  `discount_amount` double DEFAULT NULL,
  `order_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_offer_line_order_master_id` (`order_master_id`),
  CONSTRAINT `fk_offer_line_order_master_id` FOREIGN KEY (`order_master_id`) REFERENCES `order_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer_line`
--

LOCK TABLES `offer_line` WRITE;
/*!40000 ALTER TABLE `offer_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `offer_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `order_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_line_order_master_id` (`order_master_id`),
  CONSTRAINT `fk_order_line_order_master_id` FOREIGN KEY (`order_master_id`) REFERENCES `order_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
INSERT INTO `order_line` VALUES (1,'Basmati',2,4,1),(2,'Egg friedrice',2,6,1),(3,'Basmati',4,8,2),(4,'Egg friedrice',5,15,2),(5,'Chicken Biriyani',1,10.95,3),(6,'Chicken Biriyani',5,54.75,4),(7,'Chicken Biriyani',4,43.8,5),(8,'Chicken Biriyani',4,43.8,6),(9,'Chicken Biriyani',4,43.8,7),(10,'Chicken Kebab',1,6.1,8);
/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_master`
--

DROP TABLE IF EXISTS `order_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_idpcode` varchar(255) DEFAULT NULL,
  `store_name` varchar(255) DEFAULT NULL,
  `store_phone` bigint(20) DEFAULT NULL,
  `storelocation_name` varchar(255) DEFAULT NULL,
  `method_of_order` varchar(255) DEFAULT NULL,
  `expected_delivery` datetime,
  `order_number` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  `order_discount_amount` double DEFAULT NULL,
  `delivery_charge` double DEFAULT NULL,
  `service_charge` double DEFAULT NULL,
  `total_due` double DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `house_no_or_building_name` varchar(255) DEFAULT NULL,
  `road_name_area_or_street` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `landmark` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `alternate_phone` bigint(20) DEFAULT NULL,
  `address_type` varchar(255) DEFAULT NULL,
  `order_from_customer` bigint(20) DEFAULT NULL,
  `customer_order` bigint(20) DEFAULT NULL,
  `order_place_at` datetime,
  `order_accepted_at` datetime,
  `allergy_note` varchar(255) DEFAULT NULL,
  `pre_order_date` datetime,
  `email` varchar(255) DEFAULT NULL,
  `payment_ref` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_master`
--

LOCK TABLES `order_master` WRITE;
/*!40000 ALTER TABLE `order_master` DISABLE KEYS */;
INSERT INTO `order_master` VALUES (1,NULL,'Spice India',80942257,'Dublin','COLLECTION','2019-11-16 17:46:31','FEXP-1','put some more cheese and extra toppings above pizza',9.8,0.98,0,NULL,30.26,'NULL','FP-2','Neeraja','679302','LXI Technologies Pathirippala','Gandhi Seva Sadan','NULL','kerala','palakkad','null',9809203816,NULL,NULL,1,1,'2019-11-16 17:06:31','2019-11-16 17:11:31',NULL,NULL,'abilash.s@lxisoft.com','NULL','ORDER NOT PAID'),(2,NULL,'Spice India',80942257,'Dublin','DELIVERY','2019-11-16 17:47:48','FEXP-2','NULL',22.55,0,0,NULL,23,'NULL','FP-2','Abilash','679302','LXI Technologies Pathirippala','Gandhi Seva Sadan','Pathirippala','Kerala','Palakkad',NULL,9809203816,NULL,NULL,2,2,'2019-11-16 17:07:48','2019-11-16 17:12:48',NULL,'1970-01-19 05:12:04','abilash.s@lxisoft.com','NULL','ORDER PAID'),(3,NULL,'Spiceindia',51561651654,'pathiripala','DELIVERY','2019-12-20 11:54:20','FEXP-25',NULL,10.95,0,0,NULL,10.95,NULL,'FP-8',NULL,'787878','LxiSoft Gandhi Seve Sadan',NULL,'Pathiripalla',NULL,'Kerala',NULL,8129528847,NULL,NULL,21,21,'2019-12-20 11:14:20','2019-12-20 11:19:20',NULL,NULL,'test@gmail.com',NULL,'ORDER NOT PAID'),(4,NULL,'Spiceindia',51561651654,'pathiripala','DELIVERY','2019-12-20 12:03:56','FEXP-26',NULL,54.75,0,0,NULL,54.75,NULL,'FP-8',NULL,'787878','LxiSoft Gandhi Seve Sadan',NULL,'Pathiripalla',NULL,'Kerala',NULL,8129528847,NULL,NULL,22,22,'2019-12-20 11:23:56','2019-12-20 11:28:56',NULL,NULL,'test@gmail.com',NULL,'ORDER NOT PAID'),(5,NULL,'Spiceindia',51561651654,'pathiripala','DELIVERY','2019-12-20 12:11:08','FEXP-27',NULL,43.8,0,0,NULL,43.8,NULL,'FP-8',NULL,'787878','LxiSoft Gandhi Seve Sadan',NULL,'Pathiripalla',NULL,'Kerala',NULL,8129528847,NULL,NULL,23,23,'2019-12-20 11:31:08','2019-12-20 11:36:08',NULL,NULL,'test@gmail.com',NULL,'ORDER NOT PAID'),(6,NULL,'Spiceindia',51561651654,'pathiripala','DELIVERY','2019-12-20 12:19:35','FEXP-28',NULL,43.8,0,0,NULL,43.8,NULL,'FP-8',NULL,'787878','LxiSoft Gandhi Seve Sadan',NULL,'Pathiripalla',NULL,'Kerala',NULL,8129528847,NULL,NULL,24,24,'2019-12-20 11:39:35','2019-12-20 11:44:35',NULL,NULL,'test@gmail.com',NULL,'ORDER NOT PAID'),(7,NULL,'Spiceindia',51561651654,'pathiripala','COLLECTION','2019-12-20 12:22:10','FEXP-29',NULL,43.8,0,0,NULL,43.8,NULL,'FP-8',NULL,'787878','LxiSoft Gandhi Seve Sadan',NULL,'Pathiripalla',NULL,'Kerala',NULL,8129528847,NULL,NULL,25,25,'2019-12-20 11:42:10','2019-12-20 11:47:10',NULL,'2017-03-13 07:29:00','test@gmail.com',NULL,'ORDER NOT PAID'),(8,NULL,'Sofias',546161646,'palakkad','DELIVERY','2019-12-20 12:44:20','FEXP-34',NULL,6.1,0,0,NULL,6.1,NULL,'FP-8',NULL,'787878','LxiSoft Gandhi Seve Sadan',NULL,'Pathiripalla',NULL,'Kerala',NULL,8129528847,NULL,NULL,1,30,'2019-12-20 12:04:20','2019-12-20 12:09:20',NULL,NULL,'test@gmail.com',NULL,'ORDER NOT PAID');
/*!40000 ALTER TABLE `order_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(255) DEFAULT NULL,
  `store_phone` bigint(20) DEFAULT NULL,
  `storeidpcode` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `jhi_date` datetime,
  `grand_total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_line`
--

DROP TABLE IF EXISTS `ticket_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `sale_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ticket_line_sale_id` (`sale_id`),
  CONSTRAINT `fk_ticket_line_sale_id` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_line`
--

LOCK TABLES `ticket_line` WRITE;
/*!40000 ALTER TABLE `ticket_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_line` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-24  9:54:07
