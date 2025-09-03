-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: customerdb
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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `aadhaar` varchar(255) DEFAULT NULL,
  `account_type` enum('CURRENT','FIXED_DEPOSIT','LOAN','SAVINGS') DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `age` int DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` enum('FEMALE','MALE','OTHERS') DEFAULT NULL,
  `kyc_status` enum('ACTIVE','INACTIVE','PENDING','REJECTED') DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `pan` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'098765432109','SAVINGS','vizag',24,'chandu@gmail.com','MALE','PENDING','chandu','ALLPC8654G','0987654321','1234567890'),(2,'123456789070','SAVINGS','hyderabad',25,'uttam@gmail.com','MALE','PENDING','uttam','CLLPG5643R','1234567890','0987654321'),(3,'987654321012','CURRENT','Bangalore',30,'ravi.reddy@gmail.com','MALE','ACTIVE','Ravi Reddy','ABCDE1234F','9988776655','1234509876'),(4,'234567890123','SAVINGS','Mumbai',28,'sita.sharma@yahoo.com','FEMALE','PENDING','Sita Sharma','FGHIJ5678K','8899001122','5678901234'),(5,'345678901234','SAVINGS','Chennai',35,'anil.kumar@outlook.com','MALE','REJECTED','Anil Kumar','KLMNO9012P','7766554433','3456789012'),(6,'122134435326','SAVINGS','hyd',20,'anil.kumar12@outlook.com','MALE','PENDING','AnilraKumar','KLKNO9012P','7366554433','3456749012'),(7,'111111111111','SAVINGS','hyd',23,'sivakrishnavinayakvemana@gmail.com','MALE','PENDING','sivakrishnavinayakvemana','CDDFE4544W','7366554433','344566766'),(8,'111111111111','SAVINGS','hyd',23,'sivakrishnavinayakvemana@gmail.com','MALE','PENDING','sivakrishnavinayakvemana','CDDFE4544W','7366554433','344566766'),(9,'111111111111','SAVINGS','hyds',24,'sivakrishnavinayakvemana@gmail.com','MALE','PENDING','sivakrishnavinasssayakvemana','CDDFE4744W','7366554433','344569766'),(10,'111111111111','SAVINGS','hyds',24,'sivakrishnavinayakvemana@gmail.com','MALE','PENDING','sivakrishnavinasssayakvemana','CDDFE4744W','7366554433','344569766'),(11,'111111111111','SAVINGS','hyds',24,'sivakrishnavinayakvemana@gmail.com','MALE','PENDING','sivakrishnavinasssayakvemana','CDDFE4744W','7366554433','344569766'),(12,'111111111111','SAVINGS','hyds',24,'sivakrishnavinayakvemana@gmail.com','MALE','PENDING','sivakrishnavinasssayakvemana','CDDFE4744W','7366554433','344569766'),(13,'111111111111','SAVINGS','hyds',24,'sivakrishnavinayakvemana@gmail.com','MALE','PENDING','sivakrishnavinasssayakvemana','CDDFE4744W','7366554433','344569766'),(14,'111111111111','SAVINGS','hyds',24,'sivakrishnavinayakvemana@gmail.com','MALE','PENDING','sivakrishnavinasssayakvemana','CDDFE4744W','7366554433','344569766'),(15,NULL,NULL,'asdfghj',NULL,'narra@gnail.com',NULL,NULL,'narrra1',NULL,'5556667778',NULL),(16,'221221322343','CURRENT','qswedrty',23,'grfe@frg.com','MALE','PENDING','uttamkumarreddy','CDFES2344G','5566554565',NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
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
