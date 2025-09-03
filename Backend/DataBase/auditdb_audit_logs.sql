-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: auditdb
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
-- Table structure for table `audit_logs`
--

DROP TABLE IF EXISTS `audit_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit_logs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `amount` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `reference_id` bigint DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_logs`
--

LOCK TABLES `audit_logs` WRITE;
/*!40000 ALTER TABLE `audit_logs` DISABLE KEYS */;
INSERT INTO `audit_logs` VALUES (1,'PAYMENT_PROCESSED',1500,'2025-08-31 16:18:15.210805',1,'Payment successful','PaymentService','SUCCESS',1),(2,'PAYMENT_PROCESSED',1500,'2025-08-31 20:13:34.480257',2,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(3,'PAYMENT_PROCESSED',1500,'2025-08-31 20:13:35.945133',2,'Payment successful','PaymentService','SUCCESS',1),(4,'Payment_Intiated',1500,'2025-08-31 20:16:02.418584',2,'Payment processed successfully','PaymentService','SUCCESS',3),(5,'PAYMENT_PROCESSED',1500,'2025-08-31 20:28:25.749634',3,'Payment successful','PaymentService','SUCCESS',1),(6,'PAYMENT_PROCESSED',1500,'2025-08-31 20:28:43.984914',4,'Payment successful','PaymentService','SUCCESS',7),(7,'PAYMENT_PROCESSED',20000,'2025-08-31 20:29:25.675479',5,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',7),(8,'PAYMENT_PROCESSED',2000,'2025-08-31 20:29:39.910621',6,'Payment successful','PaymentService','SUCCESS',7),(9,'PAYMENT_PROCESSED',2000,'2025-09-02 00:05:10.065403',7,'Payment successful','PaymentService','SUCCESS',1),(10,'PAYMENT_PROCESSED',2000,'2025-09-02 00:06:25.468796',8,'Payment successful','PaymentService','SUCCESS',7),(11,'PAYMENT_PROCESSED',4,'2025-09-02 11:58:34.169333',9,'Payment successful','PaymentService','SUCCESS',7),(12,'PAYMENT_PROCESSED',4,'2025-09-02 12:00:35.028306',10,'Payment successful','PaymentService','SUCCESS',7),(13,'PAYMENT_PROCESSED',4000,'2025-09-02 12:01:00.345883',11,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(14,'PAYMENT_PROCESSED',4000,'2025-09-02 12:02:03.127117',12,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(15,'PAYMENT_PROCESSED',4000,'2025-09-02 15:28:33.902643',13,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(16,'PAYMENT_PROCESSED',4000,'2025-09-02 15:29:13.269067',14,'Payment successful','PaymentService','SUCCESS',3),(17,'PAYMENT_PROCESSED',4000,'2025-09-02 15:29:22.915450',15,'Payment successful','PaymentService','SUCCESS',2),(18,'PAYMENT_PROCESSED',4000,'2025-09-02 15:30:51.619187',16,'Payment successful','PaymentService','SUCCESS',2),(19,'PAYMENT_PROCESSED',4000,'2025-09-02 15:32:52.085754',17,'Payment successful','PaymentService','SUCCESS',3),(20,'PAYMENT_PROCESSED',4000,'2025-09-02 15:33:11.466747',18,'Payment successful','PaymentService','SUCCESS',6),(21,'PAYMENT_PROCESSED',4000,'2025-09-02 15:33:26.344155',19,'Payment successful','PaymentService','SUCCESS',6),(22,'PAYMENT_PROCESSED',4000,'2025-09-02 15:43:25.419334',20,'Payment successful','PaymentService','SUCCESS',6),(23,'PAYMENT_PROCESSED',4000,'2025-09-02 15:43:51.167649',21,'Payment successful','PaymentService','SUCCESS',6),(24,'PAYMENT_PROCESSED',4000,'2025-09-02 15:59:10.114666',22,'Payment successful','PaymentService','SUCCESS',6),(25,'PAYMENT_PROCESSED',4000,'2025-09-02 16:03:25.341527',23,'Payment successful','PaymentService','SUCCESS',6),(26,'PAYMENT_PROCESSED',4000,'2025-09-02 16:05:42.532271',24,'Payment successful','PaymentService','SUCCESS',6),(27,'PAYMENT_PROCESSED',1,'2025-09-03 02:37:02.414707',26,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(28,'PAYMENT_PROCESSED',1,'2025-09-03 02:37:02.414707',25,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(29,'PAYMENT_PROCESSED',1,'2025-09-03 02:37:05.320774',25,'Payment successful','PaymentService','SUCCESS',1),(30,'PAYMENT_PROCESSED',1,'2025-09-03 02:37:05.320774',26,'Payment successful','PaymentService','SUCCESS',1),(31,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:24.636749',27,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(32,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:24.693598',27,'Payment successful','PaymentService','SUCCESS',1),(33,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:26.217125',28,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(34,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:26.266047',28,'Payment successful','PaymentService','SUCCESS',1),(35,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:26.517784',29,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(36,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:26.546618',29,'Payment successful','PaymentService','SUCCESS',1),(37,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:27.086975',30,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(38,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:27.131143',30,'Payment successful','PaymentService','SUCCESS',1),(39,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:37.134256',31,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(40,'PAYMENT_PROCESSED',145,'2025-09-03 02:38:37.167831',31,'Payment successful','PaymentService','SUCCESS',1),(41,'PAYMENT_PROCESSED',1000,'2025-09-03 03:18:09.636251',32,'Payment successful but Kafka failed: Send failed','PaymentService','SUCCESS_WITHOUT_NOTIFICATION',1),(42,'PAYMENT_PROCESSED',1000,'2025-09-03 03:18:10.274697',32,'Payment successful','PaymentService','SUCCESS',1),(43,'PAYMENT_PROCESSED',1000,'2025-09-03 10:59:29.993684',33,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(44,'PAYMENT_PROCESSED',1000,'2025-09-03 11:06:49.600397',34,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(45,'PAYMENT_PROCESSED',1000,'2025-09-03 11:07:38.982797',35,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(46,'PAYMENT_PROCESSED',1000,'2025-09-03 12:46:03.192610',36,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(47,'PAYMENT_PROCESSED',1000,'2025-09-03 12:46:10.598286',37,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(48,'PAYMENT_PROCESSED',1,'2025-09-03 13:13:08.246105',38,'Payment successful','PaymentService','SUCCESS',1),(49,'PAYMENT_PROCESSED',1666,'2025-09-03 13:13:23.525821',39,'Unexpected error: Insufficient balance in sender\'s account','PaymentService','FAILED',1),(50,'PAYMENT_PROCESSED',3,'2025-09-03 13:13:35.916880',40,'Payment successful','PaymentService','SUCCESS',1),(51,'PAYMENT_PROCESSED',3,'2025-09-03 13:13:42.520810',41,'Payment successful','PaymentService','SUCCESS',1),(52,'PAYMENT_PROCESSED',100,'2025-09-03 15:57:57.677549',42,'Payment successful','PaymentService','SUCCESS',1),(53,'PAYMENT_PROCESSED',1,'2025-09-03 16:19:28.306933',43,'Payment successful','PaymentService','SUCCESS',1);
/*!40000 ALTER TABLE `audit_logs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-03 22:52:15
