-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: pidev24esprit-mysqldb-westeurope-shared.mysql.database.azure.com    Database: pidevdb
-- ------------------------------------------------------
-- Server version	8.0.36-cluster

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_viewed` bit(1) NOT NULL,
  `application_date` datetime(6) DEFAULT NULL,
  `offer_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKns78bpo6datm7fdogkisk7rrd` (`offer_id`),
  KEY `FKawte0mbtubellxed1dvpoxhdj` (`user_id`),
  CONSTRAINT `FKawte0mbtubellxed1dvpoxhdj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKns78bpo6datm7fdogkisk7rrd` FOREIGN KEY (`offer_id`) REFERENCES `offre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,_binary '\0','2024-07-07 16:00:31.347000',1,2),(2,_binary '\0','2024-07-07 16:25:58.067000',1,452),(3,_binary '\0','2024-07-07 16:34:16.581000',1,452),(4,_binary '\0','2024-07-08 20:05:03.285000',4,452),(5,_binary '\0','2024-07-08 20:07:33.666000',4,452),(6,_binary '\0','2024-07-08 21:00:59.867000',4,452),(7,_binary '\0','2024-07-08 21:05:58.229000',4,452);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `price` float NOT NULL,
  `state` bit(1) DEFAULT b'0',
  `category_id` bigint NOT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_updated` datetime(6) DEFAULT NULL,
  `author` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `nb_page` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkcc8h8k2diksb51v89pjmn9yy` (`category_id`),
  CONSTRAINT `FKkcc8h8k2diksb51v89pjmn9yy` FOREIGN KEY (`category_id`) REFERENCES `category_books` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (0,_binary '\0',1,'2024-06-30 12:06:01.622000',9,'2024-07-10 18:16:53.644000','Illo alias nostrum n','Enim et qui laudantihhh','d98dbb44-bc5f-4830-b2b4-0ee640c56feb_1_yPbP6pEqNbvIfZ4-aWqzyQ.jpg','Dolor fugiat dignis',200),(0,_binary '\0',22,'2024-06-30 14:27:01.643000',10,'2024-07-06 15:21:38.842000','Dolores molestiae qu','Consectetur sit sed ss','336c70c3-80c3-4244-a2ad-f706102ef46e_1_yPbP6pEqNbvIfZ4-aWqzyQ.jpg','Ea sit quis est lib',150),(0,_binary '\0',2,'2024-06-30 14:34:44.835000',11,'2024-07-06 15:21:45.037000','Cupidatat inventore ','Doloribus eum ab fug','4c6b5de2-90d1-4e70-80b8-eb12876e1da7_Mask group (3).png','Inventore consequatu',120),(0,_binary '\0',22,'2024-06-30 14:35:39.497000',12,'2024-07-10 00:26:38.643000','Cumque nemo error qu','Cum quis est ut qui ','d416bff6-791e-475c-a67b-ffe57c79a0d7_Mask group (5).png','Natus vel aute amet',75),(0,_binary '',21,'2024-06-30 14:37:01.080000',13,'2024-07-09 19:24:19.006000','Sit voluptate recusa','Non incididunt moles','4eb23d5f-14db-4743-8c7e-59ba1cba3ec6_Group 46.png','Doloremque et cumque',40),(0,_binary '',22,'2024-07-06 13:52:03.817000',17,'2024-07-06 15:22:04.836000','Facere est omnis in ','Necessitatibus lorem','bece1e16-b622-4e16-baee-b2c329935cd7_Group 42.png','Dolore nisi lorem qu',760),(0,_binary '\0',21,'2024-07-06 14:00:55.436000',18,'2024-07-11 14:45:12.460000','Labore a itaque qui ','Et laboriosam facil','e1bb4a2f-1147-4311-ab34-2a2be4a3e6cd_social-media-concept-composition 1.png','Eius eum voluptas om',233),(0,_binary '\0',21,'2024-07-06 15:25:56.648000',19,'2024-07-06 15:25:56.648000','Eu excepturi est sit','ttest ttest ttest ttest ttest ttest ttest ttest ttest ','024cb845-f312-441d-924f-75f1e88a2194_Group.png','Est inventore volupt',2);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_books`
--

DROP TABLE IF EXISTS `category_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_books` (
  `date_created` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_updated` datetime(6) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_books`
--

LOCK TABLES `category_books` WRITE;
/*!40000 ALTER TABLE `category_books` DISABLE KEYS */;
INSERT INTO `category_books` VALUES ('2024-06-25 21:23:44.324000',1,'2024-06-25 21:23:44.324000','romantic','romantic'),('2024-06-25 21:24:38.025000',2,'2024-06-25 21:24:38.025000','aventure','aventure'),('2024-06-25 21:26:02.175000',3,'2024-06-25 21:26:02.175000','religieux','religieux'),('2024-06-27 01:00:48.912000',20,'2024-06-27 01:00:48.912000','PFE BOOK','PFE BOOK'),('2024-06-27 01:31:11.814000',21,'2024-06-27 01:31:28.694000','Culturel','Culturel '),('2024-06-27 15:25:59.108000',22,'2024-06-27 15:25:59.108000','informatique','informatique');
/*!40000 ALTER TABLE `category_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_room`
--

DROP TABLE IF EXISTS `class_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_room` (
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `level` enum('FirstGrade','None','SecondGrade','ThirdGrade') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_room`
--

