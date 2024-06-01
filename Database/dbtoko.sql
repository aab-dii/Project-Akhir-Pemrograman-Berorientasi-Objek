-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: dbtoko
-- ------------------------------------------------------
-- Server version	8.0.37

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbantar`
--

LOCK TABLES `tbantar` WRITE;
/*!40000 ALTER TABLE `tbantar` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbpesanan`
--

LOCK TABLES `tbpesanan` WRITE;
/*!40000 ALTER TABLE `tbpesanan` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbproduk`
--

LOCK TABLES `tbproduk` WRITE;
/*!40000 ALTER TABLE `tbproduk` DISABLE KEYS */;
INSERT INTO `tbproduk` VALUES (29,'sapu',50,20000.00,'sapu besar','kaki lima','rumahtangga','sabut','1 meter',NULL,NULL,NULL),(30,'kemoceng',30,15000.00,'pembersih meja','hujan lebat','rumahtangga','serat sintesis','50 cm',NULL,NULL,NULL),(31,'palu',60,100000.00,'palu besi','mr yid','perkakas',NULL,NULL,NULL,NULL,NULL),(32,'tangga',2,80000.00,'tangga menuju surga','istiqomah','perkakas',NULL,NULL,NULL,NULL,NULL),(33,'lampu',32,140000.00,'menerangi hari-harimu','semangat','elektronik',NULL,NULL,'penerangan','AMBRT 100','putih'),(34,'kipas turbo',15,120000.00,'menerbangkan mimpi','moto','elektronik',NULL,NULL,'tornado','TRN 10','hitam'),(35,'lemari',2,350000.00,'menyimpan kenangan indah','kayu','furniture','atom kayu','4 meter',NULL,NULL,NULL),(36,'kursi',7,200000.00,'saksi bisu begadang','tolak IPI','furniture','besi kuat','200 cm',NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuser`
--

LOCK TABLES `tbuser` WRITE;
/*!40000 ALTER TABLE `tbuser` DISABLE KEYS */;
INSERT INTO `tbuser` VALUES (5,'admin','admin','admin123','admin@gmail.com','812312345',NULL,'admin',NULL),(17,'saya laju','silaju','123','laju@mail.com','6812654',NULL,'kurir',NULL),(18,'menyala abangkuh','simenyala','123','menyala@mail.com','6282341',NULL,'kurir',NULL),(19,NULL,'abdul','123',NULL,NULL,NULL,'customer',NULL);
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

-- Dump completed on 2024-06-01 22:58:00
