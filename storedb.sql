-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: storedb
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.22.04.1

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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goodsid` varchar(45) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `stockin` date DEFAULT NULL,
  `img` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'A001','MacBook Pro 2020',3000000,9,'2022-10-01',NULL),(2,'A002','Surface Pro 2021',3200000,12,'2022-09-28',NULL),(5,'A003','Dell Inspiron',1000000,5,'2022-09-18',NULL),(6,'A004','Acer Aspire',800000,4,'2022-09-20',NULL),(7,'A005','Lenovo ThinkPad',1000000,10,'2022-08-10',NULL),(8,'A006','Mi Laptop',800000,13,'2022-06-19',NULL),(9,'C001','Ipad Pro',2000000,19,'2022-03-29',NULL),(10,'C002','Samsaung Tablet',500000,17,'2022-01-20',NULL),(11,'C003','MI Tablet',400000,11,'2022-04-10',NULL),(12,'C004','IPhone X',1000000,12,'2022-01-20',NULL),(13,'B001','Remix Earphone',30000,10,'2022-02-25',NULL),(14,'B002','Bluetooth Headphone',40000,16,'2022-03-20',NULL),(15,'B003','Airport 1',15000,10,'2022-04-19',NULL),(16,'B004','Airport 2',20000,10,'2022-05-20',NULL),(18,'B005','Airport 3',30000,30,'2021-02-12',NULL),(19,'B006','Airport 4',40000,30,'2021-02-12',NULL),(20,'B007','Samsung Earphone',15000,47,'2021-03-17',NULL),(21,'B008','Samsung Wireless Earphone',15000,48,'2021-03-17',NULL),(22,'B009','Redmi Earphone',12000,30,'2021-07-27',NULL),(23,'B010','Redmi Wireless Earphone',12000,28,'2021-07-27',NULL),(27,'B011','Oppo Earphone',8000,85,'2021-02-12',NULL),(29,'C005','Iphone 14',2000000,9,'2021-12-23',NULL),(30,'C006','Iphone 11',1500000,10,'2022-04-01',NULL),(36,'C007','Vivo Y9',400000,12,'2021-02-10',NULL),(37,'A008','MSI Gaming Laptop',3000000,10,'2021-03-20',NULL);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goodsid` varchar(45) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (1,'A003','Dell Inspiron',1000000,3),(68,'B007','Samsung Earphone',15000,2),(69,'B011','Oppo Earphone',8000,5),(73,'A003','Dell Inspiron',1000000,2),(82,'B011','Oppo Earphone',8000,1),(83,'A002','Surface Pro 2021',3200000,3),(84,'C002','Samsaung Tablet',500000,2);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,'a','a@gmail.com','1000:a37f228d189d634f03d7c7db519125ef:8619c11f0cd0710f4b0518132ef39ddfe266c525e2862564d64e95ed724deacb82d6fa1e4eb10af1b4434f1e34276ec047639676a53653f18d1f900a326b008d','admin'),(14,'user','u@gmail.com','1000:2b67d5441b5016cef6fd20a83d014d12:86be3b2c8f3a555dafe26525171caac9ad197e564c5833893d7da1b6091fbe9c27a37df7e2c8d5b9935854639365055dd41937866a57a8f175cc9250854cfe90','user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-25 10:39:53