LOCK TABLES `class_room` WRITE;
/*!40000 ALTER TABLE `class_room` DISABLE KEYS */;
INSERT INTO `class_room` VALUES ('2024-06-29','2024-07-01',1,'1GL1','FirstGrade'),('2024-06-29','2024-06-29',2,'1GL2','FirstGrade'),('2024-06-29','2024-06-29',3,'1GL3','FirstGrade'),('2024-06-29','2024-06-29',4,'2GL1','SecondGrade'),('2024-06-29','2024-06-29',5,'3GL1','ThirdGrade');
/*!40000 ALTER TABLE `class_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_events`
--

DROP TABLE IF EXISTS `club_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_events` (
  `event_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` int NOT NULL,
  `event_date` datetime(6) NOT NULL,
  `event_type` enum('ARTS_ET_LOISIRS','CONFERENCE','CULTURELLE','FORMATION','HACKATHON','INTEGRATION','SPORTIF','TECHNOLOGIE_ET_INNOVATION') NOT NULL,
  `location` varchar(255) NOT NULL,
  `price` int DEFAULT NULL,
  `remaining_places` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `total_places` int NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `club_id` bigint NOT NULL,
  PRIMARY KEY (`event_id`),
  KEY `FKo1xhj84xwd7rc3tcksxtaqcae` (`club_id`),
  CONSTRAINT `FKo1xhj84xwd7rc3tcksxtaqcae` FOREIGN KEY (`club_id`) REFERENCES `clubs` (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_events`
--

LOCK TABLES `club_events` WRITE;
/*!40000 ALTER TABLE `club_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `club_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_members`
--

DROP TABLE IF EXISTS `club_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_members` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `cin` int DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `join_date` datetime(6) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `role` enum('MEMBER','TEAM_LEADER','VICE_PRESIDENT') NOT NULL,
  `club_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FKbwqgge4dgaaxukg2hytd9enhp` (`club_id`),
  KEY `FKrhejy2k7mkjakkwdckyps1jfo` (`user_id`),
  CONSTRAINT `FKbwqgge4dgaaxukg2hytd9enhp` FOREIGN KEY (`club_id`) REFERENCES `clubs` (`club_id`),
  CONSTRAINT `FKrhejy2k7mkjakkwdckyps1jfo` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_members`
--

LOCK TABLES `club_members` WRITE;
/*!40000 ALTER TABLE `club_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `club_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubs`
--

DROP TABLE IF EXISTS `clubs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clubs` (
  `club_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `logo_uri` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `total_members` int DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `president_id` bigint DEFAULT NULL,
  PRIMARY KEY (`club_id`),
  KEY `FK5fjmnq81wcqnkl5a4akm0oqjm` (`president_id`),
  CONSTRAINT `FK5fjmnq81wcqnkl5a4akm0oqjm` FOREIGN KEY (`president_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubs`
--

LOCK TABLES `clubs` WRITE;
/*!40000 ALTER TABLE `clubs` DISABLE KEYS */;
/*!40000 ALTER TABLE `clubs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `reclamation_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb70d2j2qj3t5yjm8jn9t5skh4` (`reclamation_id`),
  KEY `FKqm52p1v3o13hy268he0wcngr5` (`user_id`),
  CONSTRAINT `FKb70d2j2qj3t5yjm8jn9t5skh4` FOREIGN KEY (`reclamation_id`) REFERENCES `reclamation` (`id`),
  CONSTRAINT `FKqm52p1v3o13hy268he0wcngr5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'string','2024-07-02',1,1),(2,'Soyez le premier à tester notre application pour MAC et PC avec tests de streaming et navigation inclus.','2024-07-02',1,2),(3,'Il s’agit d’une séquence de mots latina cqui en cette position ne forment pas des phrases significatives, mais un texte de test pour remplir des éspaces qui ensuite seront occupés','2024-07-03',1,3),(4,'C\'est certainement le texte d’espace réservé le plus célèbre, même s\'il existe différentes versions qui se distinguent de l\'ordre dans lequel les mots latins sont répétés.','2024-07-03',1,3),(5,'hello test','2024-07-03',1,3),(6,'test test file ','2024-07-03',1,3),(7,'note progamation pas correcte','2024-07-05',2,1),(8,'test statistiques reclamations','2024-07-05',3,1),(9,'note php pas correcte','2024-07-06',4,4),(10,'note test et validation pas correcte','2024-07-06',5,4),(11,'this claims for test','2024-07-06',6,4),(12,'cette réclamation en cours de traitement','2024-07-06',4,3),(13,'hello this for test','2024-07-06',7,4),(14,'test','2024-07-06',8,4),(15,'test','2024-07-06',9,4),(16,'bonjour','2024-07-06',9,3);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_attachments`
--

DROP TABLE IF EXISTS `comment_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_attachments` (
  `my_row_id` bigint unsigned NOT NULL AUTO_INCREMENT /*!80023 INVISIBLE */,
  `comment_id` bigint NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`my_row_id`),
  KEY `FK7spt9miepoy084r83qklwurxd` (`comment_id`),
  CONSTRAINT `FK7spt9miepoy084r83qklwurxd` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_attachments`
--

LOCK TABLES `comment_attachments` WRITE;
/*!40000 ALTER TABLE `comment_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document` (
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `status` enum('Approved','Completed','Pending','Rejected') DEFAULT NULL,
  `type` enum('attendanceCertificate','diploma','studentCard','transcript') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm19xjdnh3l6aueyrpm1705t52` (`user_id`),
  CONSTRAINT `FKm19xjdnh3l6aueyrpm1705t52` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES ('2024-06-29','2024-07-07',1,1,NULL,'Completed','diploma'),('2024-07-02','2024-07-06',2,2,'test','Rejected','diploma'),('2024-07-06','2024-07-07',3,4,NULL,'Completed','studentCard'),('2024-07-07','2024-07-07',4,4,NULL,'Completed','studentCard'),('2024-07-07','2024-07-07',5,4,NULL,'Completed','studentCard'),('2024-07-07','2024-07-07',6,4,NULL,'Completed','attendanceCertificate'),('2024-07-07','2024-07-07',7,4,NULL,'Pending','transcript'),('2024-07-07','2024-07-07',8,4,NULL,'Pending','transcript'),('2024-07-07','2024-07-09',9,4,NULL,'Completed','studentCard'),('2024-07-07','2024-07-07',10,4,NULL,'Completed','studentCard'),('2024-07-07','2024-07-07',11,4,NULL,'Pending','studentCard'),('2024-07-07','2024-07-07',12,4,NULL,'Pending','diploma'),('2024-07-07','2024-07-07',13,4,NULL,'Pending','studentCard'),('2024-07-07','2024-07-07',14,4,NULL,'Pending','transcript'),('2024-07-07','2024-07-09',15,4,NULL,'Completed','transcript'),('2024-07-07','2024-07-09',16,4,NULL,'Completed','studentCard');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_registers`
--

DROP TABLE IF EXISTS `event_registers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_registers` (
  `register_id` bigint NOT NULL AUTO_INCREMENT,
  `feedback` varchar(255) DEFAULT NULL,
  `register_date` datetime(6) NOT NULL,
  `status` enum('APPROVED','PENDING','REJECTED') NOT NULL,
  `event_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`register_id`),
  KEY `FKc47fhc5uaameamid3sd8xp0qb` (`event_id`),
  KEY `FKgf966s1wce2lggdpufevovpx0` (`user_id`),
  CONSTRAINT `FKc47fhc5uaameamid3sd8xp0qb` FOREIGN KEY (`event_id`) REFERENCES `club_events` (`event_id`),
  CONSTRAINT `FKgf966s1wce2lggdpufevovpx0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_registers`
