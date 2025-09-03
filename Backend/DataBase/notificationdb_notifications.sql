-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: notificationdb
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'2025-08-31 16:18:15.217142','Payment of amount 1500 from user 1 to user 2 is SUCCESS',1,'FAILED',1),(2,'2025-08-31 16:18:15.217142','Payment of amount 1500 from user 1 to user 2 is SUCCESS',1,'SENT',2),(3,'2025-08-31 20:28:25.866039','Payment of amount 1500 from user 1 to user 2 is SUCCESS',3,'FAILED',1),(4,'2025-08-31 20:28:25.866039','Payment of amount 1500 from user 1 to user 2 is SUCCESS',3,'SENT',2),(5,'2025-08-31 20:28:44.030181','Payment of amount 1500 from user 7 to user 3 is SUCCESS',4,'SENT',3),(6,'2025-08-31 20:28:44.029176','Payment of amount 1500 from user 7 to user 3 is SUCCESS',4,'FAILED',7),(7,'2025-08-31 20:29:39.932172','Payment of amount 2000 from user 7 to user 7 is SUCCESS',6,'SENT',7),(8,'2025-08-31 20:29:39.932172','Payment of amount 2000 from user 7 to user 7 is SUCCESS',6,'FAILED',7),(9,'2025-09-02 00:05:10.492931','Payment of amount 2000 from user 1 to user 2 is SUCCESS',7,'FAILED',1),(10,'2025-09-02 00:05:10.492931','Payment of amount 2000 from user 1 to user 2 is SUCCESS',7,'SENT',2),(11,'2025-09-02 00:06:25.485268','Payment of amount 2000 from user 7 to user 7 is SUCCESS',8,'SENT',7),(12,'2025-09-02 00:06:25.485268','Payment of amount 2000 from user 7 to user 7 is SUCCESS',8,'FAILED',7),(13,'2025-09-02 11:58:35.282936','Payment of amount 4 from user 7 to user 7 is SUCCESS',9,'SENT',7),(14,'2025-09-02 11:58:35.282936','Payment of amount 4 from user 7 to user 7 is SUCCESS',9,'FAILED',7),(15,'2025-09-02 12:00:35.220399','Payment of amount 4 from user 7 to user 7 is SUCCESS',10,'SENT',7),(16,'2025-09-02 12:00:35.220399','Payment of amount 4 from user 7 to user 7 is SUCCESS',10,'FAILED',7),(17,'2025-09-02 15:29:14.011897','Payment of amount 4000 from user 3 to user 3 is SUCCESS',14,'FAILED',3),(18,'2025-09-02 15:29:14.011897','Payment of amount 4000 from user 3 to user 3 is SUCCESS',14,'SENT',3),(19,'2025-09-02 15:29:22.940418','Payment of amount 4000 from user 2 to user 3 is SUCCESS',15,'FAILED',2),(20,'2025-09-02 15:29:22.941443','Payment of amount 4000 from user 2 to user 3 is SUCCESS',15,'SENT',3),(21,'2025-09-02 15:30:51.632352','Payment of amount 4000 from user 2 to user 3 is SUCCESS',16,'FAILED',2),(22,'2025-09-02 15:30:51.632352','Payment of amount 4000 from user 2 to user 3 is SUCCESS',16,'SENT',3),(23,'2025-09-02 15:32:52.095856','Payment of amount 4000 from user 3 to user 9 is SUCCESS',17,'FAILED',3),(24,'2025-09-02 15:32:52.098374','Payment of amount 4000 from user 3 to user 9 is SUCCESS',17,'SENT',9),(25,'2025-09-02 15:33:11.476827','Payment of amount 4000 from user 6 to user 8 is SUCCESS',18,'FAILED',6),(26,'2025-09-02 15:33:11.479336','Payment of amount 4000 from user 6 to user 8 is SUCCESS',18,'SENT',8),(27,'2025-09-02 15:33:26.354361','Payment of amount 4000 from user 6 to user 2 is SUCCESS',19,'FAILED',6),(28,'2025-09-02 15:33:26.354361','Payment of amount 4000 from user 6 to user 2 is SUCCESS',19,'SENT',2),(29,'2025-09-02 15:43:25.910635','Payment of amount 4000 from user 6 to user 2 is SUCCESS',20,'FAILED',6),(30,'2025-09-02 15:43:25.910635','Payment of amount 4000 from user 6 to user 2 is SUCCESS',20,'SENT',2),(31,'2025-09-02 15:43:51.315503','Payment of amount 4000 from user 6 to user 2 is SUCCESS',21,'FAILED',6),(32,'2025-09-02 15:43:51.376217','Payment of amount 4000 from user 6 to user 2 is SUCCESS',21,'SENT',2),(33,'2025-09-02 15:59:10.647794','Payment of amount 4000 from user 6 to user 2 is SUCCESS',22,'FAILED',6),(34,'2025-09-02 15:59:10.647794','Payment of amount 4000 from user 6 to user 2 is SUCCESS',22,'SENT',2),(35,'2025-09-02 16:03:25.660926','Payment of amount 4000 from user 6 to user 2 is SUCCESS',23,'FAILED',6),(36,'2025-09-02 16:03:25.661925','Payment of amount 4000 from user 6 to user 2 is SUCCESS',23,'SENT',2),(37,'2025-09-02 16:05:42.558380','Payment of amount 4000 from user 6 to user 2 is SUCCESS',24,'SENT',2),(38,'2025-09-02 16:05:42.557381','Payment of amount 4000 from user 6 to user 2 is SUCCESS',24,'FAILED',6),(39,'2025-09-03 13:13:10.497025','Payment of amount 1 from user 1 to user 1 is SUCCESS',38,'SENT',1),(40,'2025-09-03 13:13:10.497025','Payment of amount 1 from user 1 to user 1 is SUCCESS',38,'FAILED',1),(41,'2025-09-03 13:13:35.995877','Payment of amount 3 from user 1 to user 2 is SUCCESS',40,'FAILED',1),(42,'2025-09-03 13:13:36.002201','Payment of amount 3 from user 1 to user 2 is SUCCESS',40,'SENT',2),(43,'2025-09-03 13:13:42.539553','Payment of amount 3 from user 1 to user 1 is SUCCESS',41,'FAILED',1),(44,'2025-09-03 13:13:42.539553','Payment of amount 3 from user 1 to user 1 is SUCCESS',41,'SENT',1),(45,'2025-09-03 15:57:59.368494','Payment of amount 100 from user 1 to user 2 is SUCCESS',42,'FAILED',1),(46,'2025-09-03 15:57:59.368494','Payment of amount 100 from user 1 to user 2 is SUCCESS',42,'SENT',2),(47,'2025-09-03 16:19:28.881665','Payment of amount 1 from user 1 to user 1 is SUCCESS',43,'SENT',1),(48,'2025-09-03 16:19:28.881665','Payment of amount 1 from user 1 to user 1 is SUCCESS',43,'FAILED',1);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-03 22:52:14
