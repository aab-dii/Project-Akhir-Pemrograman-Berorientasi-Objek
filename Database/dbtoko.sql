-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: dbtoko
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbantar`
--

DROP TABLE IF EXISTS `tbantar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbantar` (
  `id_antar` int NOT NULL AUTO_INCREMENT,
  `id_barang` int DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  `id_pesanan` int DEFAULT NULL,
  `id_kurir` int DEFAULT NULL,
  PRIMARY KEY (`id_antar`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbantar`
--

LOCK TABLES `tbantar` WRITE;
/*!40000 ALTER TABLE `tbantar` DISABLE KEYS */;
INSERT INTO `tbantar` VALUES (4,14,8,55,13),(5,14,8,56,15),(6,14,8,62,15);
/*!40000 ALTER TABLE `tbantar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbkeranjang`
--

DROP TABLE IF EXISTS `tbkeranjang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbkeranjang` (
  `idKeranjang` int NOT NULL AUTO_INCREMENT,
  `idCust` int NOT NULL,
  `idProduk` int NOT NULL,
  `jumlah` int NOT NULL,
  PRIMARY KEY (`idKeranjang`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbkeranjang`
--

LOCK TABLES `tbkeranjang` WRITE;
/*!40000 ALTER TABLE `tbkeranjang` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbkeranjang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbpesanan`
--

DROP TABLE IF EXISTS `tbpesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbpesanan` (
  `idPesanan` int NOT NULL AUTO_INCREMENT,
  `idCust` int NOT NULL,
  `idProduk` int NOT NULL,
  `jumlah` int NOT NULL,
  `status` varchar(100) NOT NULL,
  `tanggalPesanan` date DEFAULT NULL,
  PRIMARY KEY (`idPesanan`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbpesanan`
--

LOCK TABLES `tbpesanan` WRITE;
/*!40000 ALTER TABLE `tbpesanan` DISABLE KEYS */;
INSERT INTO `tbpesanan` VALUES (53,8,14,123,'Pesanan telah sampai',NULL),(54,8,14,20,'Pesanan telah sampai',NULL),(55,8,14,20,'Sedang Diantar Kurir',NULL),(56,8,14,30,'Sedang Diantar Kurir',NULL),(57,8,14,20,'Menunggu Konfirmasi',NULL),(58,8,14,30,'Menunggu Konfirmasi',NULL),(59,8,14,20,'Menunggu Konfirmasi',NULL),(60,8,14,20,'Menunggu Konfirmasi',NULL),(61,8,14,20,'Menunggu Konfirmasi',NULL),(62,8,14,10,'Sedang Diantar Kurir',NULL),(63,8,14,10,'Menunggu Konfirmasi',NULL),(64,8,14,10,'Menunggu Konfirmasi',NULL),(65,8,14,10,'Menunggu Konfirmasi',NULL),(66,14,14,1,'Menunggu Konfirmasi',NULL),(67,14,14,1,'Menunggu Konfirmasi',NULL),(68,8,14,10,'Menunggu Konfirmasi','2024-06-01');
/*!40000 ALTER TABLE `tbpesanan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbproduk`
--

DROP TABLE IF EXISTS `tbproduk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbproduk` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) DEFAULT NULL,
  `stok` int DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL,
  `deskripsi` text,
  `merk` varchar(100) DEFAULT NULL,
  `jenis` varchar(100) NOT NULL,
  `bahan` varchar(100) DEFAULT NULL,
  `ukuran` varchar(100) DEFAULT NULL,
  `tipe` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `warna` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbproduk`
--

LOCK TABLES `tbproduk` WRITE;
/*!40000 ALTER TABLE `tbproduk` DISABLE KEYS */;
INSERT INTO `tbproduk` VALUES (2,'k1',1,1.00,'k1','1','perkakas',NULL,NULL,NULL,NULL,NULL),(4,'f1',1,1.00,'f1','1','furniture',NULL,NULL,NULL,NULL,NULL),(14,'123',81,123.00,'123','123','rumahtangga',NULL,NULL,NULL,NULL,NULL),(16,'qwer',132,123.00,'qwer','123','perkakas',NULL,NULL,NULL,NULL,NULL),(17,'rty',123,123.00,'132','123','elektronik',NULL,NULL,NULL,NULL,NULL),(18,'ghj',123,123.00,'123','123','furniture',NULL,NULL,NULL,NULL,NULL),(19,' ',1,1.00,' ',' ','rumahtangga',NULL,NULL,NULL,NULL,NULL),(20,' ',22,1.00,'1 ','2','rumahtangga',NULL,NULL,NULL,NULL,NULL),(21,'1',1,1.00,'1','1','rumahtangga',NULL,NULL,NULL,NULL,NULL),(22,'1',1,1.00,'1','1','rumahtangga',NULL,NULL,NULL,NULL,NULL),(23,'123',123,123.00,'123','123','rumahtangga',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tbproduk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuser`
--

DROP TABLE IF EXISTS `tbuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbuser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telp` varchar(100) DEFAULT NULL,
  `alamat` text,
  `role` varchar(100) NOT NULL,
  `saldo` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuser`
--

LOCK TABLES `tbuser` WRITE;
/*!40000 ALTER TABLE `tbuser` DISABLE KEYS */;
INSERT INTO `tbuser` VALUES (5,'admin','admin','admin123','admin@gmail.com','812312345',NULL,'admin',NULL),(6,NULL,'123','8023fd0ad4203dfc402481e651de11392c84c51f5336de1d56a9c1dffef732b1',NULL,NULL,NULL,'customer',NULL),(7,NULL,'abdi','8023fd0ad4203dfc402481e651de11392c84c51f5336de1d56a9c1dffef732b1',NULL,NULL,NULL,'customer',NULL),(8,'abdi','abdul','123','abdi@gmail.com','123','jl, amsangaji','customer',123770),(9,NULL,'aku','123',NULL,NULL,NULL,'customer',NULL),(12,'abdi','tes2','123','abdi@gmail.com','123',NULL,'kurir',NULL),(13,'123','qwe','qwe','kurir@gmail.com','123',NULL,'kurir',NULL),(14,'abdel','abdel','123','a@gmail.com','213','123','customer',100000),(15,'123','qwer','qwer','123@gmail.com','123',NULL,'kurir',NULL);
/*!40000 ALTER TABLE `tbuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-01 16:42:22