--

LOCK TABLES `event_registers` WRITE;
/*!40000 ALTER TABLE `event_registers` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_registers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbacks`
--

DROP TABLE IF EXISTS `feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedbacks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `note` double DEFAULT NULL,
  `book_id` bigint NOT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4sylw2xlbux3k80ehcv80q0n` (`book_id`),
  CONSTRAINT `FKt4sylw2xlbux3k80ehcv80q0n` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacks`
--

LOCK TABLES `feedbacks` WRITE;
/*!40000 ALTER TABLE `feedbacks` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offre`
--

DROP TABLE IF EXISTS `offre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offre` (
  `date_publication` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_user` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `type_offre` enum('EMPLOIE','STAGE') NOT NULL,
  `date_maj` datetime(6) DEFAULT NULL,
  `is_still_available` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7chy437j1a9lfqkkepoxfkkq7` (`id_user`),
  CONSTRAINT `FK7chy437j1a9lfqkkepoxfkkq7` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offre`
--

LOCK TABLES `offre` WRITE;
/*!40000 ALTER TABLE `offre` DISABLE KEYS */;
INSERT INTO `offre` VALUES ('2024-07-07 15:49:09.963000',1,2,'kkk','hhhh','EMPLOIE',NULL,_binary ''),('2024-07-07 15:50:10.431000',4,2,'fhghg','hhhfff','EMPLOIE',NULL,_binary ''),('2024-07-07 16:23:56.035000',6,3,'tssst','tesst','EMPLOIE',NULL,_binary '');
/*!40000 ALTER TABLE `offre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reclamation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `rate` varbinary(255) DEFAULT NULL,
  `status` enum('Approved','Completed','Pending','Rejected') DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `type` enum('absence','note') DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKliymw8hofjdysoseevjbf1ti1` (`user_id`),
  CONSTRAINT `FKliymw8hofjdysoseevjbf1ti1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reclamation`
--

LOCK TABLES `reclamation` WRITE;
/*!40000 ALTER TABLE `reclamation` DISABLE KEYS */;
INSERT INTO `reclamation` VALUES (1,'2024-07-02','string',NULL,'Completed','string','note','2024-07-03',1),(2,'2024-07-05','note progamation pas correcte',NULL,'Rejected','PROBLEME NOTE','note','2024-07-05',1),(3,'2024-07-05','test statistiques reclamations',NULL,'Pending','Probleme','note','2024-07-05',1),(4,'2024-07-06','note php pas correcte',NULL,'Approved','Probleme note','note','2024-07-06',4),(5,'2024-07-06','note test et validation pas correcte',NULL,'Pending','Probleme note test et validation','note','2024-07-06',4),(6,'2024-07-06','this claims for test',NULL,'Completed','Test create reclamation ','absence','2024-07-06',4),(7,'2024-07-06','hello this for test',NULL,'Pending','Test create reclamation 1','absence','2024-07-06',4),(8,'2024-07-06','test',NULL,'Completed','Test create reclamation ','absence','2024-07-06',4),(9,'2024-07-06','test',NULL,'Completed','Test create reclamation 1','note','2024-07-06',4);
/*!40000 ALTER TABLE `reclamation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reclamation_comments`
--

DROP TABLE IF EXISTS `reclamation_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reclamation_comments` (
  `my_row_id` bigint unsigned NOT NULL AUTO_INCREMENT /*!80023 INVISIBLE */,
  `reclamation_id` bigint NOT NULL,
  `comments_id` int NOT NULL,
  PRIMARY KEY (`my_row_id`),
  UNIQUE KEY `UKenuwsswxejwkav1641b4n38lh` (`comments_id`),
  KEY `FKpnhswlbmoh82dx07iwvixbade` (`reclamation_id`),
  CONSTRAINT `FKpnhswlbmoh82dx07iwvixbade` FOREIGN KEY (`reclamation_id`) REFERENCES `reclamation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reclamation_comments`
