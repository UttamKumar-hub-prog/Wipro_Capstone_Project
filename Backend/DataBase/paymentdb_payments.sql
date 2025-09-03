-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: paymentdb
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
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint DEFAULT NULL,
  `payment_status` tinyint DEFAULT NULL,
  `receiver_account_number` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `sender_account_number` varchar(255) DEFAULT NULL,
  `sender_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,1500,0,'1234567890','uttam','3222312312','chandu'),(2,1500,0,'1234567890','uttam','3222312312','chandu'),(3,1500,0,'1234567890','uttam','3222312312','chandu'),(4,1500,0,'9876543210','Ravi Reddy','123212342','sivakrishnavinayakvemana'),(6,2000,0,'123212342','sivakrishnavinayakvemana','123212342','sivakrishnavinayakvemana'),(7,2000,0,'1234567890','uttam','3222312312','chandu'),(8,2000,0,'123212342','sivakrishnavinayakvemana','123212342','sivakrishnavinayakvemana'),(9,4,0,'123212342','sivakrishnavinayakvemana','123212342','sivakrishnavinayakvemana'),(10,4,0,'123212342','sivakrishnavinayakvemana','123212342','sivakrishnavinayakvemana'),(14,4000,0,'9876543210','Ravi Reddy','9876543210','Ravi Reddy'),(15,4000,0,'9876543210','Ravi Reddy','1234567890','uttam'),(16,4000,0,'9876543210','Ravi Reddy','1234567890','uttam'),(17,4000,0,'123412342','sivakrishnavinasssayakvemana','9876543210','Ravi Reddy'),(18,4000,0,'123412342','sivakrishnavinayakvemana','5678901234','AnilraKumar'),(19,4000,0,'1234567890','uttam','5678901234','AnilraKumar'),(20,4000,0,'1234567890','uttam','5678901234','AnilraKumar'),(21,4000,0,'1234567890','uttam','5678901234','AnilraKumar'),(22,4000,0,'1234567890','uttam','5678901234','AnilraKumar'),(23,4000,0,'1234567890','uttam','5678901234','AnilraKumar'),(24,4000,0,'1234567890','uttam','5678901234','AnilraKumar'),(25,1,0,'1234567890','uttam','3222312312','chandu'),(26,1,0,'1234567890','uttam','3222312312','chandu'),(27,145,0,'1234567890','uttam','3222312312','chandu'),(28,145,0,'1234567890','uttam','3222312312','chandu'),(29,145,0,'1234567890','uttam','3222312312','chandu'),(30,145,0,'1234567890','uttam','3222312312','chandu'),(31,145,0,'1234567890','uttam','3222312312','chandu'),(32,1000,0,'1234567890','uttam','3222312312','chandu'),(38,1,0,'3222312312','chandu','3222312312','chandu'),(40,3,0,'1234567890','uttam','3222312312','chandu'),(41,3,0,'3222312312','chandu','3222312312','chandu'),(42,100,0,'1234567890','uttam','3222312312','chandu'),(43,1,0,'3222312312','chandu','3222312312','chandu');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
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
