-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: vanstopdb
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Temporary view structure for view `allview`
--

DROP TABLE IF EXISTS `allview`;
/*!50001 DROP VIEW IF EXISTS `allview`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `allview` AS SELECT 
 1 AS `name`,
 1 AS `lat`,
 1 AS `long`,
 1 AS `nation`,
 1 AS `eu`,
 1 AS `overnight_friendly`,
 1 AS `tag`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `nations`
--

DROP TABLE IF EXISTS `nations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nations` (
  `nations_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `eu` tinyint unsigned DEFAULT '0',
  `overnight_friendly` tinyint unsigned DEFAULT '0',
  PRIMARY KEY (`nations_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nations`
--

LOCK TABLES `nations` WRITE;
/*!40000 ALTER TABLE `nations` DISABLE KEYS */;
INSERT INTO `nations` VALUES (1,'United Kingdom',0,0),(2,'Sweden',1,1),(3,'Ukraine',0,0),(4,'Germany',1,1),(5,'France',1,0),(6,'Austria',1,0),(7,'Belgium',1,1),(8,'Italy',1,0),(9,'Netherlands',1,0);
/*!40000 ALTER TABLE `nations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stops`
--

DROP TABLE IF EXISTS `stops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stops` (
  `stops_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lat` varchar(45) NOT NULL,
  `long` varchar(45) NOT NULL,
  `nations_id` int NOT NULL,
  PRIMARY KEY (`stops_id`,`nations_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_stops_nations1_idx` (`nations_id`),
  CONSTRAINT `fk_stops_nations1` FOREIGN KEY (`nations_id`) REFERENCES `nations` (`nations_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stops`
--

LOCK TABLES `stops` WRITE;
/*!40000 ALTER TABLE `stops` DISABLE KEYS */;
INSERT INTO `stops` VALUES (1,'moooob','87.87878','98.98988',5),(2,'rutrum, justo.','16.43234','-26.85429',2),(3,'metus sit','70.86210','170.50368',3),(4,'elefant','78.78489','65.84519',5),(7,'apapa','45.87457','45.87854',3),(9,'jehbha','-132.37888888','45.23987',5),(10,'balopa','16.43234','-26.85429',2),(11,'ekat','16.43234','-26.85429',2),(13,'ewewewewe','16.43234','-26.85429',2),(15,'wewe','16.43234','-26.85429',2);
/*!40000 ALTER TABLE `stops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stops_tags`
--

DROP TABLE IF EXISTS `stops_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stops_tags` (
  `stops_id` int NOT NULL,
  `tags_id` int NOT NULL,
  PRIMARY KEY (`stops_id`,`tags_id`),
  KEY `fk_stops_tags_stops_idx` (`stops_id`),
  KEY `fk_stops_tags_tags1_idx` (`tags_id`),
  CONSTRAINT `fk_stops_tags_stops` FOREIGN KEY (`stops_id`) REFERENCES `stops` (`stops_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stops_tags_tags1` FOREIGN KEY (`tags_id`) REFERENCES `tags` (`tags_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stops_tags`
--

LOCK TABLES `stops_tags` WRITE;
/*!40000 ALTER TABLE `stops_tags` DISABLE KEYS */;
INSERT INTO `stops_tags` VALUES (1,5),(1,10),(2,1),(2,5),(2,9),(2,10),(3,5),(3,7),(3,8),(4,1),(7,1),(7,5),(7,7),(9,5),(9,10),(10,4),(10,7),(11,4),(11,5),(11,10),(13,9),(15,10);
/*!40000 ALTER TABLE `stops_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `tags_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`tags_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,'potable_water'),(2,'power'),(3,'toilet'),(4,'shower'),(5,'river'),(6,'ocean'),(7,'lake'),(8,'parkinglot'),(9,'free'),(10,'very good');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `allview`
--

/*!50001 DROP VIEW IF EXISTS `allview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `allview` AS select `s`.`name` AS `name`,`s`.`lat` AS `lat`,`s`.`long` AS `long`,`n`.`name` AS `nation`,`n`.`eu` AS `eu`,`n`.`overnight_friendly` AS `overnight_friendly`,group_concat(coalesce(`t`.`name`) separator ', ') AS `tag` from (((`stops` `s` left join `nations` `n` on((`s`.`nations_id` = `n`.`nations_id`))) left join `stops_tags` `st` on((`s`.`stops_id` = `st`.`stops_id`))) left join `tags` `t` on((`st`.`tags_id` = `t`.`tags_id`))) group by `s`.`name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-30 17:16:20