--

LOCK TABLES `reclamation_comments` WRITE;
/*!40000 ALTER TABLE `reclamation_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `reclamation_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_book`
--

DROP TABLE IF EXISTS `reservation_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_created` datetime(6) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `is_approved` bit(1) NOT NULL,
  `is_returned` bit(1) NOT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `reason` varchar(1000) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `book_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1io1nog3pnug4hrieyueymifl` (`book_id`),
  KEY `FK3i5bloghv902o7rvs1cit6aud` (`user_id`),
  CONSTRAINT `FK1io1nog3pnug4hrieyueymifl` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK3i5bloghv902o7rvs1cit6aud` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_book`
--

LOCK TABLES `reservation_book` WRITE;
/*!40000 ALTER TABLE `reservation_book` DISABLE KEYS */;
INSERT INTO `reservation_book` VALUES (3,'2024-07-07 12:38:26.515000','2024-07-20',_binary '',_binary '\0','2024-07-07 12:38:26.515000','test ','2024-07-07',17,1),(10,'2024-07-09 00:27:40.370000','2024-07-18',_binary '',_binary '\0','2024-07-09 19:24:18.920000','je veux ce livre en urg','2024-07-09',13,4),(26,'2024-07-11 14:41:53.227000','2024-07-28',_binary '',_binary '','2024-07-11 14:44:05.772000','test test test ','2024-07-11',18,5);
/*!40000 ALTER TABLE `reservation_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `date_of_birth` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `resume` bigint DEFAULT NULL,
  `education` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `experiance` varchar(255) NOT NULL,
  `langue` varchar(255) NOT NULL,
  `skils` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKn8ekymyv031bp9vuagen8i8mp` (`email`),
  UNIQUE KEY `UKfl4d61g6aoxdvl56d3ngtbwle` (`resume`),
  CONSTRAINT `FKqk2l6f86h9somvnxl6joujt9o` FOREIGN KEY (`resume`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_reservations`
--

DROP TABLE IF EXISTS `room_reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_reservations` (
  `reservation_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `number_of_attendees` int NOT NULL,
  `purpose` varchar(255) NOT NULL,
  `reserved_date` datetime(6) NOT NULL,
  `room_number` varchar(255) NOT NULL,
  `status` enum('APPROVED','PENDING','REJECTED') NOT NULL,
  `event_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`reservation_id`),
  UNIQUE KEY `UKnr0bqxhjyg3dvj7rynj2nq9u6` (`event_id`),
  KEY `FKf0pu3idh4qipr1gp1xrca75a1` (`user_id`),
  KEY `FKjqk4jqcga43n265y0pbxcknj5` (`id`),
  CONSTRAINT `FKf0pu3idh4qipr1gp1xrca75a1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKiwxr4s4lg3i3m4hdqnrn57ohy` FOREIGN KEY (`event_id`) REFERENCES `club_events` (`event_id`),
  CONSTRAINT `FKjqk4jqcga43n265y0pbxcknj5` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_reservations`
--

LOCK TABLES `room_reservations` WRITE;
/*!40000 ALTER TABLE `room_reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `coefficient` float NOT NULL,
  `created_date` date DEFAULT NULL,
  `total_hours` int NOT NULL,
  `updated_date` date DEFAULT NULL,
  `classroom_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtl03bfd9hnb8gq7bfm2o5fd1a` (`classroom_id`),
  CONSTRAINT `FKtl03bfd9hnb8gq7bfm2o5fd1a` FOREIGN KEY (`classroom_id`) REFERENCES `class_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1.5,'2024-06-29',40,NULL,1,1,'Data Structures'),(1.8,'2024-06-29',45,NULL,1,2,'Algorithms'),(1.6,'2024-06-29',30,NULL,1,3,'Operating Systems'),(1.7,'2024-06-29',35,NULL,1,4,'Database Systems'),(1.4,'2024-06-29',25,NULL,1,5,'Computer Networks'),(1.3,'2024-06-29',20,NULL,1,6,'Software Engineering'),(1.9,'2024-06-29',15,NULL,1,7,'Artificial Intelligence'),(2,'2024-06-29',50,NULL,1,8,'Machine Learning'),(1.5,'2024-06-29',25,NULL,1,9,'Computer Graphics'),(1.2,'2024-06-29',30,NULL,1,10,'Human-Computer Interaction');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_test`
--

DROP TABLE IF EXISTS `subject_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_test` (
  `coefficient` float NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subject_id` bigint DEFAULT NULL,
  `test_type` enum('CC','EXAM','None','TP') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9oagn37djwra1nwwn0nqdhi5h` (`subject_id`),
  CONSTRAINT `FK9oagn37djwra1nwwn0nqdhi5h` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_test`
--

LOCK TABLES `subject_test` WRITE;
/*!40000 ALTER TABLE `subject_test` DISABLE KEYS */;
INSERT INTO `subject_test` VALUES (0.5,'2024-06-29','2024-07-07',1,1,'EXAM'),(0.3,'2024-06-29','2024-07-07',2,1,'CC'),(0.7,'2024-06-29',NULL,3,2,'EXAM'),(0.2,'2024-06-29','2024-07-07',4,2,'TP'),(0.6,'2024-06-29',NULL,5,3,'EXAM'),(0.4,'2024-06-29',NULL,6,3,'CC'),(0.7,'2024-06-29',NULL,7,4,'EXAM'),(0.3,'2024-06-29',NULL,8,4,'TP'),(0.6,'2024-06-29',NULL,9,5,'EXAM'),(0.4,'2024-06-29',NULL,10,5,'CC'),(0.7,'2024-06-29',NULL,11,6,'EXAM'),(0.3,'2024-06-29',NULL,12,6,'TP'),(1,'2024-06-29',NULL,13,7,'EXAM'),(0.8,'2024-06-29',NULL,14,8,'EXAM'),(0.2,'2024-06-29',NULL,15,8,'CC'),(0.7,'2024-06-29',NULL,16,9,'EXAM'),(0.3,'2024-06-29',NULL,17,9,'TP'),(0.7,'2024-06-29',NULL,18,10,'EXAM'),(0.3,'2024-06-29',NULL,19,10,'CC'),(0.2,'2024-07-07','2024-07-07',24,1,'TP');
/*!40000 ALTER TABLE `subject_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_evalution`
--

DROP TABLE IF EXISTS `test_evalution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_evalution` (
  `created_date` date DEFAULT NULL,
  `note` float NOT NULL,
  `updated_date` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subject_test_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24pctdv0fn1mt4x0cjvr51lnm` (`subject_test_id`),
  KEY `FK5n170099ka8q2d6ujbe1it9rx` (`user_id`),
  CONSTRAINT `FK24pctdv0fn1mt4x0cjvr51lnm` FOREIGN KEY (`subject_test_id`) REFERENCES `subject_test` (`id`),
  CONSTRAINT `FK5n170099ka8q2d6ujbe1it9rx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_evalution`
--

LOCK TABLES `test_evalution` WRITE;
/*!40000 ALTER TABLE `test_evalution` DISABLE KEYS */;
INSERT INTO `test_evalution` VALUES ('2024-06-29',17,NULL,1,1,207),('2024-06-29',17,'2024-07-06',2,2,207),('2024-06-29',18,'2024-07-06',3,3,207),('2024-06-29',19,NULL,4,4,207),('2024-06-29',15,NULL,5,5,207),('2024-06-29',14,NULL,6,6,207),('2024-06-29',16,NULL,7,7,207),('2024-06-29',17,NULL,8,8,207),('2024-06-29',13,NULL,9,9,207),('2024-06-29',16,NULL,10,10,207),('2024-06-29',14,NULL,11,11,207),('2024-06-29',17,NULL,12,12,207),('2024-06-29',20,NULL,13,13,207),('2024-06-29',18,NULL,14,14,207),('2024-06-29',15,NULL,15,15,207),('2024-06-29',17,NULL,16,16,207),('2024-06-29',16,NULL,17,17,207),('2024-06-29',14,NULL,18,18,207),('2024-06-29',15,NULL,19,19,207),('2024-07-06',20,NULL,20,4,210),('2024-07-06',12,NULL,21,7,210),('2024-07-07',20,NULL,22,24,207);
/*!40000 ALTER TABLE `test_evalution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `classroom_id` bigint DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `user` bigint DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile_picuri` varchar(255) DEFAULT NULL,
  `resumeuri` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','ENTERPRISE','PROFESSOR','STUDENT','LIBRARY_MANAGER') NOT NULL,
  `login_attempts` int NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_option` enum('BI','CLOUD','DEVOPS','GL','MOBILE','TWIN') DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKdmw0k15utpdl196fcup0h4xao` (`user`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKf9uquwyrc2sg4q7y0t1ols40d` (`classroom_id`),
  CONSTRAINT `FKf9uquwyrc2sg4q7y0t1ols40d` FOREIGN KEY (`classroom_id`) REFERENCES `class_room` (`id`),
  CONSTRAINT `FKgepl6vupbwfl153k006e9oudd` FOREIGN KEY (`user`) REFERENCES `resume` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (NULL,NULL,1,'2024-07-10 16:05:28.598000',NULL,'med','baha','$2a$10$xD4zB9DRkDtDfZz26bcqNeEu3EYGWFl0tcPeP7U5i8afmOWP0i0Oy','208453-P03GET-24.jpg',NULL,'STUDENT',0,'mb06-2024','DEVOPS','bousnina.baha14@gmail.com'),(NULL,'2024-06-23 19:55:02.396000',2,'2024-07-07 16:00:27.739000',NULL,'dhou','baha','$2a$10$2kfuqvJwPeJF4nZVEMdJve18LPC3P7XcMhlX5EiUQNeGxSWPsgGrO','Wael_Cv.pdf','string','STUDENT',0,'db06-2024','GL',NULL),(NULL,NULL,3,'2024-06-26 17:47:06.330000',NULL,'test','test','$2a$10$NfokT2S9dk4Hd1SWJSoaTe2avqkGAe0x1ivncgMjFWDMkzumIHmcK','string','string','ADMIN',0,'testtest06-2024',NULL,NULL),(NULL,'2024-06-23 19:58:20.689000',4,NULL,NULL,'imen','tes','$2a$10$HvPcFGigL2ssrlbPn8xrfeGullSlg7aQqrth4Zj4ZPpHT5Le0mYn2','string','string','STUDENT',0,'it06-2024','BI','imen.zhani@esprit.tn'),(NULL,'2024-06-23 19:58:31.218000',5,NULL,NULL,'imen','shrif','$2a$10$3qfGriZj4Zt01JJWJzIyEO8vojKIhdkehzKV/lOlXHD5fOmKj8h5K','string','string','STUDENT',0,'is06-2024','BI','saber10ouledsalem@gmail.com'),(NULL,'2024-06-23 19:59:23.635000',6,NULL,NULL,'abir','shrif','$2a$10$lTYcQWNi7jYEBm5nhyHaEOrjrO/KeEEt.LcYD0DesLeGgfZM9kotu','string','string','STUDENT',0,'as06-2024','TWIN',NULL),(NULL,'2024-06-23 20:04:57.487000',52,NULL,NULL,'abir','mhiri','$2a$10$NLkXcgSf0nghAkTD6NaKN.BcBw1qwyhweN4URqwvki2T6YYg5Xmfm','string','string','STUDENT',0,'am06-2024','TWIN',NULL),(NULL,'2024-06-25 18:11:38.012000',102,'2024-06-25 19:35:05.030000',NULL,'haifa','dridi','$2a$10$RVF4j0D/DchFCIkCyYbQXOioCYVJZSX5xlAsHreyBi8JZXY6C/T6W','string','string','ADMIN',0,NULL,NULL,NULL),(NULL,'2024-06-29 18:08:41.380000',206,'2024-06-29 18:04:49.069000',NULL,'dhou','sn','$2a$10$i8EUhbFGSSvxMT9A9V8h8ulKWkYrlPwGAUcBxbCDYxJFwsJR2Yld.','string','string','ADMIN',0,'ds06-2024','DEVOPS',NULL),(1,'2024-06-29 18:17:07.669000',207,'2023-05-15 10:30:00.000000',NULL,'John','Doe','$2a$10$923gg.dkStaQ6B0h5v.E5OKkTF3Gj3aB381caqWOmTuMYigWK3qJO','https://example.com/profilepic1.jpg','https://example.com/resume1.pdf','STUDENT',0,'JD06-2024','DEVOPS',NULL),(1,'2024-06-29 18:17:29.728000',210,'2023-08-05 12:15:00.000000',NULL,'Emma','Johnson','$2a$10$R81qoUwZBKN443S149B/geuxdwMKirQ3c0rjpgSdEGd6HCTZVK46S','https://example.com/profilepic4.jpg','https://example.com/resume4.pdf','STUDENT',0,'EJ06-2024','BI',NULL),(1,'2024-06-29 18:17:37.160000',211,'2023-09-01 14:45:00.000000',NULL,'Michael','Wilson','$2a$10$7HLBpAPS.XgAKIk9iUpo2OfiK/2CP9NmlwVCGCrVEaCfHJt/L4Pyu','https://example.com/profilepic5.jpg','https://example.com/resume5.pdf','STUDENT',0,'MW06-2024','TWIN',NULL),(1,'2024-06-29 18:17:43.614000',212,'2023-10-12 16:30:00.000000',NULL,'Sophia','Martinez','$2a$10$kfYOo4x8uxqPZ9u5fYGhO.XVWyXly23g6K5d4bKl0L4XkBFlFSEwe','https://example.com/profilepic6.jpg','https://example.com/resume6.pdf','STUDENT',0,'SM06-2024','MOBILE',NULL),(1,'2024-06-29 19:36:47.617000',213,'2023-11-20 09:00:00.000000',NULL,'Daniel','Garcia','$2a$10$12EfkPl5iFoELa0yvNiStusKlGcmrUGPlxfcV9cM0z9sX.swKVpsW','https://example.com/profilepic7.jpg','https://example.com/resume7.pdf','STUDENT',0,'DG06-2024','DEVOPS',NULL),(1,'2024-06-29 19:36:56.880000',214,'2023-12-05 11:45:00.000000',NULL,'Olivia','Lopez','$2a$10$Fis.jfG/63EEFG/riNKin.2qnoCIBnwziCtOHAt4iSDMBkifxxfyK','https://example.com/profilepic8.jpg','https://example.com/resume8.pdf','STUDENT',0,'OL06-2024','GL',NULL),(1,'2024-06-29 19:37:03.756000',215,'2024-01-10 13:20:00.000000',NULL,'James','Young','$2a$10$CZRiNr.k8PeXMFk0LRYYDeVhK/S5QPa.zfRRA/H1OxDiqza1bBYeO','https://example.com/profilepic9.jpg','https://example.com/resume9.pdf','STUDENT',0,'JY06-2024','CLOUD',NULL),(1,'2024-06-29 19:37:13.026000',216,'2024-02-15 14:00:00.000000',NULL,'Emily','Hernandez','$2a$10$Rgg0FGFPXlUBhqVb.LqEf.y/nfQtfpsYIDSLTEN/YSj9ytwSndR8m','https://example.com/profilepic10.jpg','https://example.com/resume10.pdf','STUDENT',0,'EH06-2024','BI',NULL),(1,'2024-06-29 19:37:19.680000',217,'2024-03-20 09:30:00.000000',NULL,'William','King','$2a$10$VlU8uwA17LHobKnJ5NvZ/.THavxq8ow38/yitQmjiHg3EWpL6m/hq','https://example.com/profilepic11.jpg','https://example.com/resume11.pdf','STUDENT',0,'WK06-2024','TWIN',NULL),(NULL,'2024-07-03 19:06:19.595000',302,NULL,NULL,'zhani','imen','$2a$10$pPX2Htmi1mej2vU2klr2DuJPDHa/3RUODm.h8.sTQMyd5YOlWmFyS',NULL,NULL,'STUDENT',0,'zi07-2024',NULL,NULL),(NULL,'2024-07-04 17:49:39.190000',352,NULL,NULL,'malek','jlassi','string',NULL,NULL,'STUDENT',0,'mj07-2024',NULL,NULL),(NULL,'2024-07-07 13:25:00.732000',402,NULL,NULL,'imen','trabelsi','string',NULL,NULL,'STUDENT',0,'it07-2024','TWIN','imen.trabelsi@gmail.com'),(NULL,'2024-07-07 14:41:47.982000',452,'2024-07-07 16:34:09.886000',NULL,'Wael','boubaker','$2a$10$5bajbDWCeA4Ux89wZwDUsOshHDBuMlYMimTl.ACDO0oQnaYV6f8X.','Wael_Cv.pdf','Wael_Cv.pdf','STUDENT',0,'Wb07-2024',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_seq`
--

DROP TABLE IF EXISTS `users_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_seq` (
  `my_row_id` bigint unsigned NOT NULL AUTO_INCREMENT /*!80023 INVISIBLE */,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`my_row_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_seq`
--

LOCK TABLES `users_seq` WRITE;
/*!40000 ALTER TABLE `users_seq` DISABLE KEYS */;
INSERT INTO `users_seq` (`my_row_id`, `next_val`) VALUES (1,551);
/*!40000 ALTER TABLE `users_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-11 20:05:10
